package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 模擬物件的委派測試範例，使用 Mockito 的 AdditionalAnswers.delegatesTo 方法。
 * 這個測試展示了如何創建一個模擬物件，並將未 stub 的方法委派給一個真實的物件，同時允許對特定方法進行覆寫。
 */
class AdditionalAnswersDelegatesToTest {

    interface SomeInterface {
        String sayHello(String name);

        int add(int a, int b);
    }

    static class RealSomeInterface implements SomeInterface {
        @Override
        public String sayHello(String name) {
            return "Hello, " + name;
        }

        @Override
        public int add(int a, int b) {
            return a + b;
        }
    }

    @Test
    void delegatesTo_shouldForwardUnstubbedMethods_andAllowSelectiveOverride() {
        SomeInterface realObject = new RealSomeInterface();
        SomeInterface wrapper = mock(SomeInterface.class, delegatesTo(realObject));

        // 未 stub 的方法會直接委派到 realObject
        Assertions.assertEquals("Hello, Larry", wrapper.sayHello("Larry"));
        Assertions.assertEquals(5, wrapper.add(2, 3));

        // 仍可對特定方法做 override
        when(wrapper.sayHello("Larry")).thenReturn("Hi, Larry (mocked)");
        Assertions.assertEquals("Hi, Larry (mocked)", wrapper.sayHello("Larry"));

        // 其他未 stub 呼叫仍走真實實作
        Assertions.assertEquals("Hello, Amy", wrapper.sayHello("Amy"));
    }
}
