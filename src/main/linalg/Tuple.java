package linalg;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class Tuple<T> {
	private T[] internal;
	private Map<String, Integer> names;
	
	/**
	 * Constructs a new tuple of type c, with initial valuses
	 * @param c
	 * @param one
	 * @param two
	 */
	public Tuple(Class<T> c, T one, T two) {
		@SuppressWarnings("unchecked")
		final T[] internal = (T[]) Array.newInstance(c, 2);
		this.internal = internal;
		internal[0] = one;
		internal[1] = two;
		names = new String[]{"a", "b"};
	}
	
	
	/**
	 * 
	 * @param c
	 * @param n
	 * @param names
	 * @param col
	 */
	public Tuple(Class<T> c, int n, String[] names, Collection<T> col) {
		
		@SuppressWarnings("unchecked")
		final T[] internal = (T[]) Array.newInstance(c, n);
		this.internal = internal;
	}
}
