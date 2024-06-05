package boardgenerator;

import java.util.Arrays;

import static java.lang.Math.ceil;

/**
 * Utility class for matrix operations
 */
public class MatrixUtil {

    /**
     * Transposes the given matrix
     *
     * @param arr the matrix to transpose
     * @return the transposed matrix
     */
    public static double[][] transpose(double[][] arr) {
        double[][] res = new double[arr[0].length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res[j][i] = arr[i][j];
            }
        }
        return res;
    }

    /**
     * Convolves a 1D matrix with the given 1D kernel
     *
     * @param arr    the matrix to convolve
     * @param kernel the 1D kernel to convolve with
     * @return the convolved matrix
     */
    public static double[] convolve1D(double[] arr, double[] kernel) {
        double[] res = new double[arr.length + kernel.length - 1];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < kernel.length; j++) {
                res[i + j] += arr[i] * kernel[j];
            }
        }

        int lower = (kernel.length - 1) / 2;
        int upper = (int) ceil((kernel.length - 1) / 2d);
        return Arrays.copyOfRange(res, lower, res.length - upper);
    }

    /**
     * Convolves the given 2D matrix with the given 1D kernel
     *
     * @param arr    the matrix to convolve
     * @param kernel the 1D kernel to convolve with
     * @return the convolved matrix
     */
    public static double[][] convolve2D(double[][] arr, double[] kernel) {
        double[][] res = new double[arr.length][arr[0].length];

        // Convolve a row at a time
        for (int i = 0; i < arr.length; i++) {
            res[i] = convolve1D(arr[i], kernel);
        }
        return res;
    }
}