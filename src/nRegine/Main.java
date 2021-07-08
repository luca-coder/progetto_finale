package nRegine;

import java.util.Scanner;

public class Main {

    private static MainBacktracking MainBacktracking;
    private static MainForwardChecking mainForwardChecking;
    private static Hill hill;

    public static void main(String[] args) {

        System.out.println("Scegli l' algoritmo da utilizzare: |\n 1 Backtracking \n 2 Forward Checking \n 3 Hill climbing");
        int scelta = new Scanner(System.in).nextInt();

        switch (scelta) {
            case 1:
                MainBacktracking = new MainBacktracking();
                break;
            case 2:
                mainForwardChecking = new MainForwardChecking();
                break;
            case 3:
                hill = new Hill();
                break;
            default:
                System.out.println("Scelta non valida");
        }
    }

}
