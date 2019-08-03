package common.datastructures.concrete;

import common.datastructures.interfaces.IPriorityQueue;
import common.misc.exceptions.EmptyContainerException;

/**
 * See IPriorityQueue for details on what each method must do.
 */
public class ArrayHeap<T extends Comparable<T>> implements IPriorityQueue<T> {
    // See spec: you must implement a implement a 4-heap.
    private static final int NUM_CHILDREN = 4;

    // You MUST use this field to store the contents of your heap.
    // You may NOT rename this field: we will be inspecting it within
    // our private tests.
    private T[] heap;

    // Feel free to add more fields and constants.
    private int items;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayHeap() {
        heap  = makeArrayOfT(DEFAULT_CAPACITY);
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain elements of type T.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private T[] makeArrayOfT(int size) {
        // This helper method is basically the same one we gave you
        // in ArrayDictionary and ChainedHashDictionary.
        //
        // As before, you do not need to understand how this method
        // works, and should not modify it in any way.
        return (T[]) (new Comparable[size]);
    }

    @Override
    public T removeMin() {
        T min = peekMin();
        heap[0] = heap[items-- - 1];
        percolateDown(0);
        return min;
    } 

    @Override
    public T peekMin() {
        if (isEmpty()) {
            throw new EmptyContainerException();
        }
        return heap[0];
    }

    @Override
    public void insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        heap[items++] = item;
        if (items == heap.length) {
            T[] oldHeap = heap;
            heap = makeArrayOfT(oldHeap.length * 2);
            items = 0;
            for (int i = 0; i < oldHeap.length; i++) {
                insert(oldHeap[i]);
            }
        }
        percolateUp(items - 1);
    }
    
    /**
     * percolates the given index up to its correct spot
     * @param index of percolated entry
     */
    private void percolateUp(int index) {
        int parent = (index - 1)/NUM_CHILDREN;
        if (parent >= 0) {
            if (heap[index].compareTo(heap[parent]) < 0) {
                T temp = heap[parent];
                heap[parent] = heap[index];
                heap[index] = temp;
                percolateUp(parent);
            }
        }
    }
    

    @Override
    public int size() {
        return items;
    }
    
    
    /**
     * sorts the given sub heap and its children if swapped
     * @param parent
     */
    private void percolateDown(int parent) {
        if (parent <= lastParent()) {
            //index of first and last child
            int firstChild = parent * NUM_CHILDREN + 1;
            int lastChild = firstChild + NUM_CHILDREN - 1;
            
            //finds the smallest element
            int indexOfSmallestElement = parent;
            for (int i = firstChild; i < lastChild + 1 && i < items; i++) {
                if (heap[indexOfSmallestElement].compareTo(heap[i]) > 0) {
                    indexOfSmallestElement = i;
                }
            }
            
            //if child is smaller, swap
            if (indexOfSmallestElement != parent) {
                T temp = heap[parent];
                heap[parent] = heap[indexOfSmallestElement];
                heap[indexOfSmallestElement] = temp;
                percolateDown(indexOfSmallestElement);
            }
            
        }
    }
    
    /**
     * @return the last parent in the heap
     */
    private int lastParent() {
        return (items - 2)/ NUM_CHILDREN;
    }
}
