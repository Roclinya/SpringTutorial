package leetCode.interfaceDisc;

public class Interface02 {
    public static void main(String[] args) {
        System.out.println(new TestA() {
            @Override
            public String toStringCall() {
                return "test";
            }
        });
    }

    interface TestA{ String toStringCall();}
}
