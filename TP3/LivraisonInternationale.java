public class LivraisonStandard extends Livraison {

    // constructeur
    public LivraisonStandard(int n, String d, int p, int pr){
        super(n, d, p, pr); // Appelle le constructeur de la classe parente Livraison
    }

    // méthode afficher()
    @Override
    public void afficher() {
        System.out.println("Livraison Standard - Numéro: " + getNumero()
        + ", Destination: " + getDestination() + ", Poids: " + getPoids() + "kg, Prix Base: " + getPrixBase() + "€");
    }

<<<<<<< HEAD
    @Override
    public int calculerPrix() {
        return (prixBase * poids) + 1; // Le prix est calculé en multipliant le prix de base par le poids
    }
=======
    @Override
    public int calculerPrix() {
        return prixBase * poids + 1; // Le prix est calculé en multipliant le prix de base par le poids
    }
>>>>>>> db3bda536f50baf94bf3cb2fa51d350ce9eb7210

}