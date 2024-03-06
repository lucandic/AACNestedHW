package structures;

/**
 * An easy way to store key/value pairs.  We assume that other
 * classes will access fields directly.
 */
public class KVPair<K,V> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The key.
   */
  private K key;

  /**
   * The value.
   */
  private V value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create an empty key/value pair.
   */
  KVPair() {
    this(null, null);
  } // KVPair()

  /**
   * Create a new key/value pair.
   */
  KVPair(K key, V value) {
    this.key = key;
    this.value = value;
  } // KVPair(K,V)

  public K getKey() {
    return this.key;
  }

  public V getValue() {
    return this.value;
  }

  public void setValue(V val) {
    this.value = val;
  }

  // +------------------+--------------------------------------------
  // | Standard methods |
  // +------------------+

  public KVPair<K,V> clone() {
    return new KVPair<K,V>(this.key, this.value);
  } // clone()

  public String toString() {
    return "{ " + this.key.toString() + ": " + this.value.toString() + "}";
  } // toString()
} // class KVPair

