package com.mahidol.app;

// import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<T> implements List<T> {
    private Object[] array;
    private int length;
    private int allocated;

    public MyList() {
        this.array = new Object[5];
        this.length = 0;
        this.allocated = 5;
    }

    public MyList(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Invalid length");
        }
        this.array = new Object[length];
        this.length = 0;
        this.allocated = length;
    }

    public MyList(T[] array) {
        this.array = (Object[]) array;
        this.length = array.length;
        this.allocated = array.length;
    }

    public MyList(Collection<? extends T> col){
        this.array = col.toArray();
        this.length = col.size();
        this.allocated = col.size();
    }

    private void allocateMore(int c, String type){
        // expect c as a positive int
        if(type.equals("mul")){
            this.allocated = this.allocated * c;
        }
        else if(type.equals("plus")){
            this.allocated = this.allocated + c;
        }
        else{
            throw new IllegalArgumentException();
        }
        
        Object[] temp = new Object[this.allocated];
        System.arraycopy(this.array, 0, temp, 0, this.length);
        this.array = temp;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if(obj instanceof List){
            MyList<T> temp_list = (MyList<T>) obj;
            boolean is_same = true;    
            for (int i = 0; i < this.length; i++) {
                if(!temp_list.get(i).equals(this.get(i))){
                    is_same = false;
                    break;
                }
            }
            return is_same;
        }
        return false;
        
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer(this.length * 3);
        str.append("[ ");
        for(int i = 0; i < this.size(); i++){
            str.append(this.get(i).toString() + " ");
        }
        str.append("]");
        return str.toString();
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) this.array[index];
    }

    @Override
    public boolean add(T value) {
        try {
            if (this.length >= this.allocated) {
                this.allocateMore(2, "mul");
            }
            this.array[this.length] = value;
            this.length++;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] obj_list = new Object[this.length];
        System.arraycopy(this.array, 0, obj_list, 0, this.length);
        return obj_list;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO something that I dont understand 
        return null;
    }

    @Override
    public boolean remove(Object o) {
        int index_o = this.indexOf(o);
        try{
            if(index_o == -1){
                return true;
            }
            System.arraycopy(this.array, index_o + 1, this.array, index_o, this.length - index_o);
            this.length--;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if(c.size() > this.size()){
            return false;
        }
        boolean is_contain = true;
        for (Object o : c) {
            is_contain = contains(o);
            if(!is_contain){
                break;
            }
        }
        return is_contain;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try{
            Iterator<? extends T> s = c.iterator();
            while(s.hasNext()){
                this.add(s.next());
            }
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if(index > this.length){
            throw new IllegalArgumentException("invalid index");
        }
        try{
            int cLen = c.size();
            Object[] cTemp = c.toArray();
            if(this.length + cLen > this.allocated){
                Object[] tempArray = new Object[this.allocated + cLen];
                System.arraycopy(this.array, 0, tempArray, 0, index);
                System.arraycopy(cTemp, 0, tempArray, index, cLen);
                System.arraycopy(this.array, index, tempArray, index + cLen, this.length - index);
                this.array = tempArray;
                this.allocated = this.allocated + cLen;
            }
            else{
                Object[] tempArray = new Object[length - index];
                System.arraycopy(this.array, index, tempArray, 0, this.length - index);
                System.arraycopy(cTemp, 0, this.array, index, cLen);
                System.arraycopy(tempArray, 0, this.array, index + cLen, this.length - index);
            }
            this.length = this.length + cLen;

        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        this.length = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        T temp = (T) this.array[index];
        this.array[index] = (Object) element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        if(index >= this.length || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if(this.allocated >= this.length){
            this.allocateMore(2, "mul");
        }
        System.arraycopy(this.array, index, this.array, index + 1, this.length - index);
        this.array[index] = element;
        this.length++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T temp = (T) this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.length - index);
        this.length--;
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < this.length; i++) {
            if(this.get(i).equals(o)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = this.length; i >= 0; i--) {
            if(this.get(i).equals(o)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }



}
