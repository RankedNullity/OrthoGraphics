package common.datastructures.concrete;

import common.datastructures.interfaces.IList;
import common.misc.exceptions.EmptyContainerException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Note: For more info on the expected behavior of your methods, see
 * the source code for IList.
 */
public class DoubleLinkedList<T> implements IList<T> {
    // You may not rename these fields or change their types.
    // We will be inspecting these in our private tests.
    // You also may not add any additional fields.
    private Node<T> front;
    private Node<T> back;
    private int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }
    
    /**
     * Returns the Node at a given index
     * 
     * @throws  IndexOutOfBoundsException if index < 0 
     * or index < the largest index
     */
    private Node<T> findIndex(int index) throws IndexOutOfBoundsException{
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current;
        //if index is in the first half, starts from the back
        //if index is in the second half, starts from the front
        if (index < size/2) {
            current = this.front;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = this.back;
            for (int i = size-1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public void add(T item) {
        if (size == 0) {
            front = new Node<T>(item);
            back = front;
        } else {
            back.next = new Node<T>(back, item, null);
            back = back.next;
        }
        size++;
    }

    @Override
    public T remove() throws EmptyContainerException{
        if (size == 0) {
            throw new EmptyContainerException();
        }
        T item = back.data;
        //deletes list if only one object
        if (size == 1) {
            back = null;
            front = null;
        } else {
            back = back.prev;
            back.next = null;
        }
        size--;
        return item;
    }

    //findIndex throws IndexOutOfBoundsException so not needed here
    @Override
    public T get(int index){
        Node<T> current = findIndex(index);
        return current.data;
    }

    //findIndex checks for IndexOutOfBounds so not needed here
    @Override
    public void set(int index, T item) {
        if (index >= size + 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //index at back case
        if (index == size-1) {
            remove();
            add(item);
        //index at front case (if index not out of bounds)
        } else if (index == 0 && size != 0) {
            front.next.prev = new Node<T>(null, item, front.next);
            front = front.next.prev;
        }else {
            Node<T> current = findIndex(index);
            current.prev.next = new Node<T>(current.prev, item, current.next);
            current.next.prev = current.prev.next;
        }
    }

    @Override
    public void insert(int index, T item) throws IndexOutOfBoundsException{
        if (index >= size + 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //index at back case (add(item) increments size)
        if (index == size) {
            add(item);
        //index at front case
        } else if (index == 0) {
            front.prev = new Node<T>(null, item, front);
            front = front.prev;
            size++;
        } else {
            Node<T> current = findIndex(index);
            current.prev = new Node<T>(current.prev, item, current);
            current.prev.prev.next = current.prev;
            size++;
        }
    }

    //findIndex throws IndexOutOfBoundsException so not needed here
    @Override
    public T delete(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T item;
        //if index is at the end (remove() decreases size)
        if (index == size - 1) {
            item = back.data;
            remove();
        //if index is at the front
        } else if (index == 0 && size != 0) {
            item = front.data;
            front = front.next;
            front.prev = null;
            size--;
        } else {
            Node<T> current = findIndex(index);
            item = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
        return item;
    }

    @Override
    public int indexOf(T item) {
        Node<T> current = this.front;
        for (int i = 0; i < size; i++) {
            //returns index if item found
            if (Objects.equals(current.data, item)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T other) {
        return indexOf(other) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        // Note: we have provided a part of the implementation of
        // an iterator for you. You should complete the methods stubs
        // in the DoubleLinkedListIterator inner class at the bottom
        // of this file. You do not need to change this method.
        return new DoubleLinkedListIterator<>(this.front);
    }

    private static class Node<E> {
        // You may not change the fields in this node or add any new fields.
        public final E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(E data) {
            this(null, data, null);
        }

        // Feel free to add additional constructors or methods to this class.
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        // You should not need to change this field, or add any new fields.
        private Node<T> current;

        public DoubleLinkedListIterator(Node<T> current) {
            // You do not need to make any changes to this constructor.
            this.current = current;
        }

        /**
         * Returns 'true' if the iterator still has elements to look at;
         * returns 'false' otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next item in the iteration and internally updates the
         * iterator to advance one element forward.
         *
         * @throws NoSuchElementException if we have reached the end of the iteration and
         *         there are no more elements to look at.
         */
        public T next() throws NoSuchElementException{
            if (current == null) {
                throw new NoSuchElementException();
            }
            T item = current.data;
            current = current.next;
            return item;
        }
    }
}
