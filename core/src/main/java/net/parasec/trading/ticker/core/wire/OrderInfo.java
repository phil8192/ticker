package net.parasec.trading.ticker.core.wire;

public class OrderInfo {

  private final String exchangeOrderId;
  private int limitPrice;
  private long volume;
  private final long exchangeTimestamp;

  
  public OrderInfo(final String exchangeOrderId, final int limitPrice, 
                   final long volume, final long exchangeTimestamp) {
    this.exchangeOrderId = exchangeOrderId;
    this.limitPrice = limitPrice;
    this.volume = volume;
    this.exchangeTimestamp = exchangeTimestamp;
  }

  public String getexchangeOrderId() {
    return exchangeOrderId;
  }
 
  public int getLimitPrice() {
    return limitPrice;
  }

  public long getVolume() {
    return volume;
  }

  public long getExchangeTimestamp() {
    return exchangeTimestamp;
  }

  public void setLimitPrice(final int limitPrice) {
    this.limitPrice = limitPrice;
  }

  public void setVolume(final long volume) {
    this.volume = volume;
  }

}

