package leetCode.interfaceDisc;

abstract class AbsClass implements A{

	//抽象介面AbsClass不一定要實作所有A，因為可以直接擁有A的方法a()
//	@Override
//	public void a() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void b() {
		// TODO Auto-generated method stub
		System.out.println("this is b");
		
	}
	abstract void c();

}
