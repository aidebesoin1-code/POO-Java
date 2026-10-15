public class Produit {
    // Attributs
    private String reference;
    private String nom;
    private int prixUnitaire;

    // Constructeur
    public Produit(String r, String n, int p) {
        this.reference = r;
        this.nom = n;

        if (p < 0) {
            throw new IllegalArgumentException(
                "Le prix unitaire ne peut pas être négatif"
            );
        }

        this.prixUnitaire = p;
    }

    // Getter référence
    public String getReference() {
        return reference;
    }

    // Setter référence
    public void setReference(String reference) {
        this.reference = reference;
    }

    // Getter nom
    public String getNom() {
        return nom;
    }

    // Setter nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter prix unitaire
    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    // Setter prix unitaire
    public void setPrixUnitaire(int prixUnitaire) {
        if (prixUnitaire < 0) {
            throw new IllegalArgumentException("Le prix unitaire ne peut pas être négatif");
        }
        this.prixUnitaire = prixUnitaire;
    }
}