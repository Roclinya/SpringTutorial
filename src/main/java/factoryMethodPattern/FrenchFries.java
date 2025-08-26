package factoryMethodPattern;

//實現薯條
public class FrenchFries implements Product {
	// 預設有鹽巴的
	String state = "有鹽巴";

	// 預設建構子
	public FrenchFries() {
	}

	// 帶狀態的建構子
	public FrenchFries(String state) {
		this.state = state;
	}

	@Override
	public void describe() {
		System.out.println("我是" + state + "薯條");
	}

}
