package com.sw.todaeat;

public class Fraction {
    int numerator; // 분자 필드
    int denominator; // 분모 필드

    public Fraction(){
        this.numerator = 1;
        this.denominator = 1;
    }
    public Fraction(int d){
        this.numerator = 1;
        this.denominator = d;
    }
    public Fraction(int n, int d){
        this.numerator = n;
        this.denominator = d;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    // 분수 덧셈
    public static Fraction addFraction(Fraction frac1, Fraction frac2){
        int nume = (frac1.numerator * frac2.denominator) + (frac2.numerator * frac1.denominator);

        int denomi = (frac1.denominator*frac2.denominator);

        int gcd = gcd(nume, denomi);

        Fraction frac3 = new Fraction(nume / gcd ,denomi / gcd);


        return frac3;
    }

    // 분수 뺄셈
    public static Fraction subFraction(Fraction frac1, Fraction frac2) {

        int nume = (frac1.numerator * frac2.denominator) - (frac2.numerator * frac1.denominator);

        int denomi = (frac1.denominator*frac2.denominator);

        int gcd = gcd(nume, denomi);

        Fraction frac3 = new Fraction(nume / gcd ,denomi / gcd);

        return frac3;

    }

    public static int gcd(int a, int b ){  // 분자 분모 최대공약수 구하기
        while(b != 0){
            int r = a % b;

            a = b;
            b = r;
        }
        return a;
    }
}



