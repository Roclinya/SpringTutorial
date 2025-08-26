package factoryMethodPattern;

public class ProduceMain {

	public static void main(String[] args) {

		FrenchFriesFactory friesFactory = new FrenchFriesFactory();
		Product fries = friesFactory.getProduct();
		Product saltFreefries = friesFactory.getProduct("無鹽的");
		fries.describe();// 我是有鹽巴薯條
		saltFreefries.describe();// 我是無鹽的薯條

	}

}
