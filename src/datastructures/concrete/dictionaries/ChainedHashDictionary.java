package datastructures.concrete.dictionaries;

import datastructures.concrete.KVPair;
import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * See the spec and IDictionary for more details on what each method should do
 */
public class ChainedHashDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private IDictionary<K, V>[] chains;
    
    private int totalPairs;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int RESIZE_CONSTANT = 2;

    // You're encouraged to add extra fields (and helper methods) though!

    public ChainedHashDictionary() {
        chains = makeArrayOfChains(DEFAULT_CAPACITY);
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain IDictionary<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private IDictionary<K, V>[] makeArrayOfChains(int size) {
        // Note: You do not need to modify this method.
        // See ArrayDictionary's makeArrayOfPairs(...) method for
        // more background on why we need this method.
        return (IDictionary<K, V>[]) new IDictionary[size];
    }

    @Override
    public V get(K key) {
        int hash = hashOf(key);
        if (chains[hash] == null || !chains[hash].containsKey(key)) {
            throw new NoSuchKeyException();
        }
        return chains[hash].get(key);
    }

    @Override
    public void put(K key, V value) {
        int hash = hashOf(key);
        IDictionary<K, V> bucket = chains[hash];
        if (bucket == null) {
            chains[hash] = new ArrayDictionary<K, V>();
            bucket = chains[hash];
        }
        if (!bucket.containsKey(key)) {
            totalPairs++;
            if (totalPairs / chains.length == 1) {
                resize();
                put(key, value);
            }
        } 
        bucket.put(key, value); 
    
    }
    
    // Increases size of table by resize constant, rehashes everything in old table.
    private void resize() { 
        IDictionary<K, V>[] oldChains = chains;
        chains = makeArrayOfChains(chains.length * RESIZE_CONSTANT);
        totalPairs = 0;
        for (int i = 0; i < oldChains.length; i++) {
            if (oldChains[i] != null) {
                Iterator<KVPair<K, V>> iterator = oldChains[i].iterator();
                while (iterator.hasNext()) {
                    KVPair<K, V> pair = iterator.next();
                    this.put(pair.getKey(), pair.getValue());
                }
            }
        }
    }
    
    @Override
    public V remove(K key) {
        int hash = hashOf(key);
        if (chains[hash] == null || !chains[hash].containsKey(key)) {
            throw new NoSuchKeyException();
        }
        totalPairs--;
        return chains[hash].remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        int h = hashOf(key);
        if (chains[h] == null) {
            return false;
        }
        return chains[hashOf(key)].containsKey(key);
    }
    
    // Returns the hash code fitted to the internal table
    private int hashOf(K key) {
        return abs(((key == null) ? 0 : key.hashCode()) % chains.length);
    }
    
    // Returns the absolute value of n.
    private static int abs(int n) {
        return n > 0 ? n : -n;
    }
    
    @Override
    public int size() {
        return totalPairs;
    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // Note: you do not need to change this method
        return new ChainedIterator<>(this.chains);
    }
   

    /**
     * Hints:
     *
     * 1. You should add extra fields to keep track of your iteration
     *    state. You can add as many fields as you want. If it helps,
     *    our reference implementation uses three (including the one we
     *    gave you).
     *
     * 2. Before you try and write code, try designing an algorithm
     *    using pencil and paper and run through a few examples by hand.
     *
     *    We STRONGLY recommend you spend some time doing this before
     *    coding. Getting the invariants correct can be tricky, and
     *    running through your proposed algorithm using pencil and
     *    paper is a good way of helping you iron them out.
     *
     * 3. Think about what exactly your *invariants* are. As a
     *    reminder, an *invariant* is something that must *always* be 
     *    true once the constructor is done setting up the class AND 
     *    must *always* be true both before and after you call any 
     *    method in your class.
     *
     *    Once you've decided, write them down in a comment somewhere to
     *    help you remember.
     *
     *    You may also find it useful to write a helper method that checks
     *    your invariants and throws an exception if they're violated.
     *    You can then call this helper method at the start and end of each
     *    method if you're running into issues while debugging.
     *
     *    (Be sure to delete this method once your iterator is fully working.)
     *
     * Implementation restrictions:
     *
     * 1. You **MAY NOT** create any new data structures. Iterators
     *    are meant to be lightweight and so should not be copying
     *    the data contained in your dictionary to some other data
     *    structure.
     *
     * 2. You **MAY** call the `.iterator()` method on each IDictionary
     *    instance inside your 'chains' array, however.
     */
    private static class ChainedIterator<K, V> implements Iterator<KVPair<K, V>> {
        private IDictionary<K, V>[] chains;
        private int nextUsefulIndex;
        private Iterator<KVPair<K, V>> currentIterator;

        public ChainedIterator(IDictionary<K, V>[] chains) {
            this.chains = chains;
            updateIndex(); 
            if (nextUsefulIndex < chains.length) {
                currentIterator = chains[nextUsefulIndex].iterator();
            }
            updateIndex();
        }
        
        /**
         * Returns the index of the next non-null bucket. If no more exist,
         * then returns chains.size();
         * @return
         */
        private void updateIndex() {
            if (currentIterator != null) {
                nextUsefulIndex++;
            }
            while (nextUsefulIndex < chains.length && (chains[nextUsefulIndex] == null 
                    || !chains[nextUsefulIndex].iterator().hasNext())) {
                nextUsefulIndex++;
            }
        }

        @Override
        public boolean hasNext() {
            return (currentIterator != null && currentIterator.hasNext()) || nextUsefulIndex < chains.length;
        }

        @Override
        public KVPair<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } 
            if (currentIterator.hasNext()) {
                return currentIterator.next();
            }
            else {
                currentIterator = chains[nextUsefulIndex].iterator();
                updateIndex();
                return currentIterator.next();
            }
        }
    }
}
