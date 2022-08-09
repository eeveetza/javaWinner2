package test;

import static org.junit.Assert.assertTrue;

public class TestUtil{

    public TestUtil( double tolerance ){
        this.tolerance = tolerance;
    }

    private double tolerance;

    public void assertDoubleEquals( double expected, double result ){
        String errorMessage = "Expected " + expected + " but got " + result;
        assertDoubleEquals(errorMessage, expected, result);
    }

    public void assertDoubleEquals( String errorMessage, double expected, double result ){
        if (Double.isNaN( expected)) {
            assertTrue( errorMessage, Double.isNaN( result) );
        } else {
            assertTrue(errorMessage, Math.abs( expected - result) < tolerance);
        }

    }


}