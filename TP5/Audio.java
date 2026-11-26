public class Audio extends Document implements Partageable {
    private double duree;   // en minutes

    public Audio(int id, String titre, double tailleMo, double duree) {
        super(id, titre, tailleMo);
        this.duree = duree;
    }

    public double getDuree() { return duree; }
    public void setDuree(double duree) { this.duree = duree; }

    @Override
    public void afficherInformations() {
        super.afficherInformations();
        System.out.println("   -> Audio : " + duree + " min");
    }

    @Override
    public void partager() {
        System.out.println("Partage de l'audio '" + getTitre() + "'");
    }
}