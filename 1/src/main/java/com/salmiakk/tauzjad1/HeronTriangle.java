package com.salmiakk.tauzjad1;

public class HeronTriangle {
    public double a,b,c;

    public double calculate(double a, double b, double c){
        if(a <=0 || b <=0 || c<= 0) throw new IllegalArgumentException();
        double biggest = Math.max(c, (Math.max(a, b)));
        if(biggest < (a+b+c - biggest)) {
            double p = Math.sqrt( ((a+b+c)/2) * ((a+b+c)/2 - a) * ((a+b+c)/2 - b) * ((a+b+c)/2 - c));
            return p;
        } else{
            throw new IllegalArgumentException();
        }
    }
}
