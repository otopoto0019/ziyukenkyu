import util.math.Fraction;
import util.math.SquareRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public class ApproximateCircleAreaCalculator {
    //xは区分間隔,radiumは計算したい円の半径
    static BiFunction<Fraction, Fraction, SquareRoot> calculateHeight = (x, radium) -> new SquareRoot(Fraction.subtractFractions(Fraction.powerFraction(radium,2),Fraction.powerFraction(x,2)));

    //xは区分間隔,radiumは計算したい円の半径
    static BiFunction<Fraction, Fraction, Double> sumTotalArea = (x, radium) -> {

        List<SquareRoot> sectionalSquares = new ArrayList<>();
        Fraction bottom = x.copy();
        double result;

        while (!Fraction.compareFractionSize(bottom, radium)){
            if (Math.abs(multiplySquareRoot(x, calculateHeight.apply(x, radium)).getRoot_number().getDenominator()) >= Math.pow(2, 31) -1 ||
                    Math.abs(multiplySquareRoot(x, calculateHeight.apply(x, radium)).getRoot_number().getNumerator()) >= Math.pow(2, 31) -1) {
                System.out.println("半径が大きすぎるか区分間隔が小さすぎます。");
                break;
            }
            sectionalSquares.add(multiplySquareRoot(x, calculateHeight.apply(bottom, radium)).rationalizeSquareRoot());
            bottom.addFraction(x);
        }
        result = sectionalSquares.stream()
                .map(value -> (Math.sqrt(value.getRoot_number().getNumerator()) / Math.sqrt(value.getRoot_number().getDenominator()))
                        * ((double) value.getCoefficient().getNumerator() / (double) value.getCoefficient().getDenominator()))
                .mapToDouble(Double::doubleValue)
                .sum() * 4;

        return result;
    };
}
