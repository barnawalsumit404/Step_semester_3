public class Problem1_BasicDrawingCanvas {
    abstract static class Shape {
        private static int counter = 1000;
        private final String shapeId;

        Shape() {
            counter++;
            this.shapeId = "SHAPE-" + counter;
        }

        public abstract double calculateArea();

        void scale(double factor) {
            if (factor <= 0) {
                throw new IllegalArgumentException("Factor must be positive");
            }
        }

        void scale(double xFactor, double yFactor) {
            scale(xFactor);
            scale(yFactor);
        }

        public String getShapeId() {
            return shapeId;
        }
    }

    static class CircleShape extends Shape {
        private double radius;

        public CircleShape(double radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        void scale(double factor) {
            radius *= factor;
        }

        @Override
        void scale(double xFactor, double yFactor) {
            radius *= xFactor;
            radius *= yFactor;
        }
    }

    static class SquareShape extends Shape {
        private double side;

        public SquareShape(double side) {
            this.side = side;
        }

        @Override
        public double calculateArea() {
            return side * side;
        }

        @Override
        void scale(double factor) {
            side *= factor;
        }

        @Override
        void scale(double xFactor, double yFactor) {
            side *= (xFactor + yFactor) / 2;
        }
    }

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.println(c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
    }
}
