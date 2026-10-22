public class Produit {
    // Attributs
    private String reference;
    private String nom;
    private int prixUnitaire; // 'double' si besoin 

    // Constructeur
    public Produit(String r, String n, int p) {
        this.reference = r;
        this.nom = n;
        if (p < 0) { // optionnel : boucler 3 fois sur le throw
            throw new IllegalArgumentException("Le prix unitaire ne peut pas être négatif.");
        }
        this.prixUnitaire = p;
    }

    // Getters et Setters
    public String getReference() {
        return reference;
    }

    public String getNom() {
        return nom;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        if (prixUnitaire < 0) {
            throw new IllegalArgumentException("Le prix unitaire ne peut pas être négatif.");
        }
        this.prixUnitaire = prixUnitaire;
    }

    // Méthodes
    public void afficherProduit() {
        System.out.println("Référence : " + reference + 
                           "\nNom : " + nom + 
                           "\nPrix unitaire : " + prixUnitaire);
    }

}