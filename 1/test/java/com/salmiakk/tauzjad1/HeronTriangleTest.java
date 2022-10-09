package com.salmiakk.tauzjad1;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeronTriangleTest {
    private HeronTriangle heronTriangle;

    @BeforeEach
    public void setUp(){
        heronTriangle = new HeronTriangle();
        System.out.println("Before!");
    }
    @AfterEach
    public void tearDown(){
        heronTriangle = null;
        System.out.println("After!");
    }

    @Test
    public void ShouldThrowExceptionNegativeNumber(){
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(1,1,-1);
        });
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(-1,1,1);
        });
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(1,-1,1);
        });
    }
    @Test
    public void ShouldThrowExceptionNoTriangle(){
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(3,5,8);
        });
    }
    @Test
    public void ShouldThrowExceptionZero(){
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(0,5,8);
        });
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(1,0,8);
        });
        assertThrows(IllegalArgumentException.class,() ->
        {
            heronTriangle.calculate(4,5,0);
        });
    }

    @Test
    public void testHeronTriangle1(){
        double result = heronTriangle.calculate(3,4, 5);
        assertEquals(6, result);
    }
    @Test
    public void testHeronTriangle2(){
        double result = heronTriangle.calculate(5,3, 4);
        assertEquals(6, result);
    }
    @Test
    public void testHeronTriangle3(){
        double result = heronTriangle.calculate(4,5, 3);
        assertEquals(6, result);
    }
    @Test
    public void testHeronTriangle4(){
        double result = heronTriangle.calculate(Double.MAX_VALUE,Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals(Double.POSITIVE_INFINITY, result);
    }
    @Test
    public void testHeronTriangle5(){
        double result = heronTriangle.calculate(Double.MIN_VALUE,Double.MIN_VALUE, Double.MIN_VALUE);
        assertEquals(0, result);
    }
    @Test
    public void testHeronTriangle6(){
        double result = heronTriangle.calculate(1.9,0.5,1.5);
        assertEquals(0.2522275758120036, result);
    }

}
