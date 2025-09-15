package org.example;

import java.util.Arrays;

public class MatrixProcessor {

    public static void main(String[] args) {
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
}
