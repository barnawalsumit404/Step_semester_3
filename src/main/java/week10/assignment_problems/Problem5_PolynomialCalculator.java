package week10.assignment_problems;

public class Problem5_PolynomialCalculator {
    public static class Node {
        int coefficient;
        int exponent;
        Node next;

        Node(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null || q != null) {
            int coefficient;
            int exponent;

            if (q == null || (p != null && p.exponent > q.exponent)) {
                coefficient = p.coefficient;
                exponent = p.exponent;
                p = p.next;
            } else if (p == null || q.exponent > p.exponent) {
                coefficient = q.coefficient;
                exponent = q.exponent;
                q = q.next;
            } else {
                coefficient = p.coefficient + q.coefficient;
                exponent = p.exponent;
                p = p.next;
                q = q.next;
            }

            if (coefficient != 0) {
                tail.next = new Node(coefficient, exponent);
                tail = tail.next;
            }
        }
        return dummy.next;
    }

    public static String format(Node polynomial) {
        if (polynomial == null) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        while (polynomial != null) {
            appendTerm(result, polynomial.coefficient, polynomial.exponent);
            polynomial = polynomial.next;
        }
        return result.toString();
    }

    private static void appendTerm(StringBuilder result, int coefficient, int exponent) {
        boolean firstTerm = result.length() == 0;
        int absoluteCoefficient = Math.abs(coefficient);

        if (!firstTerm) {
            result.append(coefficient < 0 ? " - " : " + ");
        } else if (coefficient < 0) {
            result.append("-");
        }

        if (exponent == 0) {
            result.append(absoluteCoefficient);
        } else {
            if (absoluteCoefficient != 1) {
                result.append(absoluteCoefficient);
            }
            result.append("x");
            if (exponent != 1) {
                result.append("^").append(exponent);
            }
        }
    }

    public static void main(String[] args) {
        Node p = polynomial(new int[][]{{5, 3}, {4, 2}, {2, 0}});
        Node q = polynomial(new int[][]{{5, 2}, {-3, 1}, {1, 0}});
        System.out.println(format(addPolynomials(p, q)));

        p = polynomial(new int[][]{{3, 2}, {1, 0}});
        q = polynomial(new int[][]{{-3, 2}, {4, 1}});
        System.out.println(format(addPolynomials(p, q)));
    }

    private static Node polynomial(int[][] terms) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;
        for (int[] term : terms) {
            tail.next = new Node(term[0], term[1]);
            tail = tail.next;
        }
        return dummy.next;
    }
}
