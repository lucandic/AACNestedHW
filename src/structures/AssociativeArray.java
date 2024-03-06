package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Candice Lu
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The maximum capacity of the associative array.
   */
  public int capacity;

  /**
   * The array of key/value pairs.
   */
  private KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
    this.capacity = DEFAULT_CAPACITY;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> clone = new AssociativeArray<K, V>();
    KVPair<K, V> newPair;
    for (int i = 0; i < this.size; i++){
      newPair = this.pairs[i].clone();
      clone.pairs[i] = newPair;
      clone.size += 1;
    }
    return clone;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    String output = "{";
    if (this.size != 0) {
      output += this.pairs[0].toString().replaceAll("[\\{\\}]", "");
      for (int i = 1; i < this.size; i++){
        String str = this.pairs[i].toString().replaceAll("[\\{\\}]", "");
        output += "," + str;
      }
    }
    return output + " }";
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) throws NullKeyException {
    boolean found = false;
    if (key == null){
      throw new NullKeyException();
    }
    if (this.size == this.capacity) {
      this.expand();
      this.capacity *= 2;
    }
    for (int i = 0; i < this.size; i++){
      if (this.pairs[i].getKey().equals(key)){
        this.pairs[i].setValue(value);
        found = true;
      }
    }
    if (!found) {
      this.pairs[this.size] = new KVPair<K, V>(key, value);
      this.size += 1;
    }
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not 
   *                              appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++){
      if (this.pairs[i].getKey().equals(key)) {
        return this.pairs[i].getValue();
      }
    }
    throw new KeyNotFoundException();
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    for (int i = 0; i < this.size; i++){
      if (this.pairs[i].getKey().equals(key)) {
        return true;
      }
    }
    return false;
  } // hasKey(K)
  

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    int index = -1;
    if (this.size == 0) {
      return;
    }
    boolean found = false;
    for (int i = 0; i < this.size; i++){
      if (this.pairs[i].getKey().equals(key)) {
        index = i;
        this.size -= 1;
        found = true;
      }
    }
    if (found) {
      for (int i = index; i < this.size; i++) {
        this.pairs[i] = this.pairs[i+1];
      }
    }
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public KVPair<K,V>[] getPairs() {
    return this.pairs;
  } // size()


  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++){
      if (this.pairs[i].getKey().equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException();   
  } // find(K)
} // class AssociativeArray