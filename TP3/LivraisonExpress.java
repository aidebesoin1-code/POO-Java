public class LivraisonExpress extends Livraison {
    // attribut propre à la livraison express
    private int supplementExpress;

    // constructeur
    public LivraisonExpress(int numero, String destination, int poids, int prixBase, int supplementExpress) {
        super(numero, destination, poids, prixBase);
        this.supplementExpress = supplementExpress;
    }

    // getter et setter
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
                + ", Destination: " + getDestination()
                + ", Poids: " + getPoids() + "kg, Prix Base: " + getPrixBase()
                + "€, Supplément Express: " + supplementExpress + "€");
    }

    // méthode calculerPrix() : (prix de base * poids) + supplément express
    @Override
    public int calculerPrix() {
        return getPrixBase() * getPoids() + supplementExpress;
    }
}