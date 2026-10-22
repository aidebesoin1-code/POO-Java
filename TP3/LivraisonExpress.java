// Une livraison Express possède : numéro, destination, poids, et prixBase
// + avec des méthodes : calculerPrix(), afficher()

public class LivraisonExpress extends Livraison {
    // attributs
    private int supplementExpress;

    // constructeur
    public LivraisonExpress(int n, String d, int p, int pr, int s){
        super(n, d, p, pr);
        this.supplementExpress = s;
    }

    public int getSupplementExpress() {
        return supplementExpress;
    }

    public void setSupplementExpress(int supplementExpress) {
        this.supplementExpress = supplementExpress;
    }

    // méthode afficher()
    @Override
    public void afficher() {
        System.out.println("Livraison Express - Numéro: " + getNumero()
        + ", Destination: " + getDestination() + ", Poids: " + getPoids() + "kg, Prix Base: " + getPrixBase() + "€, Supplement Express: " + getSupplementExpress() + "€");
    }

    @Override
    public int calculerPrix() {
        return (prixBase * poids) + setSupplementExpress; // Le prix est calculé en multipliant le prix de base par le poids
    }

}