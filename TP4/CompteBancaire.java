public class CompteBancaire {

    private int Numero;
    private String NomDuTitulaire;
    protected double Solde;

    // Constructeur
    public CompteBancaire(int Numero, String NomDuTitulaire, double Solde) {
        this.Numero = Numero;
        this.NomDuTitulaire = NomDuTitulaire;
        this.Solde = Solde;
    }

    // Getters et setters
    public int getNumero() {
        return Numero;
    }

    public String getNomDuTitulaire() {
        return NomDuTitulaire;
    }

    public void setNomDuTitulaire(String Nouveau_NomDuTitulaire) {
        NomDuTitulaire = Nouveau_NomDuTitulaire;
    }

    public double getSolde() {
        return Solde;
    }

    public void setSolde(double Solde) {
        this.Solde = Solde;
    }

    // Dépôt
    public void deposer(double montant) {
        if (montant > 0) {
            Solde += montant;
        }
    }

    // Affichage des informations
    public String afficherInformations() {
        return "Compte numero " + Numero + ", titulaire : " + NomDuTitulaire+ ", solde : " + Solde;
    }
}
