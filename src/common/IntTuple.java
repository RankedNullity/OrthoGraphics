package common;

import common.datastructures.concrete.dictionaries.ChainedHashDictionary;

public class IntTuple {
	/*-------------------------Constants-----------------------------------*/
	private static final String DEFAULT_NAMES = "abcdefghijklmnopqrstuvwxyz";
	
	/*---------------------------Fields------------------------------------*/
	private final int[] internal;
	private final int size;
	private ChainedHashDictionary<String, Integer> names;
	
	
	/**
	 * Creates a tuple of length n <= 26 with the default naming scheme.
	 * @param n 
	 */
	public IntTuple(int n) {
		if (n > 26) {
			throw new IllegalArgumentException();
		}
		size = n;
		internal = new int[n];
		names = new ChainedHashDictionary<>();
		for (int i = 0; i < n; i++) {
			names.put(DEFAULT_NAMES.substring(i, i + 1), i);
		}
	}
	
	public IntTuple(int a, int b) {
		this(2);
		set(0, a);
		set(1, b);
	}
	
	
	/**
	 * Returns the size of the tuple.
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Gets the value at the selected index.
	 * @param index
	 * @return
	 */
	public int get(int index) {
		return internal[index];
	}
	
	/**
	 * Gets the value at the selected name.
	 * @param name
	 * @return
	 */
	public int get(String name) {
		if (!names.containsKey(name))
			throw new IllegalArgumentException("Name attribute not found.");
		return internal[names.get(name)];
	}
	
	/**
	 * Sets the value at the index to the value.
	 * @param index
	 * @param value
	 */
	public void set(int index, int value) {
		internal[index] = value;
	}
	
	/**
	 * Sets the value at the name to the value.
	 * @param name
	 * @param value
	 */
	public void set(String name, int value) {
		set(names.get(name), value);
	}
	
}