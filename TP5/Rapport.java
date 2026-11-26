public class Rapport extends Document implements Imprimable, Exportable {
    private int nombrePages;

    public Rapport(int id, String titre, double tailleMo, int nombrePages) {
        super(id, titre, tailleMo);
        this.nombrePages = nombrePages;
    }

    public int getNombrePages() { return nombrePages; }
    public void setNombrePages(int nombrePages) { this.nombrePages = nombrePages; }

    @Override
    public void afficherInformations() {
        super.afficherInformations();
        System.out.println("   -> Rapport : " + nombrePages + " pages");
    }

    @Override
    public void imprimer() {
        System.out.println("Impression du rapport '" + getTitre() + "' (" + nombrePages + " pages)");
    }

    @Override
    public void exporterPDF() {
        System.out.println("Export PDF du rapport '" + getTitre() + "'");
    }
}