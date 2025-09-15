package org.example;

import java.util.Arrays;

public class MatrixProcessor {

    public static void main(String[] args) {
        final byte a = 3;
        // All values are within [-42, 42] so that there is no byte overflow when multiplying by 3
        byte[][] B = {
                {1, 2, 3, 4, 5, 6, 7},
                {7, 6, 5, 4, 3, 2, 5},
                {-1, -2, -3, -4, -5, -6, -7},
                {10, 20, 30, 40, -40, -30, -20},
                {5, -5, 10, -10, 15, -15, 20},
                {8, 16, 24, -24, 32, -32, 42}
        };

        validateMatrix(B);

        System.out.println("Initial matrix B:");
        printMatrix(B);

        byte[][] C = multiplyMatrix(B, a);
        System.out.println("\nResulting matrix C = a * B:");
        printMatrix(C);

        int result = calculateSum(C);
        System.out.println("\nSum of the largest elements in even rows "
                + "and the smallest in odd rows: " + result);
    }

    private static byte findMax(byte[] row) {
        byte max = row[0];

        for (byte val : row) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    private static byte findMin(byte[] row) {
        byte min = row[0];

        for (byte val : row) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    private static void printMatrix(byte[][] matrix) {
        for (byte[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static byte[][] multiplyMatrix(byte[][] matrix, byte multiplier) {
        byte[][] result = new byte[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int value = multiplier * matrix[i][j];

                if (value > Byte.MAX_VALUE || value < Byte.MIN_VALUE) {
                    throw new ArithmeticException("Byte overflow during multiplication");
                }

                result[i][j] = (byte) value;
            }
        }
        return result;
    }

    public static int calculateSum(byte[][] matrix) {
        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            byte extremeValue;

            if (i % 2 == 0) {
                extremeValue = findMax(matrix[i]);
            } else {
                extremeValue = findMin(matrix[i]);
            }

            sum += extremeValue;
        }
        return sum;
    }

    public static void validateMatrix(byte[][] matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix is null");
        }
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Matrix is empty");
        }

        int expectedLength = -1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null) {
                throw new NullPointerException("Row " + i + " of the matrix is not initialized (null)");
            }
            if (matrix[i].length == 0) {
                throw new IllegalArgumentException("Row " + i + " of the matrix is empty");
            }
            if (expectedLength == -1) {
                expectedLength = matrix[i].length;
            } else if (matrix[i].length != expectedLength) {
                throw new IllegalArgumentException("Matrix rows have different lengths");
            }
        }
    }
}
