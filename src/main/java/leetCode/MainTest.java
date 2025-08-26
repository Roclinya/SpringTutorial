package leetCode;

import com.tutorial.SpringTutorial.Target;
import org.apache.poi.ss.formula.functions.T;

public class MainTest {
//    public static void main(String[] args) {
//       int i = 4;
//       do{
//           System.out.println(--i);
//       }while (i==0);
//    }

//    class test{
        public static void main(String[] args) {
            System.out.println(new Target().addOne());
        }

        public class Tagret{
            private Integer i=0;
            public int addOne(){return ++i;}
        }
//    }

}
