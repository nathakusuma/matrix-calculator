import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static class MatrixOrder {
        int row, col;

        private MatrixOrder(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private enum Operation {
        END_PROGRAM,
        ADDITION,
        SUBTRACTION,
        SCALAR_MULTIPLICATION,
        MULTIPLICATION,
        SCALAR_DIVISION
    }

    private static final Scanner scanner = new Scanner(System.in);

    private static Operation getOperation(boolean isError) {
        if (isError) System.out.println("Id salah!\n");
        String tableFormatter = "| %2s | %-16s |\n";
        String tableRowDivider = "-".repeat(25);
        System.out.println(tableRowDivider);
        System.out.printf(tableFormatter, "Id", "Operasi");
        System.out.println(tableRowDivider);
        System.out.printf(tableFormatter, "0", "Keluar program");
        System.out.printf(tableFormatter, "1", "Penjumlahan");
        System.out.printf(tableFormatter, "2", "Pengurangan");
        System.out.printf(tableFormatter, "3", "Perkalian Skalar");
        System.out.printf(tableFormatter, "4", "Perkalian");
        System.out.printf(tableFormatter, "5", "Pembagian Skalar");
        System.out.println(tableRowDivider);

        byte operationId;
        System.out.print("Id Operasi : ");
        try {
            operationId = scanner.nextByte();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return getOperation(true);
        }
        System.out.print('\n');
        switch (operationId) {
            case 0 -> {
                return Operation.END_PROGRAM;
            }
            case 1 -> {
                return Operation.ADDITION;
            }
            case 2 -> {
                return Operation.SUBTRACTION;
            }
            case 3 -> {
                return Operation.SCALAR_MULTIPLICATION;
            }
            case 4 -> {
                return Operation.MULTIPLICATION;
            }
            case 5 -> {
                return Operation.SCALAR_DIVISION;
            }
            default -> {
                return getOperation(true);
            }
        }
    }

    private static Operation getOperation() {
        return getOperation(false);
    }

    private static MatrixOrder getMatrixOrder(String matrixName, String errorMessage) {
        if (errorMessage != null) System.out.println(errorMessage + "\n");
        else if (!matrixName.isEmpty()) matrixName += " ";
        System.out.printf("[ Ordo Matriks %s]\n", matrixName);
        try {
            System.out.print("Jumlah baris : ");
            int n = scanner.nextInt();
            System.out.print("Jumlah kolom : ");
            int m = scanner.nextInt();
            System.out.print('\n');
            return new MatrixOrder(n, m);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return getMatrixOrder(matrixName, "Masukan salah");
        }
    }

    private static MatrixOrder getMatrixOrder(String matrixName) {
        return getMatrixOrder(matrixName, null);
    }

    private static MatrixOrder getMatrixOrder() {
        return getMatrixOrder("", null);
    }

    private static double[][] getMatrixElements(MatrixOrder order, String matrixName, String errorMessage) {
        if (errorMessage != null) System.out.println(errorMessage + "\n");
        else if (!matrixName.isEmpty()) matrixName += " ";
        int n = order.row;
        int m = order.col;
        System.out.printf("Masukkan matriks %s(pisahkan kolom dengan spasi dan baris dengan enter)\n", matrixName);
        double[][] matrix = new double[n][m];
        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return getMatrixElements(order, matrixName, "Masukan salah");
        }
        System.out.print('\n');
        return matrix;
    }

    private static double[][] getMatrixElements(MatrixOrder order, String matrixName) {
        return getMatrixElements(order, matrixName, null);
    }

    private static double[][] getMatrixElements(MatrixOrder order) {
        return getMatrixElements(order, "", null);
    }

    private static void printMatrix(double[][] matrix) {
        System.out.println("Matriks hasil: ");
        for (double[] rows : matrix) {
            for (double element : rows) {
                if (element % 1 == 0) System.out.print((int) element + " ");
                else if ((element * 10) % 1 == 0) System.out.printf("%.1f ", element);
                else System.out.printf("%.2f ", element);
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    private static double[][] addOrSubtract(String errorMessage, boolean isSubtract) {
        if (errorMessage != null) System.out.println(errorMessage + "\n");
        MatrixOrder order1 = getMatrixOrder("1");
        MatrixOrder order2 = getMatrixOrder("2");
        int n = order1.row;
        int m = order1.col;
        if (n != order2.row || m != order2.col) {
            return addOrSubtract("Ordo kedua matriks harus sama!", isSubtract);
        }
        double[][] a = getMatrixElements(order1, "1");
        double[][] b = getMatrixElements(order2, "2");
        if (isSubtract) for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] -= b[i][j];
            }
        }
        else for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] += b[i][j];
            }
        }
        return a;
    }

    private static double[][] add() {
        return addOrSubtract(null, false);
    }

    private static double[][] subtract() {
        return addOrSubtract(null, true);
    }

    private static double[][] scalarMultiplyOrDivide(boolean isScalarDivide, String errorMessage) {
        if (errorMessage != null) System.out.println(errorMessage + "\n");
        System.out.print("Masukkan skala : ");
        double scale;
        try {
            scale = scanner.nextDouble();
            if (isScalarDivide) {
                if(scale == 0) return scalarMultiplyOrDivide(true, "Tidak bisa membagi dengan 0");
                scale = 1 / scale;
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return scalarMultiplyOrDivide(isScalarDivide, "Masukan salah");
        }
        System.out.print('\n');
        MatrixOrder order = getMatrixOrder();
        int n = order.row;
        int m = order.col;
        double[][] matrix = getMatrixElements(order);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] *= scale;
            }
        }
        return matrix;
    }

    private static double[][] scalarMultiply() {
        return scalarMultiplyOrDivide(false, null);
    }

    private static double[][] scalarDivide() {
        return scalarMultiplyOrDivide(true, null);
    }

    private static double[][] multiply(String errorMessage) {
        if (errorMessage != null) System.out.println(errorMessage + "\n");
        int n, m, p;
        double[][] a, b;
        MatrixOrder order1 = getMatrixOrder("1");
        MatrixOrder order2 = getMatrixOrder("2");
        n = order1.row;
        m = order1.col;
        if (m != order2.row) {
            return multiply("Jumlah kolom matriks 1 harus sama dengan jumlah baris matriks 2!");
        }
        p = order2.col;
        a = getMatrixElements(order1, "1");
        b = getMatrixElements(order2, "2");
        double[][] c = new double[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                c[i][j] = 0;
                for (int k = 0; k < m; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    private static double[][] multiply() {
        return multiply(null);
    }

    public static void main(String[] args) {
        System.out.println(">> KALKULATOR MATRIKS <<\n");
        infLoop:
        while (true) {
            Operation op = getOperation();
            switch (op) {
                case END_PROGRAM -> {
                    System.out.println(">> PROGRAM SELESAI <<");
                    break infLoop;
                }
                case ADDITION -> printMatrix(add());
                case SUBTRACTION -> printMatrix(subtract());
                case SCALAR_MULTIPLICATION -> printMatrix(scalarMultiply());
                case MULTIPLICATION -> printMatrix(multiply());
                case SCALAR_DIVISION -> printMatrix(scalarDivide());
            }
        }
        scanner.close();
    }
}