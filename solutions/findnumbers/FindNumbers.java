package solutions.findnumbers;

public class FindNumbers {

    public static int[] findNumbersWithoutZero(int N) {
        for (int A = 1; A < N; A++) {
            int B = N - A;

            if (!String.valueOf(A).contains("0") && !String.valueOf(B).contains("0")) {
                return new int[]{A, B};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int n1 = 69;
        int[] result1 = findNumbersWithoutZero(n1);
        if (result1 != null) {
            System.out.println("A = " + result1[0] + ", B = " + result1[1]);
        } else {
            System.out.println("No solution found.");
        }

        int n2 = 1010100;
        int[] result2 = findNumbersWithoutZero(n2);
        if (result2 != null) {
            System.out.println("A = " + result2[0] + ", B = " + result2[1]);
        } else {
            System.out.println("No solution found.");
        }
    }
}
