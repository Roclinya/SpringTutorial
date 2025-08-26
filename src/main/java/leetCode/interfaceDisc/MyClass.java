package leetCode.interfaceDisc;

public class MyClass extends AbsClass {



	@Override
	void c() {
		// TODO Auto-generated method stub

	}

//必須實作所有該介面的方法，否則會編譯錯誤  
	// 此處AbsClass impl A 但他沒實作方法a() 因此可以在MyClass實作
	@Override
	public void a() {
		// TODO Auto-generated method stub

	}

}
