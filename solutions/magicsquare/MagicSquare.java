package solutions.magicsquare;

import java.util.HashSet;
import java.util.Set;

public class MagicSquare {
    public static boolean isMagicSquare(int[][] square) {
        if (square == null || square.length == 0) {
            return false;
        }
        int n = square.length;

        for (int[] row : square) {
            if (row.length != n) {
                return false;
            }
        }

        int size = n * n;
        Set<Integer> seenNumbers = new HashSet<>();

        for (final int[] ints : square) {
            for (int j = 0; j < n; j++) {
                int number = ints[j];
                if (number < 1 || number > size || !seenNumbers.add(number)) {
                    return false;
                }
            }
        }

        long magicSum = (long) n * ((long) n * n + 1) / 2;
        long diag1Sum = 0;
        long diag2Sum = 0;

        for (int i = 0; i < n; i++) {
            diag1Sum += square[i][i];
            diag2Sum += square[i][n - 1 - i];
        }

        if (diag1Sum != magicSum || diag2Sum != magicSum) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            long rowSum = 0;
            long colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += square[i][j];
                colSum += square[j][i];
            }
            if (rowSum != magicSum || colSum != magicSum) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] magicSquare = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        };

        int[][] notMagicSquare = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] squareWithDuplicate = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 2}
        };

        System.out.println("Is the first array a magic square? " + isMagicSquare(magicSquare));
        System.out.println("Is the second array a magic square? " + isMagicSquare(notMagicSquare));
        System.out.println("Is the third array a magic square? " + isMagicSquare(squareWithDuplicate));
    }
}
