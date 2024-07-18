package com.hibernate.dao.test;


import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;

public class JUnitAppTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }

    @Test
    public void getMessageTest(){
       // JUnitApp jUnitApp = new JUnitApp();
      //  assertEquals("hello anhvi", jUnitApp.getMessage("anhvi"));

    }

    @Test
    public void getNameTest(){
       // assertEquals("hello anhvi", JUnitApp.getName("anhvi"));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");
    }

}
