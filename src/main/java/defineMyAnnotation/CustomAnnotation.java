package defineMyAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)  // 保留到运行时，可通过注解获取 the annotation will be available during runtime
@Documented
@interface CustomAnnotation {
	// add some features to this annotation
	// java annotation properties are also called 'elements'
	// default means default value
	int count() default 999;

	String name() default "Jack";

	String[] tags() default { "Java", "Annotation" };
}
