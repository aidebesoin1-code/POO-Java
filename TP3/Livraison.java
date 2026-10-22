public class Livraison {
    // attributs
    private int numero;
    private String destination;
    private int poids;
    private int prixBase;

    // constructeur
    public Livraison(int numero, String destination, int poids, int prixBase) {
        this.numero = numero;
        this.destination = destination;
        this.poids = poids;
        this.prixBase = prixBase;
    }

    // getters
    public int getNumero() {
        return numero;
    }

    public String getDestination() {
        return destination;
    }

    public int getPoids() {
        return poids;
    }

    public int getPrixBase() {
        return prixBase;
    }

    // setters
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setPrixBase(int prixBase) {
        this.prixBase = prixBase;
    }

    // méthode afficher()
    public void afficher() {
        System.out.println("Livraison - Numéro: " + numero
                + ", Destination: " + destination
                + ", Poids: " + poids + "kg, Prix Base: " + prixBase + "€");
    }

    // méthode calculerPrix() : prix de base multiplié par le poids
    public int calculerPrix() {
        return prixBase * poids;
    }
}