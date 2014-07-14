package net.parasec.trading.ticker.bitstamp.wire;


public final class BitstampOrder {

  private int order_type;
  private double price; 
  private double amount;
  private long datetime;
  private int id;


  public BitstampOrder() {}

  public int getType() {
    return order_type;
  }

  public double getPrice() {
    return price;
  }

  public double getAmount() {
    return amount;
  }

  public long getDatetime() {
    return datetime;
  }
    
  public int getId() {
    return id;
  }
}

