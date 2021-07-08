package nRegine;

import java.util.Scanner;

public class MainBacktracking {

    static int posizionamentiTentati = 0;

    static int regineSpostate = 0;

    static int N;

    public MainBacktracking() {

        N = caricaScacchiera();

        int[][] scacchiera = generaMatrice();
        risolvi(scacchiera, 0);

        stampaSoluzione(scacchiera);
        System.out.println("numero di tentativi effettutati: " + posizionamentiTentati);
        System.out.println("numero di spostamenti di regine effettutati: " + regineSpostate);
    }

    private static int[][] generaMatrice() {
        int[][] scacchiera = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                scacchiera[i][j] = 0;
            }
        }
        return scacchiera;
    }

    private static void stampaSoluzione(int[][] scacchiera) {
        System.out.println("\nSoluzione trovata");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + scacchiera[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean controllaVincoli(int[][] scacchiera, int x, int y) {

        for (int i = 0; i < N; i++) {
            if (scacchiera[x][i] == 1)
                return false;
            if (scacchiera[i][y] == 1)
                return false;
        }

        //controllo diagonale superiore sinistra
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (scacchiera[i][j] == 1)
                return false;
        }

        //controllo diagonale inferiore sinistra
        for (int i = x, j = y; i < N && j >= 0; i++, j--) {
            if (scacchiera[i][j] == 1)
                return false;
        }

        //controllo diagonale inferiore destra
        for (int i = x, j = y; i < N && j < N; i++, j++) {
            if (scacchiera[i][j] == 1)
                return false;
        }

        //controllo diagonale superiore destra
        for (int i = x, j = y; i >= 0 && j < N; i--, j++) {
            if (scacchiera[i][j] == 1)
                return false;
        }
        return true;
    }

    private static boolean risolvi(int[][] scacchiera, int colonna) {
        if (colonna == N)
            return true;

        for (int i = 0; i < N; i++) {

            calcolaPosizionamentiTentati();

            if (controllaVincoli(scacchiera, i, colonna)) {
                scacchiera[i][colonna] = 1;

                if (risolvi(scacchiera, colonna + 1))
                    return true;
                calcolaRegineSpostate();
                scacchiera[i][colonna] = 0;
            }
        }
        return false;
    }

    private static void calcolaPosizionamentiTentati() {
        posizionamentiTentati += 1;
    }

    private static void calcolaRegineSpostate() {
        regineSpostate += 1;
    }

    private static int caricaScacchiera() {
        System.out.println("Inserisci la dimensione della scacchiera");
        return new Scanner(System.in).nextInt();
    }
}
