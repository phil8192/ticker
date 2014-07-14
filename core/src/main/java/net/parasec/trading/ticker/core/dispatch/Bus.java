package net.parasec.trading.ticker.core.dispatch;

import net.parasec.trading.ticker.core.wire.Event;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public abstract class Bus {

  private final Map<String, List<EventListener>> eventMap
      = new HashMap<String, List<EventListener>>();


  public Bus() {
    for(final Class c : getEventTypes())
      eventMap.put(c.getSimpleName(), new ArrayList<EventListener>());
  }

  private List<EventListener> getSubscribers(final Class c) {
    return eventMap.get(c.getSimpleName());
  }

  public void subscribe(final Class c, final EventListener e) {
    getSubscribers(c).add(e); 
  }

  protected void broadcast(final Event e) {
    for(final EventListener el : getSubscribers(e.getClass()))
      el.onEvent(e);  
  }

  protected abstract Class[] getEventTypes(); 
}

