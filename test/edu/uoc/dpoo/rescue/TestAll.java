package edu.uoc.dpoo.rescue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    // Tests for PR1
    edu.uoc.dpoo.rescue.PR1_Ex2_Test.class,
    edu.uoc.dpoo.rescue.PR1_Ex3_Test.class,
    edu.uoc.dpoo.rescue.PR1_Ex4_Test.class,
    // Tests for PR2
    edu.uoc.dpoo.rescue.PR2_Ex1_Test.class,
    edu.uoc.dpoo.rescue.PR2_Ex2_1_Test.class,
    edu.uoc.dpoo.rescue.PR2_Ex2_2_Test.class,
    edu.uoc.dpoo.rescue.PR2_Ex2_3_Test.class,    
    edu.uoc.dpoo.rescue.PR2_Ex3_1_Test.class,
    edu.uoc.dpoo.rescue.PR2_Ex3_2_Test.class,    
    edu.uoc.dpoo.rescue.PR2_Ex4_Test.class,    
    })

public class TestAll {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
