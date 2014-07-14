package net.parasec.trading.ticker.core.util;

public final class JSONUtil {
  private final static com.google.gson.Gson gpp
      = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
  private final static com.google.gson.Gson g
      = new com.google.gson.Gson();

  public static String serialise(final Object o, final boolean prettyPrint) {
    return prettyPrint ? gpp.toJson(o) : g.toJson(o);
  }

  public static <T> T unserialise(final String json, final Class<T> c) {
    return g.fromJson(json, c);
  }
}

