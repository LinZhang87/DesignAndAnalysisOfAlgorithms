/*
 *  Test suite bundles a few unit test cases and run them together. In JUint, 
 *  both @RunWith and @Suite annotation are used to run the suite test. Here
 *  we bundle the test cases for SecondLargest and UnimodalArray together.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	TestUnimodalArray.class  //TestSecondLargest.class ,
})
public class Week1TestSuite {
}