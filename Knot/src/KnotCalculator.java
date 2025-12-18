import java.util.ArrayList;

public class KnotCalculator {

    public String analyze(KnotGraph graph) {
        int n = graph.getNumArcs();

        // unknot
        if (n <= 1) return "Determinant: 0 (Unknot)\nColorable by: All primes";

        double[][] matrix = buildMatrix(graph);

        // make the minor matrix (n-1)x (n-1)
        double[][] minor = getMinor(matrix);

        // calculate det
        long determinant = Math.round(Math.abs(calculateDeterminant(minor)));

        // find p-colorability
        String colorableBy = findPrimeFactors(determinant);

        return "--- Analysis ---\n" +
               "Knot Determinant: " + determinant + "\n" +
               "P-Colorable for p = " + colorableBy;
    }

    private double[][] buildMatrix(KnotGraph graph) {
        ArrayList<Crossing> crossings = new ArrayList<>(graph.getCrossings());

        int size = graph.getNumArcs();
        double[][] matrix = new double[size][size];

        for (int i = 0; i < crossings.size(); i++) {
            int[] row = crossings.get(i).getEquationRow(size);

            for (int j = 0; j < size; j++) {
                matrix[i][j] = (double) row[j];
            }
        }
        return matrix;
    }

    private double[][] getMinor(double[][] matrix) {
        int size = matrix.length;

        // get rid of rightmost column, bottom most row
        double[][] minor = new double[size - 1][size - 1];

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                minor[i][j] = matrix[i][j];
            }
        }
        return minor;
    }

    private double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        double det = 1.0;

        // make a copy of the original matrix
        double[][] mat = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = matrix[i][j]; 
            }
        }

        for (int i = 0; i < n; i++) {
            // find pivot
            int pivot = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(mat[j][i]) > Math.abs(mat[pivot][i])) {
                    pivot = j;
                }
            }

            // swap rows (if needed)
            double[] temp = mat[i];
            mat[i] = mat[pivot];
            mat[pivot] = temp;

            // if we swapped rows we have to flip the sign of the det
            if (i != pivot) det *= -1;

            // diag = 0 --> det = 0
            if (Math.abs(mat[i][i]) < 1e-9) {
                return 0;
            }

            det *= mat[i][i];

            // eliminate lower rows
            for (int j = i + 1; j < n; j++) {
                double factor = mat[j][i] / mat[i][i];
                for (int k = i; k < n; k++) {
                    mat[j][k] -= factor * mat[i][k];
                }
            }
        }
        return det;
    }

    private String findPrimeFactors(long num) {
        if (num == 0) return "None (Determinant is 0)";
        if (num == 1) return "None (Trivial Knot)";

        ArrayList<Long> primes = new ArrayList<>();
        long n = num;

        // check indiv. for 2
        if (n % 2 == 0) {
            primes.add(2L);
            while (n % 2 == 0) n /= 2;
        }

        // check for odd primes
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                primes.add(i);
                while (n % i == 0) n /= i;
            }
        }

        // if n > 2, it is prime
        if (n > 2) primes.add(n);

        return primes.toString();
    }
}
