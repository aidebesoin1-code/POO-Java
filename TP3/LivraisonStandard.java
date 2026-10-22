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


}