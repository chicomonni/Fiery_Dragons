package boardGenerator;

import java.util.Arrays;

import static java.lang.Math.ceil;

public class MatrixUtil {
    public static double[][] transpose(double[][] arr) {
        double[][] res = new double[arr[0].length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res[j][i] = arr[i][j];
            }
        }
        return res;
    }

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

    public static double[][] convolve2D(double[][] arr, double[] kernel) {
        double[][] res = new double[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            res[i] = convolve1D(arr[i], kernel);
        }
        return res;
    }
}