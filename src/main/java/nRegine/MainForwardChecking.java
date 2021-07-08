package nRegine;

import java.util.Scanner;

public class MainForwardChecking {

    static int posizionamentiTentati = 0;

    static int regineSpostate = 0;

    static int N ;

    public MainForwardChecking() {

        N =caricaScacchiera();

        int[] regine = new int[N];

        int[][] dominio = new int[N][N];

        int[][] matrice = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dominio[i][j] = i;
            }
        }
        //stampaSoluzione(dominio);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrice[i][j] = 0;
            }
        }
        risolvi(matrice, dominio, regine, 0);;

        System.out.println("Soluzione trovata");
        stampaSoluzione(matrice);
        System.out.println("numero di tentativi effettuati: " + posizionamentiTentati);
        System.out.println("numero di spostamenti di regine effettuati: " + regineSpostate);
    }

    private static boolean risolvi(int[][] matrice, int[][] dominio, int[] regine, int pos){
        if (pos == N)
            return true;

        for (int i = 0; i < N; i++) {
            if (dominio[i][pos] != -1) {
                calcolaPosizionamentiTentati();
                matrice[i][pos] = 1;
                regine[pos] = i;
                aggiornaDominio(dominio, regine, pos);

                if (risolvi(matrice, dominio, regine, pos + 1)) {
                    return true;
                }
            }
        }
        calcolaRegineSpostate();
        azzeraMatrice(matrice);
        resettaMatrice(matrice, regine, pos - 1);

        azzeraDominio(dominio);
        resettaDominio(dominio, regine, pos - 1);

        return false;
    }

    private static void azzeraMatrice(int[][] matrice) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrice[i][j] = 0;
            }
        }
    }

    private static void resettaMatrice(int[][] matrice, int[] regine, int colonna) {
        for (int i = 0; i < colonna; i++) {
            matrice[regine[i]][i] = 1;
        }
    }

    private static void azzeraDominio(int[][] dominio) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dominio[i][j] = i;
            }
        }
    }

    private static void resettaDominio(int[][] dominio, int[] regine, int colonna) {
        for (int i = 0; i < colonna; i++) {
            aggiornaDominio(dominio, regine, i);
        }
    }

    private static void aggiornaDominio(int[][] dominio, int[] regine, int pos) {

        //controllo riga sinistra
        for (int i = 0; i < pos; i++) {
            dominio[regine[pos]][i] = -1;
        }

        //controllo riga destra
        for (int i = pos + 1; i < N; i++) {
            dominio[regine[pos]][i] = -1;
        }

        //controllo colonna superiore
        for (int i = 0; i < regine[pos]; i++) {
            dominio[i][pos] = -1;
        }

        //controllo colonna inferiore
        for (int i = regine[pos] + 1; i < N; i++) {
            dominio[i][pos] = -1;
        }

        //controllo diagonale superiore sinistra
        for (int i = regine[pos], j = pos; i >= 0 && j >= 0; i--, j--) {
            dominio[i][j] = -1;
        }

        //controllo diagonale inferiore sinistra
        for (int i = regine[pos], j = pos; i < N && j >= 0; i++, j--) {
            dominio[i][j] = -1;
        }

        //controllo diagonale inferiore destra
        for (int i = regine[pos], j = pos; i < N && j < N; i++, j++) {
            dominio[i][j] = -1;
        }

        //controllo diagonale superiore destra
        for (int i = regine[pos], j = pos; i >= 0 && j < N; i--, j++) {
            dominio[i][j] = -1;
        }

    }

    private static void stampaSoluzione(int[][] matrice) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void calcolaPosizionamentiTentati(){
        posizionamentiTentati += 1;
    }

    private static void calcolaRegineSpostate(){
        regineSpostate += 1;
    }

    private static int caricaScacchiera(){
        System.out.println("Inserisci la dimensione della scacchiera");
        return new Scanner(System.in).nextInt();
    }
}
