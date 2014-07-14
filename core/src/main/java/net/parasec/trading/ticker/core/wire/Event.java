package net.parasec.trading.ticker.core.wire;

public class Event {

  private final long localTimestamp;


  public Event() {
    this(System.currentTimeMillis());
  }  

  public Event(final long localTimestamp) {
    this.localTimestamp = localTimestamp;
  }

  public long getLocaltimestamp() {
    return localTimestamp;
  }
}

