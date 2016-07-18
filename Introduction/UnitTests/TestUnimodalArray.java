import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class TestUnimodalArray { 
	
   protected int[] arr0 = {1,2,5,4,3,2,1};
   
   protected void setUp()
   {
	   
   }

   @Test
   public void testfindMax() {
      assertEquals("Testing findMax of UnimodalArray on a small input: ", 4,UnimodalArray.findMax(arr0));
   }
}