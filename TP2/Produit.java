public class Produit {
    private String reference;
    private String nom;
    private int prixUnitaire;

    // Constructeur
    public Produit(String reference, String nom, int prixUnitaire) {
        this.reference = reference;
        this.nom = nom;

        if (prixUnitaire <= 0) {
            throw new IllegalArgumentException(
                "Le prix doit être strictement positif."
            );
        }

        this.prixUnitaire = prixUnitaire;
    }

    // Getter
    public String getReference() {
        return reference;
    }

    // Setter
    public void setReference(String reference) {
        this.reference = reference;
    }

    // Getter
    public String getNom() {
        return nom;
    }

    // Setter
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter
    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    // Setter
    public void setPrixUnitaire(int prixUnitaire) {
        if (prixUnitaire <= 0) {
            throw new IllegalArgumentException(
                "Le prix doit être strictement positif."
            );
        }

        this.prixUnitaire = prixUnitaire;
    }

    // Méthode afficher()
    public void afficher() {
        System.out.println("Référence : " + reference);
        System.out.println("Nom : " + nom);
        System.out.println("Prix unitaire : " + prixUnitaire + " €");
    }
}