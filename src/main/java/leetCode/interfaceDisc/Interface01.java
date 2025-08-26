package leetCode.interfaceDisc;

public class Interface01 {

     interface Exercise {
         // 在 Java 的 interface 內定義的變數預設是 public, static, final，所以 s 其實等價於
         // public static final String s = "alive";
        String s = "alive";
        //在 interface 內的方法預設是 public abstract，所以 exercise() 其實等價於：
        // public abstract void exercise();
        public void exercise();
    }
    interface Run{
       public void exercise(int x);
    }
    interface RunFast extends Exercise,Run{

      /*    RunFast 內，它重新宣告了這兩個方法
         public void exercise();（來自 Exercise）
         public void exercise(int x);（來自 Run）
        這樣的做法其實是多餘的，因為這兩個方法已經從 Exercise 和 Run 繼承過來了，
        這種重新宣告不會影響行為，只是讓程式碼更明確。*/
         public void exercise();
         public void exercise(int x);
    }
}
/*實作類別
如果有一個類別要實作 RunFast，則必須實作 exercise() 和 exercise(int x) 這兩個方法，例如：
class Athlete implements InterfaceClass.RunFast {
    @Override
    public void exercise() {
        System.out.println("Exercising...");
    }

    @Override
    public void exercise(int x) {
        System.out.println("Running " + x + " meters...");
    }
}*/
