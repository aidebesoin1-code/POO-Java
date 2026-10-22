public class TestLivraison {
    public static void main(String[] args) {
        // Création d'une livraison standard
        LivraisonStandard LS1 = new LivraisonStandard(1, "Paris", 10, 5);
        LS1.afficher();
        System.out.println("--------------------------------------------");

        // Création d'une livraison express
        LivraisonExpress LE1 = new LivraisonExpress(2, "Lyon", 5, 10, 15);
        LE1.afficher();
        System.out.println("--------------------------------------------");

        Livraison L10 = new Livraison(3, "Marseille", 8, 7);
        L10.afficher();
    }
}