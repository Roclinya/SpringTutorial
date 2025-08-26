package aopUtil;

public class aopMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-------processing-------");

		 String s1 = "java";
		    String s2 = "java";
		    String s3 = new String("java");
		    String s4 = null;

		    System.out.println(s1 == s2);
		    System.out.println(s1 == s3);
		    System.out.println(null == s4);
		    System.out.println(s1.equals(s3));
//		    System.out.println(s4.equals(s3));

	}

}
