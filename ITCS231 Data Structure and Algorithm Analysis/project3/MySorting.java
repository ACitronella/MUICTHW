/*
* ID: 6388003
* FullName: Phuriwat Angkoondittaphong
* Section: 1
* */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;



public class MySorting {

    
    static Random r = new Random(System.currentTimeMillis());
    
    static <T> void insertionSort(T[] array, Comparator<T> cc){
        int j;
        T tmp;
        for (int i = 1; i < array.length; i++) {
            j = i - 1;
            tmp = array[i];
            while(cc.compare(array[j], array[i]) > 0 && j > 0){
                array[j + 1] = array[j];
                j--;
            }   
            array[j] = tmp;
        }
    }


    static <T> void spiltData(List<T> a, T p, List<T> le, List<T> eq, List<T> ge, Comparator<T> cc){
        
        for(T v: a){
            int compare = cc.compare(v, p);
            if(compare < 0){
                le.add(v);
            }
            else if(compare == 0){
                eq.add(v);
            }
            else if(compare > 0){
                ge.add(v);
            }
        }
    }

    static <T> void quickSort(T[] array, Comparator<T> cc){
        List<T> tmp = new ArrayList<>(List.of(array));
        quickSort(tmp, cc);
        array = tmp.toArray(array);
    }

    static <T> void quickSort(List<T> array, Comparator<T> cc){
        int n = array.size();
        if (n <= 1)
            return;
        
        List<T> le = new ArrayList<>();
        List<T> eq = new ArrayList<>();
        List<T> ge = new ArrayList<>();
        
        // T pivot = array.get(r.nextInt(n));
        T pivot = array.get(0);
        spiltData(array, pivot, le, eq, ge, cc);
        quickSort(le, cc);
        quickSort(ge, cc);
        System.out.println(array);        
        
        array.clear();
        array.addAll(le);   
        array.addAll(eq);
        array.addAll(ge);
    }

    static <T> void mergeData(T[] left, T[] right, T[] array, Comparator<T> cc){
        for(int i = 0, j = 0, k = 0; j < left.length || k < right.length; i++){
            if(j >= left.length){
                array[i] =  right[k];
                k++;
            }
            else if(k >= right.length){
                array[i] =  left[j];
                j++;
            }
            else if(cc.compare(left[j], right[k]) <= 0){
                array[i] = left[j];
                j++;
            }
            else{
                array[i] = right[k];
                k++;
            }
        }
    }

    static <T> void mergeSort(T[] array, Comparator<T> cc){
        int n = array.length;
        if (n <= 1)
            return;
        
        T[] left = Arrays.copyOfRange(array, 0, n/2);
        T[] right = Arrays.copyOfRange(array, n/2, n);
        mergeSort(left, cc);
        mergeSort(right, cc);

        mergeData(left, right, array, cc);
    }

    public static void main(String[] args) {
        Integer[] a = { 6,4,1,8,3,2,7,5};
        // Integer[] b = { 3,2,7,5,6,4,1,8};
        Integer[] b = {  8, 12, 15, 7, 10, 5, 4, 13};
        Integer[] c = { 6,4,7,5,1,8,3,2};

        
    //    System.out.println("insertion sort");
    //    System.out.println(Arrays.toString(a));
    //    insertionSort(a, Integer::compare);
    //    System.out.println(Arrays.toString(a));

       System.out.println("quick sort");
       System.out.println(Arrays.toString(b));
       quickSort(b, Integer::compare);
       System.out.println(Arrays.toString(b));

    //    System.out.println("merge sort");
    //    System.out.println(Arrays.toString(c));
    //    mergeSort(c, Integer::compare);
    //    System.out.println(Arrays.toString(c));

    }

}
