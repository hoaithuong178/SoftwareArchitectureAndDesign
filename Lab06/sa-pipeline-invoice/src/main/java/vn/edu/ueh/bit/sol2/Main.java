package vn.edu.ueh.bit.sol2;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String polynomial = "5x^3 - 6x^4 + 6x^9 + 7x + 2";
        int xValue = 3;  // Giá trị x cần tính

        Function<String, ArrayList<Term>> parse = parsePolynomial();
        Function<ArrayList<Term>, ArrayList<Term>> differentiate = differentiatePolynomial();
        Function<ArrayList<Term>, String> stringify = polynomialToString();
        Function<ArrayList<Term>, Integer> evaluateAtX = evaluatePolynomialAt(xValue);

        Function<String, String> pipelineDifferentiate = parse.andThen(differentiate).andThen(stringify);
        Function<String, Integer> pipelineEvaluate = parse.andThen(evaluateAtX);

        String differentiated = pipelineDifferentiate.apply(polynomial);
        int evaluatedValue = pipelineEvaluate.apply(polynomial);

        System.out.println("Polynomial: " + polynomial);
        System.out.println("Differentiated Polynomial: " + differentiated);
        System.out.println("Value at x = " + xValue + ": " + evaluatedValue);
    }

    private static Function<String, ArrayList<Term>> parsePolynomial() {
        return poly -> {
            ArrayList<Term> terms = new ArrayList<>();
            Matcher matcher = Pattern.compile("([-+]?\\d*)x\\^?(\\d*)|([-+]?\\d+)").matcher(poly.replaceAll("\\s+", ""));
            while (matcher.find()) {
                int coefficient, power;
                if (matcher.group(1) != null) { // Matches x terms
                    coefficient = matcher.group(1).isEmpty() ? 1 : Integer.parseInt(matcher.group(1));
                    power = matcher.group(2).isEmpty() ? 1 : Integer.parseInt(matcher.group(2));
                } else { // Matches constant terms
                    coefficient = Integer.parseInt(matcher.group(3));
                    power = 0;
                }
                terms.add(new Term(coefficient, power));
            }
            return terms;
        };
    }


    private static Function<ArrayList<Term>, ArrayList<Term>> differentiatePolynomial() {
        return terms -> {
            ArrayList<Term> differentiatedTerms = new ArrayList<>();
            for (Term term : terms) {
                if (term.power != 0) {
                    differentiatedTerms.add(new Term(term.coefficient * term.power, term.power - 1));
                }
            }
            return differentiatedTerms;
        };
    }

    private static Function<ArrayList<Term>, String> polynomialToString() {
        return terms -> {
            StringBuilder sb = new StringBuilder();
            for (Term term : terms) {
                if (sb.length() > 0 && term.coefficient > 0) sb.append(" + ");
                if (term.coefficient < 0) sb.append(" - ");
                sb.append(Math.abs(term.coefficient));
                if (term.power > 0) {
                    sb.append("x");
                    if (term.power > 1) {
                        sb.append("^").append(term.power);
                    }
                }
            }
            return sb.toString();
        };
    }

    private static Function<ArrayList<Term>, Integer> evaluatePolynomialAt(int x) {
        return terms -> {
            int result = 0;
            for (Term term : terms) {
                result += term.coefficient * Math.pow(x, term.power);
            }
            return result;
        };
    }

    private static class Term {
        int coefficient;
        int power;

        Term(int coefficient, int power) {
            this.coefficient = coefficient;
            this.power = power;
        }
    }
}
