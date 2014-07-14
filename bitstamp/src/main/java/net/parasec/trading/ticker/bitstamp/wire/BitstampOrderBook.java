package net.parasec.trading.ticker.bitstamp.wire;


public final class BitstampOrderBook {

  private double[][] bids;
  private double[][] asks;
  private long timestamp; 
      
 
  public BitstampOrderBook() {}

  public double[][] getBids() {
    return bids;
  }

  public double[][] getAsks() {
    return asks;
  }

  public long getTimestamp() {
    return timestamp;
  }
}

