package common.datastructures.concrete.dictionaries;

import java.util.Iterator;
import java.util.NoSuchElementException;

import common.datastructures.concrete.KVPair;
import common.datastructures.interfaces.IDictionary;
import common.misc.exceptions.NoSuchKeyException;

/**
 * See IDictionary for more details on what this class should do
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;

    // You're encouraged to add extra fields (and helper methods) though!
    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    
    public ArrayDictionary() {
        pairs = makeArrayOfPairs(DEFAULT_CAPACITY);
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain Pair<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);

    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        if (index == -1) {
            throw new NoSuchKeyException();
        }
        return pairs[index].value;
    }

    @Override
    public void put(K key, V value) {
        int index = indexOf(key);
        if (index == -1) {
            if (size == pairs.length) {
                Pair<K, V>[] newPairs = makeArrayOfPairs(size * 2);
                for (int i = 0; i < size; i++) {
                    newPairs[i] = pairs[i];
                }
                pairs = newPairs;
            } 
            pairs[size] = new Pair<K, V>(key, value);
            size++;
        } else {
            pairs[index].value = value;
        }
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) {
            throw new NoSuchKeyException();
        }
        V value = pairs[index].value;
        pairs[index] = pairs[size - 1];
        size--;
        return value;
    }

    @Override
    public boolean containsKey(K key) {
        return indexOf(key) != -1;
    }

    @Override
    public int size() {
        return size;
    }
    
    
    /**
     * Returns the index of key in array. Returns -1 if not in array.
     * Resizes the array if there are sufficient empty indices. 
     * @param key
     * @return index
     */
    private int indexOf(K key) {
        for (int i = 0; i < size; i++) {
            if (isEquivalentKeys(pairs[i].key, key)) {
                return i;
            }
        }
        return -1;
    }
    
   private boolean isEquivalentKeys(K key1, K key2) { 
       if (key1 == null) {
           return key2 == null;
       } 
       return key1.equals(key2);
   }
   
    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new ArrayDictionaryIterator<K, V>(this.pairs);
    }
    
    private static class ArrayDictionaryIterator<K, V> implements Iterator<KVPair<K, V>> {
        private int currentIndex;
        private Pair<K, V>[] pairs;
        
        public ArrayDictionaryIterator(Pair<K, V>[] pairs) {
            this.pairs = pairs;
        }
        
        @Override
        public boolean hasNext() {
            return pairs[currentIndex] != null;
        }

        @Override
        public KVPair<K, V> next() throws NoSuchElementException{
           if (!hasNext()) {
               throw new NoSuchElementException();
           }
           Pair<K, V> p = pairs[currentIndex++];
           return new KVPair<K, V>(p.key, p.value);
        }
        
    }
}
