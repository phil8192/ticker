package net.parasec.trading.ticker.core.wire;


public class OrderEvent extends Event {

  public enum State {
    CREATED,
    UPDATED,
    DELETED
  }

  private final State state;
  private final Direction direction;
  private final String symbol;
  private final String venue;

  private final OrderInfo orderInfo;


  public OrderEvent(final State state, final Direction direction,
                    final String symbol, final String venue, 
                    final OrderInfo orderInfo) {
    this(state, direction, symbol, venue, System.currentTimeMillis(), 
         orderInfo);
  }

  public OrderEvent(final State state, final Direction direction, 
                    final String symbol, String venue, 
                    final long localTimestamp, final OrderInfo orderInfo) {
    super(localTimestamp);
    this.state = state;
    this.direction = direction;
    this.symbol = symbol;
    this.venue = venue;
    this.orderInfo = orderInfo;
  }

  public State getState() {
    return state;
  }

  public Direction getDirection() {
    return direction;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getVenue() {
    return venue;
  }

  public OrderInfo getOrderInfo() {
    return orderInfo;
  }
}

