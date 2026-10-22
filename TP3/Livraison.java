public class Livraison {
    // attributs
    private int numero;
    private String destination;
    private int poids;
    private int prixBase;

    // constructeur
    public Livraison(int n, String d, int p, int pr){
        this.numero = n;
        this.destination = d;
        this.poids = p;
        this.prixBase = pr;
    }

    // getters et setters 
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
        System.out.println("Livraison - Numéro: " + numero + ", Destination: " + destination + ", Poids: " + poids + "kg, Prix Base: " + prixBase + "€");
    }

    // méthode calculerPrix()
    public int calculerPrix() {
        return prixBase * poids; // Le prix est calculé en multipliant le prix de base par le poids
    }

}