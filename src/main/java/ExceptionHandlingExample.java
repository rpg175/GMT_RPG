public class ExceptionHandlingExample {
    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 0;
        try {
            int result = divide(dividend, divisor);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Divide by zero exception");
        }
        return dividend / divisor;
    }
}
