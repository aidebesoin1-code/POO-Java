public class LivraisonStandard extends Livraison {

    // constructeur
    public LivraisonStandard(int numero, String destination, int poids, int prixBase) {
        super(numero, destination, poids, prixBase); // appelle le constructeur de Livraison
    }

    // méthode afficher()
    @Override
    public void afficher() {
        System.out.println("Livraison Standard - Numéro: " + getNumero()
                + ", Destination: " + getDestination()
                + ", Poids: " + getPoids() + "kg, Prix Base: " + getPrixBase() + "€");
    }

    // méthode calculerPrix() : (prix de base * poids) + 1€
    // Les attributs de Livraison sont private : on passe par les getters
    @Override
    public int calculerPrix() {
        return getPrixBase() * getPoids() + 1;
    }
}