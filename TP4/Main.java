public class Main {

    public static void main(String[] args) {

        CompteCourant compte1 = new CompteCourant(
                1001, "Pape Ibrahima MBAYE", 50000, 20000
        );

        compte1.deposer(10000);
        compte1.retirer(70000);

        System.out.println("COMPTE COURANT");
        System.out.println(compte1.afficherInformations());

        System.out.println("==========================================");

        // Création d'un compte épargne
        CompteEpargne compte2 = new CompteEpargne(
                2001, "Adja Charlotte DIA", 100000, 5
        );

        compte2.deposer(20000);
        compte2.retirer(30000);

        System.out.println("COMPTE EPARGNE");
        System.out.println(compte2.afficherInformations());

        System.out.println("Interets : "+ compte2.calculerInterets() + " FCFA");
    }
}