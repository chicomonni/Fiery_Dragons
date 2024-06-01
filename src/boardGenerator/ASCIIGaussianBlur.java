package boardGenerator;

import java.util.function.BiFunction;

import static java.lang.Math.*;

public class ASCIIGaussianBlur {

    private static final char[] ASCII = new char[]{
            '@', '&', '%', '#', '(', '/', '+', ',', '.', ' '
    };

    private static final BiFunction<Integer, Double, Double> NORM_DIST =
            (x, sDev) -> exp(-pow(x, 2) / (2 * pow(sDev, 2))) / sqrt(2 * PI * pow(sDev, 2));

    public static String graphToASCII(Graph graph, int width, int height, double sDev) {
        double[] kernel = getKernel(sDev);

        int scale = 6 * (int) (ceil(sDev)) + 1;
        double[][] colOrdered = new double[width * scale][height * scale];

        for (int i = 0; i < width * scale; i++) {
            for (int j = 0; j < height * scale; j++) {
                double x = (double) i / scale - width / 2d;
                double y = (double) -j / scale + height / 2d;

                colOrdered[i][j] = (graph.relation(x, y)) ? 1 : 0;
            }
        }

        double[][] rowOrdered = MatrixUtil.transpose(
                MatrixUtil.convolve2D(colOrdered, kernel)
        );
        double[][] gaussValues = MatrixUtil.convolve2D(rowOrdered, kernel);

        char[][] charArray = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int iSample = (int) ((i + 0.5) * scale);
                int jSample = (int) ((j + 0.5) * scale);

                double val = gaussValues[iSample][jSample];
                charArray[i][j] = valueToASCII(val, sDev);
            }
        }

//        StringBuilder stringBuilder = new StringBuilder(
//                "<html><pre style=\"font-family: MxPlus IBM BIOS\">"
//        );

        StringBuilder stringBuilder = new StringBuilder();

        for (char[] line : charArray) {
            stringBuilder.append(line);
//            stringBuilder.append("<br>");
            stringBuilder.append('\n');
        }

//        stringBuilder.append("</pre></html>");
        return stringBuilder.toString();
    }

    private static char valueToASCII(double val, double sDev) {
        for (int k = 0; k < ASCII.length - 1; k++) {
            if (val >= 0.1 * pow(sDev, -k)) {
                return ASCII[k];
            }
        }
        return ASCII[ASCII.length - 1];
    }

    private static double[] getKernel(double sDev) {
        double[] kernel = new double[6 * (int) (ceil(sDev)) + 1];

        for (int i = 0; i < kernel.length; i++) {
            int x = i - 3 * (int) (ceil(sDev));
            kernel[i] = NORM_DIST.apply(x, sDev);
        }
        return kernel;
    }
}
