package common.datastructures.concrete;

import java.util.Iterator;

import common.datastructures.interfaces.IList;

public class ArrayList<T> implements IList<T> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private T[] container;
	private int size; 
	
	public ArrayList() {
		container = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	@Override
	public void add(T item) {
		if(container.length == size) {
			resize();
		}
		container[size++] = item;
	}
	
	/**
	 * Resizes the internal container and copies all contents. 
	 */
	private void resize() {
		T[] newContainer = (T[]) new Object[size * 2];
		for(int i = 0; i < container.length; i++) {
			newContainer[i] = container[i];
		}
		container = newContainer;
	}

	@Override
	public T remove() {
		return container[--size];
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return container[index];
	}

	@Override
	public void set(int index, T item) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		container[index] = item;
	}

	@Override
	public void insert(int index, T item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T delete(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T item = container[index];
		for (int i = index; i < size - 1; i++) {
			container[i] = container[i + 1];
		}
		return item;
	}

	@Override
	public int indexOf(T item) {
		for(int i = 0; i < size; i++) {
			if(container[i] == item) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T other) {
		for(int i = 0; i < size; i++) {
			if(container[i] == other) {
				return true;
			}
		}
		return false; 
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
