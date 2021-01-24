package com.mahidol.app;

import java.util.Vector;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        // Integer[] int_list = {0, 5, 1};
        Vector<Integer> vec = new Vector<>(16);
        vec.add(0);
        vec.add(5);
        vec.add(1);
        
        List<Integer> list = new MyList<>();
        list.addAll(vec);
        System.out.println(list.toString());

        List<Integer> list2 = new Vector<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(2);
        list.addAll(list2);
        System.out.println(list.toString());
        System.out.println(list.size());
        

    }
}
