/*
 * Test runner is used for executing the test cases
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Week1TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(Week1TestSuite.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}  	