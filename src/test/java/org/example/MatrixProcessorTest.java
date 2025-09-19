package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixProcessorTest {

    @Test
    void validateNull() {
        byte[][] matrix = null;

        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                MatrixProcessor.validateMatrix(matrix));
        assertEquals("Matrix is null", ex.getMessage());
    }

    @Test
    void validateEmpty() {
        byte[][] matrix = new byte[0][0];

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                MatrixProcessor.validateMatrix(matrix));
        assertEquals("Matrix is empty", ex.getMessage());
    }

    @Test
    void validateNullRow() {
        byte[][] matrix = {
                {1, 2},
                null
        };

        NullPointerException ex = assertThrows(NullPointerException.class, () ->
                MatrixProcessor.validateMatrix(matrix));
        assertEquals("Row 1 of the matrix is not initialized (null)", ex.getMessage());
    }

    @Test
    void validateEmptyRow() {
        byte[][] matrix = {
                {1, 2},
                {}
        };

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                MatrixProcessor.validateMatrix(matrix));
        assertEquals("Row 1 of the matrix is empty", ex.getMessage());
    }

    @Test
    void validateDiffRowLengths() {
        byte[][] matrix = {
                {1, 2, 3},
                {4, 5}
        };

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                MatrixProcessor.validateMatrix(matrix));
        assertEquals("Matrix rows have different lengths", ex.getMessage());
    }

    @Test
    void multiplyBasic() {
        byte multiplier = 3;
        byte[][] matrix = {
                {1, 2},
                {3, 4}
        };
        byte[][] expected = {
                {3, 6},
                {9, 12}
        };
        byte[][] result = MatrixProcessor.multiplyMatrix(matrix, multiplier);

        assertArrayEquals(expected, result);
    }

    @Test
    void multiplyZero() {
        byte[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };
        byte[][] expected = {
                {0, 0, 0},
                {0, 0, 0}
        };
        byte[][] result = MatrixProcessor.multiplyMatrix(matrix, (byte)0);

        assertArrayEquals(expected, result);
    }

    @Test
    void multiplyMinMax() {
        byte multiplier = 1;
        byte[][] matrix = {
                {Byte.MAX_VALUE, Byte.MIN_VALUE}
        };
        byte[][] expected = {
                {Byte.MAX_VALUE, Byte.MIN_VALUE}
        };

        assertArrayEquals(expected, MatrixProcessor.multiplyMatrix(matrix, multiplier));
    }

    @Test
    void multiplyNegNoOverflow() {
        byte multiplier = -1;
        byte[][] matrix = {
                {10, 20}
        };
        byte[][] expected = {
                {-10, -20}
        };

        assertArrayEquals(expected, MatrixProcessor.multiplyMatrix(matrix, multiplier));
    }

    @Test
    void multiplyOverflow() {
        byte multiplier = 3; // 50 * 3 = 150 > Byte.MAX_VALUE (127)
        byte[][] matrix = {
                {50, 2}
        };

        ArithmeticException ex = assertThrows(ArithmeticException.class, () ->
                MatrixProcessor.multiplyMatrix(matrix, multiplier));
        assertEquals("Byte overflow during multiplication", ex.getMessage());
    }

    @Test
    void multiplyNegOverflow() {
        byte multiplier = -3; // -150 < Byte.MIN_VALUE (-128)
        byte[][] matrix = {
                {50}
        };

        ArithmeticException ex = assertThrows(ArithmeticException.class, () ->
                MatrixProcessor.multiplyMatrix(matrix, multiplier));
        assertEquals("Byte overflow during multiplication", ex.getMessage());
    }

    @Test
    void sumBasic() {
        byte[][] matrix = {
                {1, 5, 3},    // max = 5
                {7, 6, 2},    // min = 2
                {10, 20, 15}, // max = 20
                {-1, -5, -3}  // min = -5
        };
        int expectedSum = 5 + 2 + 20 + (-5);

        assertEquals(expectedSum, MatrixProcessor.calculateSum(matrix));
    }

    @Test
    void sumSingleEven() {
        byte[][] matrix = {
                {10, 20, 30}
        };

        assertEquals(30, MatrixProcessor.calculateSum(matrix));
    }

    @Test
    void sumSingleOdd() {
        byte[][] matrix = {
                {10, 20, 30},
                {5, 1, 3}
        };
        int expectedSum = 30 + 1;

        assertEquals(expectedSum, MatrixProcessor.calculateSum(matrix));
    }

    @Test
    void sumNegative() {
        byte[][] matrix = {
                {-10, -20, -5},
                {-1, -50, -3},
                {0, 1, 2}
        };
        int expectedSum = (-5) + (-50) + 2;

        assertEquals(expectedSum, MatrixProcessor.calculateSum(matrix));
    }

    @Test
    void sumOneEven() {
        byte[][] matrix = {
                {42}
        };

        assertEquals(42, MatrixProcessor.calculateSum(matrix));
    }

    @Test
    void sumOneOdd() {
        byte[][] matrix = {
                {10},
                {-42}
        };

        assertEquals(10 + (-42), MatrixProcessor.calculateSum(matrix));
    }
}
