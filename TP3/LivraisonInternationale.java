public class LivraisonInternationale extends Livraison {
    private int fraisDouane;

    public LivraisonInternationale(int n, String d, int p, int pr, int f)) {
        super(n, d, p, pr);
        this.fraisDouane = f;
    }

    public int getFraisDouane() {
        return fraisDouane;
    }

    public void setFraisDouane(int fraisDouane) {
        this.fraisDouane = fraisDouane;
    }

    @Override
    public void afficher() {
        System.out.println("Livraison Internationale - Numéro: " + getNumero()
        + ", Destination: " + getDestination() + ", Poids: " + getPoids() + "kg, Prix Base: " + getPrixBase() + "€, frais douane: " + getFraisDouane() + "€");
    }

    @Override
    public int calculerPrix() {
        return (prixBase * poids) + fraisDouane; // Le prix est calculé en multipliant le prix de base par le poids
    }

}