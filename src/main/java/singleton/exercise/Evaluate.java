package singleton.exercise;

import java.util.function.Supplier;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Evaluate
{
    @Test
    public void test()
    {
        Object obj = new Object();
        assertTrue(SingletonTester.isSingleton(() -> obj));
        assertFalse(SingletonTester.isSingleton(Object::new));
    }
}

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        return  func.get() == func.get();
    }
}

