package util.math;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Can't divide by 0");
        }

        this.numerator = numerator;
        this.denominator = denominator;

        reduceFraction();
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Can't divide by 0");
        }

        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    private void reduceFraction() {
        if (numerator < 0 && denominator < 0) {
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }

//        List<Integer> numerator_divisors = findDivisors();
//        List<Integer> denominator_divisors = findDivisors();
//        List<Integer> common_divisors;
          int GCD = findGCD(Math.abs(numerator), Math.abs(denominator));
//
//        common_divisors = numerator_divisors.stream().filter((value1) -> {
//            for (Integer value2: denominator_divisors) {
//                if (value1.equals(value2)) return true;
//            }
//            return false;
//        }).collect(Collectors.toList());
//
//        if (!common_divisors.isEmpty()) {
//             GCD = common_divisors.stream().max(Integer::compare).get();
//        }
        numerator = numerator / GCD;
        denominator = denominator / GCD;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        if (a >= 0) {
            return a;
        } else {
            return a * -1;
        }
    }

    public static Fraction subtractFractions (Fraction frac1, Fraction frac2) {
        Fraction result;
        Fraction fraction1 = frac1.copy();
        Fraction fraction2 = frac2.copy();

        int denominator = fraction1.getDenominator() * fraction2.getDenominator();

        fraction1.setNumerator(fraction1.getNumerator() * fraction2.getDenominator());
        fraction2.setNumerator(fraction2.getNumerator() * fraction1.getDenominator());

        fraction1.setDenominator(denominator);
        fraction2.setDenominator(denominator);

        result = new Fraction(fraction1.getNumerator() - fraction2.getNumerator(), fraction1.getDenominator());

        return result;
    }

    public static Fraction multiplyFraction(Fraction frac1, Fraction frac2) {
        int numerator;
        int denominator;
        Fraction result;
        Fraction fraction1 = frac1.copy();
        Fraction fraction2 = frac2.copy();

        numerator = fraction1.getNumerator() * fraction2.getNumerator();
        denominator = fraction1.getDenominator() * fraction2.getDenominator();

        result = new Fraction(numerator, denominator);

        return result;
    }

    public static Fraction multiplyFraction(Fraction frac, int num) {
        Fraction fraction = frac.copy();

        return new Fraction(fraction.getNumerator() * num, fraction.getDenominator());
    }
    public static Fraction multiplyFraction(int num, Fraction frac) {
        Fraction fraction = frac.copy();

        return new Fraction(fraction.getNumerator() * num, fraction.getDenominator());
    }


    public void addFraction(Fraction frac) {
        Fraction fraction = frac.copy();
        this.numerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
        this.denominator = this.denominator * fraction.denominator;
        this.reduceFraction();
    }

    public static Fraction getReciprocal(Fraction fraction) {
        return new Fraction(fraction.denominator, fraction.numerator);
    }

    public static Fraction powerFraction(Fraction frac, int exponent) {
        Fraction fraction = frac.copy();

        if (exponent < 0 || exponent > 30) {
            throw new RuntimeException("Exponent must be  an integer greater than or equal to 0 and less than or equal to 30");
        }
        return new Fraction((int) Math.pow(fraction.getNumerator(), exponent), (int) Math.pow(fraction.getDenominator(), exponent));
    }

    public static boolean compareFractionSize(Fraction frac1, Fraction frac2) {
        Fraction fraction1 = frac1.copy();
        Fraction fraction2 = frac2.copy();

        int denominator = fraction1.getDenominator() * fraction2.getDenominator();

        fraction1.setNumerator(fraction1.getNumerator() * fraction2.getDenominator());
        fraction2.setNumerator(fraction2.getNumerator() * fraction1.getDenominator());

        fraction1.setDenominator(denominator);
        fraction2.setDenominator(denominator);

        return fraction1.getNumerator() >= fraction2.getNumerator();
    }

    public double test() {
        return (double) numerator / denominator;
    }

    public int getIntegerPart () {
        return numerator / denominator;
    }

    @Override
    public String toString() {
        return "(" + numerator + " / " + denominator + ")";
    }

    public Fraction copy() {
        return new Fraction(this.numerator, this.denominator);
    }
}
