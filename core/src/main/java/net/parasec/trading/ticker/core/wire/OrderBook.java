package net.parasec.trading.ticker.core.wire;

// an order book _event_
// entire order book received at once.

public final class OrderBook extends Event {

  private final OrderInfo[] bids;
  private final OrderInfo[] asks;
  private final long exchangeTimestamp;


  public OrderBook(final OrderInfo[] bids, final OrderInfo[] asks,
                   final long exchangeTimestamp) {
    this(bids, asks, exchangeTimestamp, System.currentTimeMillis());
  }

  public OrderBook(final OrderInfo[] bids, final OrderInfo[] asks, 
                   final long exchangeTimestamp, final long localTimestamp) {
    super(localTimestamp);
    this.bids = bids;
    this.asks = asks;
    this.exchangeTimestamp = exchangeTimestamp;
  }       

  public OrderInfo[] getBids() {
    return bids;
  }

  public OrderInfo[] getAsks() {
    return asks;
  }   

  public long getExchangeTimestamp() {
    return exchangeTimestamp;
  }  
}

