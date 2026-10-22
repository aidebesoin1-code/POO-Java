

public class testProduit {
    public static void main(String[] args) {
        // Création d'un produit
        Produit produit1 = new Produit("REF001", "Produit A", 100);
        Produit produit2 = new Produit("REF002", "Produit B", 200);

        Commande cmd1 = new Commande(1, "15/10/2026",
                        new java.util.ArrayList<Produit>(java.util.Arrays.asList(produit1, produit2)));
                
        Commande cmd2 = new Commande(2, "15/10/2026",
                        new java.util.ArrayList<Produit>(java.util.Arrays.asList(produit1, produit2)));

        // Affichage du produit
        produit1.afficherProduit();
        
        // Modification du prix unitaire
        produit1.setPrixUnitaire(150);
        
        // Affichage après modification
        System.out.println("\nAprès modification du prix unitaire :");
        produit1.afficherProduit();
        
        // Test de l'exception pour un prix négatif
        try {
            produit1.setPrixUnitaire(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("\nException attrapée : " + e.getMessage());
        }


        cmd1.afficherCommande();
        cmd1.calculerTotal();
        cmd1.ajouterProduit(produit2);
        cmd1.afficherCommande();
        cmd1.calculerTotal();

    }
}