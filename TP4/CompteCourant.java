public class CompteCourant extends CompteBancaire {

    private double Decouvert;

    // Constructeur
    public CompteCourant(int Numero, String NomDuTitulaire,double Solde, double Decouvert) {
        super(Numero, NomDuTitulaire, Solde);
        this.Decouvert = Decouvert;
    }

    // Getter et setter
    public double getDecouvert() {
        return Decouvert;
    }

    public void setDecouvert(double Decouvert) {
        this.Decouvert = Decouvert;
    }

    // Retrait
    public void retirer(double montant) {
        if (montant > 0 && Solde - montant >= -Decouvert) {
            Solde -= montant;
        } else {
            System.out.println("Retrait impossible : decouvert depasse.");
        }
    }

    // Affichage
    @Override
    public String afficherInformations() {
        return super.afficherInformations() + ", decouvert autorise : " + Decouvert;
    }
}