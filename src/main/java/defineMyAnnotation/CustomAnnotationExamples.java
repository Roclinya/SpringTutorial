package defineMyAnnotation;

//@CustomAnnotation(count = 123, name = "John", tags = { "Java","Examples","Annotation" }) 
//@CustomAnnotation(tags = { "Java","Examples","Annotation" }) //CustomAnnotation有設定default value for count & name
/**
 * @author u-hanlin This Java Annotation example shows what Java Annotations
 *         looks like, and where they can be placed in the code.
 */
@CustomAnnotation
public class CustomAnnotationExamples {                    // class declaration

	private String myField = null;                         // member variable

//有些annotation會被設計只用於使用classes或methods上
	@CustomAnnotation                                      // method
	public void doIt(@CustomAnnotation String message) {   // parameter

		@CustomAnnotation
		String localVar = "text";                          // local variable
	}

	@CustomAnnotation
	public CustomAnnotationExamples() {                    //constructor
	};
	
	@CustomAnnotation                                            //static method
	public static void main(@CustomAnnotation String[] args) {   //static method parameter declaration
		
	}
}
