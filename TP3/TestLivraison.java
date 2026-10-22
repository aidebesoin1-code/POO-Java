public class TestLivraison {
    public static void main(String[] args) {
        // Livraison standard
        LivraisonStandard ls1 = new LivraisonStandard(1, "Paris", 10, 5);
        ls1.afficher();
        System.out.println("Prix : " + ls1.calculerPrix() + "€");
        System.out.println("--------------------------------------------");

        // Livraison express
        LivraisonExpress le1 = new LivraisonExpress(2, "Lyon", 5, 10, 15);
        le1.afficher();
        System.out.println("Prix : " + le1.calculerPrix() + "€");
        System.out.println("--------------------------------------------");

        // Livraison internationale
        LivraisonInternationale li1 = new LivraisonInternationale(3, "Rabat", 4, 8, 20);
        li1.afficher();
        System.out.println("Prix : " + li1.calculerPrix() + "€");
        System.out.println("--------------------------------------------");

        // Livraison de base
        Livraison l1 = new Livraison(4, "Marseille", 8, 7);
        l1.afficher();
        System.out.println("Prix : " + l1.calculerPrix() + "€");
    }
}