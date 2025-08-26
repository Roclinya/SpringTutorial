package leetCode.interfaceDisc;

public class Interface03 {
    public static void main(String[] args) {
        //如何在此執行Summer.execute方法
        // 创建 Summer 实例，并调用 execute 方法
        Summer summer = new Interface03().new Summer();
        summer.execute();
    }
    class Season{
        public void start(){
            System.out.println("Seasons");
        }
    }
    class Summer extends Season{
        public void start(){
            System.out.println("Summer");
        }

        public void execute(){
            Season season = new Summer();
            season.start();
            ((Summer)season).start();
        }
    }
}
