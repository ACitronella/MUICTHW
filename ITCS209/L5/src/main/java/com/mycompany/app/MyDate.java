package com.mycompany.app;


public class MyDate {
    private int year;
    private int month;
    private int day;
    private int objectNumber;
    
    private static int objectCounter = 0;
    public static String[] strMonths = {
        "January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"
    };
    public static int[] daysInEachMonth = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };// 1   2   3   4   5   6   7   8   9  10  11  12
    // please check leap year before using this

    public MyDate(){
        this(1900, 1, 1);
        // this.year = 1900;
        // this.month = 1;
        // this.day = 1;
    }

    public MyDate(int aYear, int aMonth, int aDay){
        this.year = aYear;
        this.month = aMonth;
        this.day = aDay;
        objectCounter++;
        this.objectNumber = objectCounter;
    }
    public int getObjectNumber() {
        return this.objectNumber;
    }
    public void setDate(int aYear, int aMonth, int aDay){
        if(aDay == 29 && aMonth == 2 && isLeapYear(aYear)){
            throw new IllegalArgumentException(String.format("invalid year: year %d is not leap year", aYear));
        }
        this.year = aYear;
        this.month = aMonth;
        this.day = aDay;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setDay(int day) {
        if(this.month == 2 && day == 29){
            if(!isLeapYear(this.year)){
                day = 28;
            }
        }
        this.day = day;
    }

    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public String toString(){
        return String.format("%02d %s %04d", this.day, strMonths[this.month-1], this.year);
    }

    public MyDate nextDay(){
        // MyDate store = new MyDate(this.year, this.month, this.day);
        if(this.month == 12 && this.day == 31){
            this.day = 1;
            this.month = 1;
            this.year++;
        }
        else if((this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) && (this.day == 30) // those 30 days month
            || (this.month != 2 && this.day == 31) // those 31 days month
            || (this.month == 2 && this.day == 28 && !isLeapYear(this.year) || (this.month == 2 && this.day == 29 && isLeapYear(this.year) )) // february
            ){ 
            this.month++;
            this.day = 1;
        }
        else{
            this.day++;
        }
        return this;
    }

    public MyDate previousDay(){
        // MyDate store = new MyDate(this.year, this.month, this.day);
        if(this.month == 1 && this.day == 1){
            this.day = 31;
            this.month = 12;
            this.year--;
        }
        else if((this.month == 2 || this.month == 4 || this.month == 6 || this.month == 8 || this.month == 9 || this.month == 11) && (this.day == 1)){ 
            this.day = 31;
            this.month--;
        }
        else if((this.month == 5 || this.month == 7  || this.month == 10 || this.month == 12) && (this.day == 1)){
            this.day = 30;
            this.month--;
        }
        else if(this.month == 3 && this.day == 1 && isLeapYear(this.year)){
            this.day = 29;
            this.month--;
        }
        else if(this.month == 3 && this.day == 1 && !isLeapYear(this.year)){
            this.day = 28;
            this.month--;
        }
        else{
            this.day--;
        }
        return this;
    }

    public MyDate nextMonth(){
        if(this.month == 12){
            this.month = 1;
            this.year++;
        }
        else if(this.month == 1 && this.day >= 29){
            if(isLeapYear(this.year)){
                this.day = 29;
            }
            else{
                this.day = 28;
            }
            this.month++;
        }
        else if(this.day == 31 && (this.month == 3 || this.month == 5 || this.month == 8 || this.month == 10)){
            this.day = 30;
            this.month++;
        }
        else{
            this.month++;
        }
        return this;
    }

    public MyDate previousMonth(){
        if(this.month == 1){
            this.month = 12;
            this.year--;
        }
        else if(this.month == 3 && this.day >= 29){
            if(isLeapYear(this.year)){
                this.day = 29;
            }
            else{
                this.day = 28;
            }
            this.month--;
        }
        else if(this.day == 31 && (this.month == 3 || this.month == 5 || this.month == 8 || this.month == 10)){
            this.day = 30;
            this.month--;
        }
        else{
            this.month--;
        }
        return this;
    }

    public MyDate nextYear(){;
        if(this.day == 29 && this.month == 2){
            this.day = 28;
        }
        this.year++;
        return this;
    }

    public MyDate previousYear(){
        if(this.day == 29 && this.month == 2){
            this.day = 28;
        }
        this.year--;
        return this;
    }
    public static boolean isLeapYear(int year){
        if(year % 4 != 0){
            return false;
        }
        if(year % 100 != 0){
            return true;
        }
        if(year % 400 != 0){
            return false;
        }
        return true;
    }

    public static int dateDiff(MyDate begin, MyDate end){
        
        int diff = 0;
        MyDate s = new MyDate(begin.getYear(), begin.getMonth(), begin.getDay());
        while(!s.toString().equals(end.toString())){
            diff++;
            s.nextDay();
        }
        return diff;
    }
}
