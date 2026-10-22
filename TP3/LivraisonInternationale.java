public class LivraisonInternationale extends Livraison {
    // attribut propre à la livraison internationale
    private int fraisDouane;

    // constructeur
    public LivraisonInternationale(int numero, String destination, int poids, int prixBase, int fraisDouane) {
        super(numero, destination, poids, prixBase);
        this.fraisDouane = fraisDouane;
    }

    // getter et setter
    public int getFraisDouane() {
        return fraisDouane;
    }

    public void setFraisDouane(int fraisDouane) {
        this.fraisDouane = fraisDouane;
    }

    // méthode afficher()
    @Override
    public void afficher() {
        System.out.println("Livraison Internationale - Numéro: " + getNumero()
                + ", Destination: " + getDestination()
                + ", Poids: " + getPoids() + "kg, Prix Base: " + getPrixBase()
                + "€, Frais de douane: " + fraisDouane + "€");
    }

    // méthode calculerPrix() : (prix de base * poids) + frais de douane
    @Override
    public int calculerPrix() {
        return getPrixBase() * getPoids() + fraisDouane;
    }
}