package com.mahidol.app;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(){
        Integer[] int_list = {0, 5, 1};
        List<Integer> list = new MyList<>(int_list);
        list.add(Integer.valueOf(25));
        Integer[] int_list2 = {0, 5, 1, 25};
        List<Integer> temp = new MyList<>(int_list2);
        assertTrue(list.equals(temp));

    }
}
