package net.parasec.trading.ticker.core.dispatch;

import net.parasec.trading.ticker.core.wire.Event;


public interface EventListener<T extends Event> {
  void onEvent(T t);
}

