package com.mycompany.app;

public class EggStop {
    public static void main( String[] args ){
        elim(false, false);
        elim(true, false);
        elim(true, true);
    }

    public static void elim(boolean line, boolean stop){
        System.out.println("  _______ ");
        System.out.println(" /       \\");
        System.out.println("/         \\");
        if(stop){System.out.println("|   STOP   |");}
        System.out.println("\\         /");
        System.out.println(" \\_______/");
        if(line){System.out.println("+--------+");}
    }
}
