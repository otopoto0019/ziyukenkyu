package util.math;

public class SquareRoot {
    private Fraction coefficient;
    private Fraction root_number;

    public SquareRoot(Fraction root_number) {
        this.root_number = root_number;
        this.coefficient = new Fraction(1,1);
    }

    public Fraction getCoefficient() {
        return coefficient;
    }

    public Fraction getRoot_number() {
        return root_number;
    }

    public void setCoefficient(Fraction coefficient) {
        this.coefficient = coefficient;
    }

    public void setRoot_number(Fraction root_number) {
        this.root_number = root_number;
    }

    public static SquareRoot multiplySquareRoot (SquareRoot root1, SquareRoot root2) {
        SquareRoot result = new SquareRoot(Fraction.multiplyFraction(root1.getRoot_number(), root2.getRoot_number()));
        result.setCoefficient(Fraction.multiplyFraction(root1.getCoefficient(), root2.getCoefficient()));
        return result;
    }

    public static SquareRoot multiplySquareRoot (SquareRoot root, Fraction fractionCoefficient) {
        SquareRoot result = new SquareRoot(root.getRoot_number());

        result.setCoefficient(Fraction.multiplyFraction(root.getCoefficient(), fractionCoefficient));

        return result;
    }

    public static SquareRoot multiplySquareRoot (Fraction fractionCoefficient, SquareRoot root) {
        SquareRoot result = new SquareRoot(root.getRoot_number());

        result.setCoefficient(Fraction.multiplyFraction(root.getCoefficient(), fractionCoefficient));

        return result;
    }

    public SquareRoot rationalizeSquareRoot() {
        for(int i = 2; i * i <= root_number.getDenominator(); i++) {
            if (root_number.getDenominator() % (i * i) == 0) {
                root_number.setDenominator(root_number.getDenominator() / (i * i));
                coefficient.setDenominator(coefficient.getDenominator() * i);
                i = 1;
            }
        } for(int i = 2; i * i <= root_number.getNumerator(); i++) {
            if (root_number.getNumerator() % (i * i) == 0) {
                root_number.setNumerator(root_number.getNumerator() / (i * i));
                coefficient.setNumerator(coefficient.getNumerator() * i);
                i = 1;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return coefficient + "・" + "√" + root_number;
    }
}
