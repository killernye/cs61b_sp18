import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {


    /** Perform a few tests to see if the method isSameNumber is correct. */
    @Test
    public void testIsSameNumber() {
        int a = 127;
        int b = 127;
        String s = a + " is not equal to " + b;
        assertTrue(s, Flik.isSameNumber(a, b));
    }
}
