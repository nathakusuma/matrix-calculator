import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int P = scanner.nextInt();
        int[][] A = new int[N][M];
        int[][] B = new int[M][P];

        // MATRIX INPUT
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < P; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        // CALCULATE & OUTPUT
        int[][] C = new int[N][P];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < P; j++) {
                C[i][j] = 0;
                for (int k = 0; k < M; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
                System.out.print(C[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}