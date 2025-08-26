package Synchronized;

import supplierAndConsumerAndFunctionInterface.FunctionExample;

import java.util.HashMap;
import java.util.function.Function;

public class synChronized {

    public synchronized void addData() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() +", x: "+ i);
        }
    }

    public static void main(String[] arg) {

//        run();
        HashMap<String, HashMap<String, String>> cResult = new HashMap<String, HashMap<String, String>>();
        HashMap<String, String> cItems = null;
        cItems = new HashMap<String, String>();
        cResult.put("test", cItems);


				cItems.put("subItemNo", "subItemName");
        System.out.println(cItems);

    }
}
