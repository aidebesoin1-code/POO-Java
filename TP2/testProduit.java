public class testProduit {
    public static void main(String[] args) {

        try {
            // Création de plusieurs produits
            Produit produit1 = new Produit("P001", "Ordinateur", 800);
            Produit produit2 = new Produit("P002", "Souris", 25);
            Produit produit3 = new Produit("P003", "Clavier", 50);

            // Affichage
            produit1.afficher();
            System.out.println();

            produit2.afficher();
            System.out.println();

            produit3.afficher();

        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}