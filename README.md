Ticker Utils
============

This is a basic set of utilities for streaming real time trade and limit order 
book event data. Events are pushed to a queue, or set of queues depending on 
the implementation.

An exchange specific implementation for the Bitstamp (bitcoin) exchange can be
seen in bitstamp/ . The implementation (asynchronously) consumes trade, order 
book (delta) updates and limit order book data via a websocket, pushing events
to a blocking queue. 


Building
--------

```bash
./build.sh
```

Running
-------

Test bitstamp feed (print to screen)

```bash
./run.sh
```

Example output from ^
---------------------

```json
{
  "state": "DELETED",
  "direction": "BUY",
  "symbol": "btc/usd",
  "venue": "bitstamp",
  "orderInfo": {
    "exchangeOrderId": "58494246",
    "limitPrice": 22337,
    "volume": 2660000000,
    "exchangeTimestamp": 1425231185
  },
  "localTimestamp": 1425231194081
}
{
  "state": "CREATED",
  "direction": "SELL",
  "symbol": "btc/usd",
  "venue": "bitstamp",
  "orderInfo": {
    "exchangeOrderId": "58494254",
    "limitPrice": 24797,
    "volume": 37000000,
    "exchangeTimestamp": 1425231193
  },
  "localTimestamp": 1425231194217
}
```

