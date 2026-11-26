public class CompteEpargne extends CompteBancaire {

    private double TauxInteret;

    // Constructeur
    public CompteEpargne(int Numero, String NomDuTitulaire,double Solde, double TauxInteret) {

        super(Numero, NomDuTitulaire, Solde);
        this.TauxInteret = TauxInteret;
    }

    // Getter et setter
    public double getTauxInteret() {
        return TauxInteret;
    }

    public void setTauxInteret(double TauxInteret) {
        this.TauxInteret = TauxInteret;
    }

    // Retrait
    public void retirer(double montant) {
        if (montant > 0 && Solde - montant >= 0) {
            Solde -= montant;
        } else {
            System.out.println("Retrait impossible : solde insuffisant.");
        }
    }

    // Calcul des intérêts
    public double calculerInterets() {
        return Solde * TauxInteret / 100;
    }

    // Affichage
    @Override
    public String afficherInformations() {
        return super.afficherInformations() + ", taux d'interet : " + TauxInteret + "%";
    }
}

