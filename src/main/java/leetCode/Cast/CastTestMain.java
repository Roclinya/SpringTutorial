package leetCode.Cast;

public class CastTestMain {
    public static void main(String[] args) {
        CastTest outer = new CastTest(); // 由於 Delta 是內部類，先建立外部類實例
        CastTest.Delta delta = outer.new Delta(); // 建立 Delta 的實例
        delta.execute(args); // 呼叫 execute 方法
    }
}
