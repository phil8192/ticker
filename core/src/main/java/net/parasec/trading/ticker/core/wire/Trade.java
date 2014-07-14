package net.parasec.trading.ticker.core.wire;

public final class Trade extends Event {

  private Direction direction;
  private int price;
  private long volume;
  private long exchangeTimestamp;
  private String exchangeTradeId;
  private String makerIdentifier;
  private String takerIdentifier;

  
  public Trade(final Direction direction, final int price, final long volume, 
               final long exchangeTimestamp, final String exchangeTradeId, 
               final String makerIdentifier, final String takerIdentifier) {
    this(direction, price, volume, System.currentTimeMillis(), 
         exchangeTimestamp, exchangeTradeId, makerIdentifier, takerIdentifier);
  }

  public Trade(final Direction direction, final int price, final long volume,
               final long localTimestamp, final long exchangeTimestamp, 
               final String exchangeTradeId, final String makerIdentifier, 
               final String takerIdentifier) {
    super(localTimestamp);
    this.direction = direction;
    this.price = price;
    this.volume = volume;
    this.exchangeTimestamp = exchangeTimestamp;
    this.exchangeTradeId = exchangeTradeId;
    this.makerIdentifier = makerIdentifier;
    this.takerIdentifier = takerIdentifier;
  }

  public Direction getDirection() {
    return direction;
  }

  public int getPrice() {
    return price;
  }

  public long getVolume() {
    return volume;
  }

  public long getExchangeTimestamp() {
    return exchangeTimestamp;
  }

  public String getExchangeTradeId() {
    return exchangeTradeId;
  }

  public String getMakerIdentifier() {
    return makerIdentifier;
  }

  public String getTakerIdentifier() {
    return takerIdentifier;
  }
}

