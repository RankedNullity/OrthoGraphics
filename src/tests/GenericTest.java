package tests;

public class GenericTest {
	public static void main(String[] args) {
		
		int i;
		for (i = 0; i < 5; i++) {
			
		}
		System.out.println(i);
		
		System.out.println(-3 % 4);
		
		
	}
	
	
	
	// Testing deepcopy of int arrays.
	public void testArrays() {
		int[] a = new int[] {0,1,2,3,4};
		int[] b = new int[5];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		
		System.out.print(arrayToString(a));
		System.out.print(arrayToString(b));
		
		a[0] = 9;
		a[4] = 20;
		System.out.print(arrayToString(a));
		System.out.print(arrayToString(b));
		
	}
	
	public static String arrayToString(int[] a) {
		String answer = "[" + a[0];
		for(int i = 0; i < a.length; i++) {
			answer += ", " + i;
		}
		answer += "]";
		return answer;
	}
}
