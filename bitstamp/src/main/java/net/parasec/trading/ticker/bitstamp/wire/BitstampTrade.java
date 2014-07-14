package net.parasec.trading.ticker.bitstamp.wire;


public final class BitstampTrade {

  private double price;
  private double amount;
  private String id;


  public BitstampTrade() {}

  public double getPrice() {
    return price;
  }

  public double getAmount() {
    return amount;
  }

  public String getId() {
    return id;
  }
}

