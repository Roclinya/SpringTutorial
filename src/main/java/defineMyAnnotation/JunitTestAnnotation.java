package defineMyAnnotation;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.junit.Test;


public class JunitTestAnnotation {
	
	public static Logger log = Logger.getLogger(JunitTestAnnotation.class.getName());
	// 使用我們的自定義注解
	@CustomAnnotation(name = "用户名", count = 12)
	private String username;

	@Test
	public void reflectionTest() {

		// 获取类模板
		Class<CustomAnnotation> c = CustomAnnotation.class;

		// 获取所有字段
		for (Field f : c.getDeclaredFields()) {
			// 判断这个字段是否有CustomAnnotation注解
			if (f.isAnnotationPresent(CustomAnnotation.class)) {
				CustomAnnotation annotation = f.getAnnotation(CustomAnnotation.class);
				System.out.println("描述:[" + f.getName() + "], Tag:[" + annotation.tags() + "], 名字:[" + annotation.name()
						+ "], 計數:[" + annotation.count() + "]");
				log.info("描述:[" + f.getName() + "], Tag:[" + annotation.tags() + "], 名字:[" + annotation.name()
				+ "], 計數:[" + annotation.count() + "]");
			}
		}
	}

}
