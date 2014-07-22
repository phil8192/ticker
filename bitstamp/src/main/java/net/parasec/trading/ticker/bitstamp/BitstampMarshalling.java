package net.parasec.trading.ticker.bitstamp;

import net.parasec.trading.ticker.bitstamp.wire.BitstampOrder;
import net.parasec.trading.ticker.bitstamp.wire.BitstampOrderBook;
import net.parasec.trading.ticker.bitstamp.wire.BitstampTrade;
import net.parasec.trading.ticker.core.wire.OrderEvent;
import net.parasec.trading.ticker.core.wire.Direction;
import net.parasec.trading.ticker.core.wire.OrderInfo;
import net.parasec.trading.ticker.core.wire.OrderBook;
import net.parasec.trading.ticker.core.wire.Trade;
import net.parasec.trading.ticker.core.util.JSONUtil;


public final class BitstampMarshalling {

  public static OrderEvent parseOrder(final OrderEvent.State state, 
      final String raw) {
    final BitstampOrder rawOrder 
        = JSONUtil.unserialise(raw, BitstampOrder.class);
    final Direction direction 
        = rawOrder.getType() == 0 ? Direction.BUY : Direction.SELL;
    final int cents = (int) Math.round(rawOrder.getPrice()*100);
    final long satoshi = (long) Math.round(rawOrder.getAmount()*100000000);
    final OrderInfo orderInfo
        = new OrderInfo(Integer.toString(rawOrder.getId()), cents, satoshi, 
              rawOrder.getDatetime()); 
    return new OrderEvent(state, direction, "btc/usd", "bitstamp", orderInfo); 
  }

  private static OrderInfo[] parseOrderInfo(final double[][] orders) {
    final int len = orders.length;
    final OrderInfo[] orderInfo = new OrderInfo[len];
    for(int i = 0; i < len; i++) {
      final double[] rawOrder = orders[i];
      final int cents = (int) Math.round(rawOrder[0]*100);
      final long satoshi = (long) Math.round(rawOrder[1]*100000000);
      orderInfo[i] = new OrderInfo(null, cents, satoshi, 0);
    }
    return orderInfo;
  }

  public static OrderBook parseOrderBook(final String raw) {
    final BitstampOrderBook rawOrderBook 
        = JSONUtil.unserialise(raw, BitstampOrderBook.class); 
    final OrderInfo[] bids = parseOrderInfo(rawOrderBook.getBids());
    final OrderInfo[] asks = parseOrderInfo(rawOrderBook.getAsks());
    return new OrderBook(bids, asks, rawOrderBook.getTimestamp());
  } 

  public static Trade parseTrade(final String raw) {
    final BitstampTrade rawTrade 
        = JSONUtil.unserialise(raw, BitstampTrade.class); 
    final int cents = (int) Math.round(rawTrade.getPrice()*100);
    final long satoshi = (long) Math.round(rawTrade.getAmount()*100000000);
    return new Trade(null, cents, satoshi, 0, 0, rawTrade.getId(), null, null);
  }
}

