package leetCode;

public class StringEquals {

	public static void main(String[] args) {

		String s1 = "java";

		String s2 = "java";

		String s3 = new String("java");

		String s4 = null;
		String s5 = "va";

		System.out.println(s1 == s2);

		System.out.println(s1 == s3);

		System.out.println(s1.equals(s3));
		
		System.out.println(s5 == s2.substring(2));//記憶體位置不同

//		System.out.println(s4.equals(s3));

	}
}
