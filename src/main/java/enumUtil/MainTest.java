package enumUtil;

public class MainTest {

	public static void main(String[] args) {
		// Enum為靜態class直接呼叫
//		System.out.println(CardType.getEnum("CREDIT"));
		
		CardType type = CardType.getEnum("CREDIT");
		switch (type) {
		case CREDIT:
			// query Credit Card Table
			System.out.println("Credidt Activated!!");
			break;
		case DEBIT:
			// query Debit Card Table
			System.out.println("Debit Activated!!");
			break;
		}
		// ..

	}

}
/*
values(), ordinal() 和 valueOf() 方法
enum 定义的枚举类默认继承了 java.lang.Enum 类，并实现了 java.lang.Serializable 和 java.lang.Comparable 两个接口。
values(), ordinal() 和 valueOf() 方法位于 java.lang.Enum 类中：
values() 返回枚举类中所有的值。
ordinal()方法可以找到每个枚举常量的索引，就像数组索引一样。
valueOf()方法返回指定字符串值的枚举常量。
 */
 