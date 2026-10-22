import java.util.ArrayList;
// import java.util.Date;

public class Commande {
    private int numero;
    private String date; // String
    private ArrayList<Produit> produits;

    // constructeur
    public Commande(int numero, String date, ArrayList<Produit> produits) {
        this.numero = numero;
        this.date = date;
        if (produits == null) {
            throw new IllegalArgumentException("La liste de produits ne peut pas être nulle.");
        }
        this.produits = produits;
    }



    // mardiyath
    // getters et setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    public void afficherCommande() {
        System.out.println("Numéro de commande : " + numero);
        System.out.println("Date de commande : " + date);
        System.out.println("Produits commandés :");
        for (Produit produit : produits) {
            produit.afficherProduit();
            System.out.println("--------------------");
        }
    }

    public void ajouterProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être nul.");
        }
        this.produits.add(produit);
    }

    public int calculerTotal() {
        int total = 0;
        for (Produit produit : produits) {
            total += produit.getPrixUnitaire();
        }
        System.out.println("Total de la commande : " + total);
        return total;
    }

}