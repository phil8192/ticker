package net.parasec.trading.ticker.bitstamp;

import net.parasec.trading.ticker.core.Ticker;
import net.parasec.trading.ticker.core.wire.OrderEvent;
import org.apache.log4j.Logger;
import com.pusher.client.*;
import com.pusher.client.channel.*;
import com.pusher.client.connection.*;


public final class BitstampTicker extends Ticker {
  private final static Logger LOG = Logger.getLogger(BitstampTicker.class);

  private final static String PUSHER_PUBLISHER = "de504dc5763aeef9ff52";
  private final static long ACTIVITY_TIMEOUT = 1000*30;
  private final static long PONG_TIMEOUT = 1000*30;
  private final static long PUSHER_RETRY = 1000*5;
  private final static boolean ENCRYPT_PUSHER = false;
  private final static String QUOTES_CHANNEL = "live_orders";
  private final static String QUOTE_CREATED_TOPIC = "order_created";
  private final static String QUOTE_UPDATED_TOPIC = "order_changed";
  private final static String QUOTE_DELETED_TOPIC = "order_deleted";
  private final static String TRADES_CHANNEL = "live_trades";
  private final static String TRADES_TOPIC = "trade";
  private final static String ORDERBOOK_CHANNEL = "order_book";
  private final static String ORDERBOOK_TOPIC = "data";

  public BitstampTicker() {
    initBroadcaster();
  }

  private void initBroadcaster() {
    
    // configure pusherapp
    final PusherOptions options = new PusherOptions()
        .setEncrypted(ENCRYPT_PUSHER)
        .setActivityTimeout(ACTIVITY_TIMEOUT)
        .setPongTimeout(PONG_TIMEOUT);
    final Pusher pusher = new Pusher(PUSHER_PUBLISHER, options);

    // connect to pusherapp
    pusher.connect(new ConnectionEventListener() {
      public final void onConnectionStateChange(ConnectionStateChange change) {
        LOG.info("connection state change from " + change.getPreviousState() +
            " to " + change.getCurrentState());
        if(change.getCurrentState().equals(ConnectionState.DISCONNECTED)) {
          try {
            LOG.info("waiting for " + PUSHER_RETRY + " seconds");
            Thread.sleep(PUSHER_RETRY);
          } catch(final InterruptedException ie) {
            LOG.debug(ie, ie);
            Thread.currentThread().interrupt();
          }
          LOG.info("attempting reconnect");
          pusher.connect();
        }
      }
      public final void onError(final String message, final String code, 
          final Exception e) {
        LOG.error("message = " + message + " code = " + code, e);
      }
    }, ConnectionState.ALL);

    // subscribe to orders channel (created, updated, deleted)
    final Channel ordersChannel = pusher.subscribe(QUOTES_CHANNEL);
    ordersChannel.bind(QUOTE_CREATED_TOPIC, new SubscriptionEventListener() {
      public final void onEvent(final String channel, final String event, 
          final String data) {
        broadcast(BitstampMarshalling.parseOrder(OrderEvent.State.CREATED, 
            data));
      }
    });
    ordersChannel.bind(QUOTE_UPDATED_TOPIC, new SubscriptionEventListener() {
      public final void onEvent(final String channel, final String event, 
          final String data) {
        broadcast(BitstampMarshalling.parseOrder(OrderEvent.State.UPDATED, 
            data));
      }
    });
    ordersChannel.bind(QUOTE_DELETED_TOPIC, new SubscriptionEventListener() {
      public final void onEvent(final String channel, final String event, 
          final String data) {
        broadcast(BitstampMarshalling.parseOrder(OrderEvent.State.DELETED, 
            data));
      }
    });

    // subscribe to trades channel
    final Channel tradesChannel = pusher.subscribe(TRADES_CHANNEL);
    tradesChannel.bind(TRADES_TOPIC, new SubscriptionEventListener() {
      public final void onEvent(final String channel, final String event, 
          final String data) {
        broadcast(BitstampMarshalling.parseTrade(data));
      }
    });

    // subscribe to order book channel
    final Channel orderBookChannel = pusher.subscribe(ORDERBOOK_CHANNEL);
    orderBookChannel.bind(ORDERBOOK_TOPIC, new SubscriptionEventListener() {
      public final void onEvent(final String channel, final String event,
          final String data) {
        broadcast(BitstampMarshalling.parseOrderBook(data));
      }
    });
  } 
}

