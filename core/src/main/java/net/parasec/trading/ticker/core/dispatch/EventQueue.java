package net.parasec.trading.ticker.core.dispatch;

import net.parasec.trading.ticker.core.wire.Event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;


public final class EventQueue implements EventListener {

  private final BlockingQueue<Event> q;

  
  public EventQueue(final EventListener e) {
    this(new LinkedBlockingQueue<Event>());
    consume(e);
  }

  public EventQueue(final BlockingQueue<Event> q) {
    this.q = q;
  }

  public void onEvent(final Event e) {
    try {
      q.put(e);
    } catch(final InterruptedException ie) {
      Thread.currentThread().interrupt();
    }    
  }

  private void consume(final EventListener el) {
    Executors.defaultThreadFactory().newThread(new Runnable() {
      public void run() {
        try {
          while(!Thread.currentThread().isInterrupted())
            el.onEvent(q.take());
        } catch(final InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
      } 
    }).start();      
  }
}

