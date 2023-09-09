import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int P = scanner.nextInt();
        int[][] A = new int[N][M];
        int[][] B = new int[M][P];

        // INPUT MATRIX
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                A[n][m] = scanner.nextInt();
            }
        }
        for (int m = 0; m < M; m++) {
            for (int p = 0; p < P; p++) {
                B[m][p] = scanner.nextInt();
            }
        }

        // CALCULATE & OUTPUT
        int[][] C = new int[N][P];
        for (int n = 0; n < N; n++) {
            for (int p = 0; p < P; p++) {
                C[n][p] = 0;
                for (int m = 0; m < M; m++) {
                    C[n][p] += A[n][m] * B[m][p];
                }
                System.out.print(C[n][p] + " ");
            }
            System.out.print('\n');
        }
    }
}