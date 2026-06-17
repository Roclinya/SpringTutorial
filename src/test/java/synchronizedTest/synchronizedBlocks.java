package synchronizedTest;

import Synchronized.SynchronizedBlocks;
import Synchronized.SynchronizedMethodAndBlock;
import Synchronized.SynchronizedMethods;
import Synchronized.SynchronizedStaticMethodAndBlock;
import com.tutorial.SpringTutorial.Demo1Application;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = Demo1Application.class)
public class synchronizedBlocks {
/* Ref to https://jenkov.com/tutorials/java-concurrency/synchronized.html */
    // 測試前要先去啟動LandingH2 db /Users/u-hanlin/workspace-MyProject/h2  java -jar h2-2.3.232.jar
//    @Disabled
    @Test
    public void givenMultiThread_whenStaticSyncBlock() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000).forEach(count ->
                service.submit(SynchronizedBlocks::performStaticSyncTask)
        );

        service.shutdown();
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, SynchronizedBlocks.getStaticCount());
    }

    @Disabled
    @Test
    public void givenMultiThread_whenStaticSyncMethod() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
                .forEach(count ->
                        service.submit(SynchronizedMethods::syncStaticCalculate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, SynchronizedMethods.staticSum);
    }

//    @Disabled
    @Test
    public void  synchronized_Blocks_in_Non_Static_Methods(){
        SynchronizedMethodAndBlock myClass = new SynchronizedMethodAndBlock();
    ExecutorService executorService = Executors.newCachedThreadPool();
        // single thread execution
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

    // 使用 lambda 表達式來創建 Runnable 任務
    Runnable task1 = () -> myClass.log1("Message 1-1", "Message 1-2");
    Runnable task2 = () -> myClass.log2("Message 2-1", "Message 2-2");

    // 提交任務給執行緒池
    executorService.submit(task1);
    executorService.submit(task2);

    // 關閉執行緒池
    executorService.shutdown();

    //為何以上test執行之後,會看到console的結果可能沒有跑到999最後一筆？
        // 为了解决或缓解这些问题
        //增加线程执行时间(60秒),这样可以确保线程池中的所有任务有足够的时间完成。
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

}
    @Disabled
    @Test
    public void  synchronized_Blocks_in_Static_Methods(){
        SynchronizedStaticMethodAndBlock myClass = new SynchronizedStaticMethodAndBlock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        // single thread execution
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 使用 lambda 表達式來創建 Runnable 任務
        Runnable task1 = () -> myClass.log1("Message 1-1", "Message 1-2");
        Runnable task2 = () -> myClass.log2("Message 2-1", "Message 2-2");

        // 提交任務給執行緒池
        executorService.submit(task1);
        executorService.submit(task2);

        // 關閉執行緒池
        executorService.shutdown();

    }

}
