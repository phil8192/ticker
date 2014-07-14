package net.parasec.trading.ticker.core;

import net.parasec.trading.ticker.core.dispatch.Bus;
import net.parasec.trading.ticker.core.dispatch.EventListener;

import net.parasec.trading.ticker.core.wire.Event;
import net.parasec.trading.ticker.core.wire.OrderEvent;
import net.parasec.trading.ticker.core.wire.OrderBook;
import net.parasec.trading.ticker.core.wire.Trade;

import java.util.List;
import java.util.ArrayList;


public class Ticker extends Bus {

  protected Class[] getEventTypes() {
    return new Class[] { 
      OrderEvent.class, 
      OrderBook.class, 
      Trade.class 
    };
  }
 
  public void watchOrders(final EventListener el) {
    subscribe(OrderEvent.class, el);  
  }

  public void watchOrderBook(final EventListener el) {
    subscribe(OrderBook.class, el);
  }

  public void watchTrades(final EventListener el) {
    subscribe(Trade.class, el);
  }

}

