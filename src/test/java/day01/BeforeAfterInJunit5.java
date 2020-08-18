package day01;

import org.junit.jupiter.api.*;

public class BeforeAfterInJunit5 {

    // this method rus only once before the entire test
    // same idea as @BeforeClass
    // JUnit5 specific annotation
    @BeforeAll
    public static void setUp(){
        System.out.println("This runs before All");
    }

    // the same idea as @BeforeMethod
    // this is JUnit5 specific annotation
    @BeforeEach
    public void beforeEachTest(){
        System.out.println("This is running before the test");
    }

    @Test
    public void test1(){
        System.out.println("Test1 is running");
    }

// the same idea as @Ignore ==> @Disabled
    @Disabled
    @Test
    public void test2(){
        System.out.println("Test2 is running");
    }
    // the same idea as @AfterMethod
    // this is JUnit5 specific annotation
    @AfterEach
    public void afterEachTest(){
        System.out.println("This is running after each test");
    }


    // this method runs only once after all the tests
    // same idea as @AfterClass annotation
    // JUnit5 specific annotation
    @AfterAll
    public static void tearDown(){
        System.out.println("This runs all the way at the end");
    }




}
