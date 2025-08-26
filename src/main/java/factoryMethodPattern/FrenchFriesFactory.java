package factoryMethodPattern;

public class FrenchFriesFactory implements Factory {

	//反回一般薯條
	@Override
	public Product getProduct() {
		return new FrenchFries();
	}
	//反回我們想要的狀態的薯條..
	public Product getProduct(String state) {
		return new FrenchFries(state);
	}

}
