public class Facture extends Document implements Imprimable, Exportable {
    private String numeroFacture;
    private double montant;

    public Facture(int id, String titre, double tailleMo, String numeroFacture, double montant) {
        super(id, titre, tailleMo);
        this.numeroFacture = numeroFacture;
        this.montant = montant;
    }

    public String getNumeroFacture() { return numeroFacture; }
    public void setNumeroFacture(String numeroFacture) { this.numeroFacture = numeroFacture; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    @Override
    public void afficherInformations() {
        super.afficherInformations();
        System.out.println("   -> Facture n°" + numeroFacture + " : " + montant);
    }

    @Override
    public void imprimer() {
        System.out.println("Impression de la facture n°" + numeroFacture + " (" + montant + ")");
    }

    @Override
    public void exporterPDF() {
        System.out.println("Export PDF de la facture n°" + numeroFacture);
    }
}