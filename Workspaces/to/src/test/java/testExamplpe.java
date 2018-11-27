import org.testng.annotations.*;

/**
 * Created by yp-tc-2646 on 17/2/6.
 */
public class testExamplpe {

        @BeforeClass
        public void beforeClass(){
            System.out.println("********  beforeClass");
        }
        @BeforeGroups(groups = "case1group")
        public void beforGroups(){
            System.out.println("********  beforGroups");
        }

        @BeforeSuite
        public void beforeSuite(){
            System.out.println("********  beforeSuite");
        }
        @BeforeMethod
        public void beforeMethod(){
            System.out.println("********  beforeMethod");
        }

        @BeforeTest
        public void beforeTest(){
            System.out.println("********  beforeTest");
        }

        @Test(groups = "case1group")
        public void testcase1(){
            System.out.println("********  testcase1");
        }

        @Test(groups = "case1group")
        public void testcase2(){
            System.out.println("********  testcase2");
        }

        @Test
        public void testcase3(){
            System.out.println("********  testcase3");
        }

        @AfterClass
        public void afterClass(){
            System.out.println("********  AfterClass");
        }

        @AfterGroups(groups = "case1group")
        public void afterGroups(){
            System.out.println("********  AfterGroups");
        }

        @AfterSuite
        public void afterSuite(){
            System.out.println("********  AfterSuite");
        }
        @AfterMethod
        public void afterMethod(){
            System.out.println("********  AfterMethod");
        }

        @AfterTest
        public void afterTest(){
            System.out.println("********  AfterTest");
        }


}
