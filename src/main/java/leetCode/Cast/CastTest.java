package leetCode.Cast;

import defineAnnotation.Foo;

public class CastTest {

    interface Foointerface {}
    class Alpha implements Foointerface{}
    class Beta extends Alpha{}

    class Delta extends Beta{
        public void execute(String[] args) {
            Beta x = new Beta();
            // insert here
            Alpha a = x;                     // (1) ✅ 合法：Beta 是 Alpha 的子類，可以隱式向上轉型
            Foointerface f1 = (Alpha) x;      // (2) ✅ 合法：x 已經是 Beta，而 Beta 繼承 Alpha，Alpha 實作 Foointerface，允許轉型
            Foointerface f2 = (Delta) x;      // (3) ❌ ClassCastException：Beta 不是 Delta，強制轉型失敗
            Beta b = (Beta) (Alpha) x;        // (4) ✅ 合法：先向上轉型為 Alpha，再向下轉型回 Beta，轉型不變
            System.out.println("execute 方法執行完成");
        }
    }
}
