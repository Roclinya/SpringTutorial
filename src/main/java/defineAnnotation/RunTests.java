package defineAnnotation;

import java.lang.reflect.Method;

//https://docs.oracle.com/javase/1.5.0/docs/guide/language/annotations.html
public class RunTests {

	public static void main(String[] args) throws SecurityException, ClassNotFoundException {
		int passed = 0, failed = 0;
		Foo foo = new Foo();
		for (Method m : foo.getClass().getMethods()) {
			if (m.isAnnotationPresent(TestAnnotation.class)) {
				try {
					m.invoke(null);
					passed++;
				} catch (Throwable ex) {
					System.out.printf("Test %s failed: %s %n", m, ex.getCause());
					failed++;
				}
			}
		}
		System.out.printf("Passed: %d, Failed %d%n", passed, failed);

	}

}
