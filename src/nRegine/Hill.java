package nRegine;
import java.util.Scanner;

public class Hill {

    static int N;

    public Hill() {

        int riavvii = 0;

        N =caricaScacchiera();

        int[] regine = new int[N];

        int[][] scacchiera = new int[N][N];

        generaRegine(regine);

        generaSchieramento(regine, scacchiera);
        System.out.println("Generazione randomica delle prime regine!");
        stampaSoluzione(scacchiera);

        boolean risolto = risolvi(scacchiera, regine, calcolaCosto(scacchiera, regine));

        while (!risolto){
            riavvii += 1;
            risolto = risolvi(scacchiera, regine, calcolaCosto(scacchiera, regine));
        }
        System.out.println("soluzione trovata");
        stampaSoluzione(scacchiera);
        System.out.println("Numero totale di riavvii: " + riavvii);

    }

    public static boolean risolvi(int[][] scacchiera, int[] regine, int costo) {

        int[] regineTemporanee;

        regineTemporanee = regine.clone();

        int costoAttuale = calcolaCosto(scacchiera, regine);

        int i = 0;

        costo = calcolaMigliorePosizione(scacchiera, regine, regineTemporanee, costoAttuale);
        System.out.println("costo iniziale: " + costo);

        while (costo != 0 && costo != -1) {
            costo = calcolaMigliorePosizione(scacchiera, regine, regineTemporanee, costo);
            if (costo != -1)
                System.out.println("costo migliore trovato: " + costo);
        }
        if (costo == 0)
            return true;

        System.out.println("massimo locale:");
        stampaSoluzione(scacchiera);
        azzeraMatrice(scacchiera);
        generaRegine(regine);
        generaSchieramento(regine, scacchiera);
        System.out.println("genero nuovo schieramento");
        stampaSoluzione(scacchiera);
        return false;
    }

    public static int calcolaMigliorePosizione(int [][] scacchiera, int [] regine, int [] regineTemporanee, int costoAttuale){
        int costoCorrente;
        int pos = 0;
        int colonna;
        int colonnaMigliore = -1;
        for ( colonna = 0; colonna < N; colonna++) {
            for (int i = 0; i < regine[colonna]; i++) {
                regineTemporanee[colonna] = i;
                azzeraMatrice(scacchiera);
                generaSchieramento(regineTemporanee, scacchiera);
                costoCorrente = calcolaCosto(scacchiera, regineTemporanee);
                if (costoCorrente < costoAttuale){
                    costoAttuale = costoCorrente;
                    pos = i;
                    colonnaMigliore = colonna;
                }
            }
            regineTemporanee = regine.clone();

            for (int j = regine[colonna] + 1; j < N; j++) {
                regineTemporanee[colonna] = j;
                azzeraMatrice(scacchiera);
                generaSchieramento(regineTemporanee, scacchiera);
                costoCorrente = calcolaCosto(scacchiera, regineTemporanee);
                if (costoCorrente < costoAttuale){
                    costoAttuale = costoCorrente;
                    pos = j;
                    colonnaMigliore = colonna;
                }
            }
            regineTemporanee = regine.clone();
        }
        if (colonnaMigliore == -1)
            return -1;
        regine[colonnaMigliore] = pos;
        regineTemporanee = regine.clone();
        azzeraMatrice(scacchiera);
        generaSchieramento(regine, scacchiera);
        return costoAttuale;
    }

    public static void generaRegine(int[] regine) {
        for (int i = 0; i < N; i++) {
            regine[i] = (int) (Math.random() * (N));
        }
    }

    public static int calcolaCosto(int[][] scacchiera, int[] regine) {
        int costo = 0;
        for (int k = 0; k < N; k++) {

            for (int i = k + 1; i < N; i++) {
                if (scacchiera[regine[k]][i] == 1)
                    costo++;
            }

            for (int i = regine[k] + 1, j = k + 1; i < N && j < N; i++, j++) {
                if (scacchiera[i][j] == 1)
                    costo++;
            }

            for (int i = regine[k] - 1, j = k + 1; i >= 0 && j < N; i--, j++) {
                if (scacchiera[i][j] == 1)
                    costo++;
            }
        }
        return costo;
    }

    public static void azzeraMatrice(int[][] scacchiera) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                scacchiera[i][j] = 0;
            }
        }
    }

    public static void generaSchieramento(int[] regine, int[][] scacchiera) {
        for (int i = 0; i < N; i++) {
            scacchiera[regine[i]][i] = 1;
        }
    }

    private static void stampaSoluzione(int[][] scacchiera) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + scacchiera[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int caricaScacchiera(){
        System.out.println("Inserisci la dimensione della scacchiera");
        return new Scanner(System.in).nextInt();
    }

}