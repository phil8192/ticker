package net.parasec.trading.ticker.bitstamp;

import net.parasec.trading.ticker.core.dispatch.EventListener;
import net.parasec.trading.ticker.core.dispatch.EventQueue;
import net.parasec.trading.ticker.core.wire.OrderEvent;
import net.parasec.trading.ticker.core.wire.Trade;
import net.parasec.trading.ticker.core.wire.OrderBook;
import net.parasec.trading.ticker.core.wire.Event;
import net.parasec.trading.ticker.core.util.JSONUtil;

import org.apache.log4j.Logger;


public final class TestBitstamp {

  private final static Logger LOG = Logger.getLogger(TestBitstamp.class);

  public static void testQueues() {
    final BitstampTicker bt = new BitstampTicker();
    bt.watchOrders(new EventQueue(new EventListener<OrderEvent>() {
      public void onEvent(final OrderEvent oe) {
        LOG.info(JSONUtil.serialise(oe, true));
      }
    }));
    bt.watchTrades(new EventQueue(new EventListener<Trade>() {
      public void onEvent(final Trade t) {
        LOG.info(JSONUtil.serialise(t, true));
      }
    }));
    bt.watchOrderBook(new EventQueue(new EventListener<OrderBook>() {
      public void onEvent(final OrderBook d) {
        LOG.info(JSONUtil.serialise(d, false));
      }
    }));
  }



  public static void testSingleQueue() {
    final BitstampTicker bt = new BitstampTicker();

    final EventQueue eq = new EventQueue(new EventListener<Event>() {
      public void onEvent(final Event e) {
        System.out.println(JSONUtil.serialise(e, true));
      }
    });
    bt.watchOrders(eq);
    bt.watchTrades(eq);
    bt.watchOrderBook(eq);
  }

  public static void main(final String[] args) {
    testSingleQueue();
    //testQueues();
  }
}
