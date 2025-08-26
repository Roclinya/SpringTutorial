package leetCode;
//靜態區塊探討
	public class StaticBlock {

	    public static void main(String[] args) {
	        new Child();
	        System.out.println("------------------------");
	        new Child();
	        
	    }

	}

	class Parent {
	    
	    private static Foo foo = new Foo("Parent");

	    static {
	        System.out.println("Parent static block...");
	    }

	    {
	        System.out.println("Parent non-static block...");
	    }

	    public Parent() {
	        System.out.println("Parent()...");
	    }

	}

	class Child extends Parent {

	    private static Foo foo = new Foo("Child");
	    
	    static {
	        System.out.println("Child static block...");
	    }

	    {
	        System.out.println("Child non-static block...");
	    }

	    public Child() {
	        System.out.println("Child()...");
	    }

	}

	class Foo {
	    
	   public Foo (String s) {
	       System.out.println("Foo()..." + "called in " + s);
	   }
	}