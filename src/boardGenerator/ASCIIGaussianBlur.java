package boardGenerator;

import java.util.function.BiFunction;

import static java.lang.Math.*;

/**
 * Class used to convert a graph to an ASCII representation with a Gaussian blur
 */
public class ASCIIGaussianBlur {

    /**
     * The ASCII characters used to represent the graph, in order of brightness
     */
    private static final char[] ASCII = new char[]{
            '@', '&', '%', '#', '(', '/', '+', ',', '.', ' '
    };

    /**
     * The normal distribution function
     */
    private static final BiFunction<Integer, Double, Double> NORM_DIST =
            (x, sDev) -> exp(-pow(x, 2) / (2 * pow(sDev, 2))) / sqrt(2 * PI * pow(sDev, 2));


    /**
     * Converts the given graph to an ASCII representation with a Gaussian blur
     *
     * @param graph  the graph to convert
     * @param width  the width of the ASCII representation
     * @param height the height of the ASCII representation
     * @param sDev   the standard deviation of the Gaussian blur
     * @return the ASCII representation
     */
    public static String graphToASCII(Graph graph, int width, int height, double sDev) {
        double[] kernel = getKernel(sDev);

        // Scale the printing area by the size of the kernel (i.e. increase the resolution),
        // so it is reduced back to the desired size after convolution
        int scale = 6 * (int) (ceil(sDev)) + 1;
        double[][] colOrdered = new double[width * scale][height * scale];

        // Fill pixel array with 1 if the pixel is part of the graph, 0 otherwise
        for (int i = 0; i < width * scale; i++) {
            for (int j = 0; j < height * scale; j++) {
                double x = (double) i / scale - width / 2d;
                double y = (double) -j / scale + height / 2d;

                colOrdered[i][j] = (graph.relation(x, y)) ? 1 : 0;
            }
        }

        // First convolve the graph with the kernel in the x direction
        double[][] rowOrdered = MatrixUtil.transpose(
                MatrixUtil.convolve2D(colOrdered, kernel)
        );

        // Then convolve the result with the kernel in the y direction
        // This has the same effect as convolving the graph with the 2D kernel
        double[][] gaussValues = MatrixUtil.convolve2D(rowOrdered, kernel);

        char[][] charArray = new char[height][width];

        // Convert the values to ASCII characters
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int iSample = (int) ((i + 0.5) * scale);
                int jSample = (int) ((j + 0.5) * scale);

                double val = gaussValues[iSample][jSample];
                charArray[i][j] = valueToASCII(val, sDev);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        // Convert the char array to a string
        for (char[] line : charArray) {
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    /**
     * Returns the ASCII character corresponding to the given value. The value represents the 'brightness' of the pixel
     * (i.e. it's proximity to a coordinate on the graph).
     *
     * @param val  the value to convert
     * @param sDev the standard deviation of the Gaussian blur
     * @return the ASCII character
     */
    private static char valueToASCII(double val, double sDev) {
        double thresholdFactor = 0.1;

        for (int k = 0; k < ASCII.length - 1; k++) {
            if (val >= thresholdFactor * pow(sDev, -k)) {
                return ASCII[k];
            }
        }
        return ASCII[ASCII.length - 1];
    }

    /**
     * Returns a kernel for a Gaussian blur with the given standard deviation
     *
     * @param sDev the standard deviation of the Gaussian blur
     * @return the kernel
     */
    private static double[] getKernel(double sDev) {
        // 3 standard deviations in each direction provides a good approximation
        double[] kernel = new double[6 * (int) (ceil(sDev)) + 1];

        for (int i = 0; i < kernel.length; i++) {
            int x = i - 3 * (int) (ceil(sDev));
            kernel[i] = NORM_DIST.apply(x, sDev);
        }
        return kernel;
    }
}
