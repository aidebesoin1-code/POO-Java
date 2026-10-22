public class TestLivraison {
    public static void main(String[] args) {
        // Création d'une livraison standard
        LivraisonStandard LS1 = new LivraisonStandard(1, "Paris", 10, 5);
        LS1.afficher();
        int a = LS1.calculerPrix();
        System.out.println(a);
        System.out.println("--------------------------------------------");

        // Création d'une livraison express
        LivraisonExpress LE1 = new LivraisonExpress(2, "Lyon", 5, 10, 15);
        LE1.afficher();
        int b = LE1.calculerPrix();
        System.out.println("b = " + b);
        System.out.println("--------------------------------------------");

        Livraison L10 = new Livraison(3, "Marseille", 8, 7);
        L10.afficher();
        int c = L10.calculerPrix();
        System.out.println(c);
    }
}