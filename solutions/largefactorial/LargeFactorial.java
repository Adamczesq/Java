package solutions.largefactorial;

import java.util.ArrayList;
import java.util.Collections;

public class LargeFactorial {
    public static String calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Podaj dodatnią liczbę!");
        }
        if (n == 0) {
            return "1";
        }

        ArrayList<Integer> resultDigits = new ArrayList<>();
        resultDigits.add(1);

        for (int i = 2; i <= n; i++) {
            multiply(resultDigits, i);
        }

        Collections.reverse(resultDigits);
        StringBuilder sb = new StringBuilder();
        for (int digit : resultDigits) {
            sb.append(digit);
        }

        return sb.toString();
    }

    private static void multiply(ArrayList<Integer> largeNumber, int multiplier) {
        int carry = 0;

        for (int i = 0; i < largeNumber.size(); i++) {
            int product = largeNumber.get(i) * multiplier + carry;
            largeNumber.set(i, product % 10);
            carry = product / 10;
        }

        while (carry > 0) {
            largeNumber.add(carry % 10);
            carry /= 10;
        }
    }

    public static void main(String[] args) {
        int number = 5;
        try {
            String result = calculate(number);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
