import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestSecondLargest { 
   protected int[] arr0 = {1, 2};
   
   protected void setUp()
   {
	   
   }

   @Test
   public void testFindSecondMax() {
      assertEquals(1,SecondLargest.FindSecondMax(2, arr0));
   }
}