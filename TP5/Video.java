public class Video extends Document {
    private double duree;       // en minutes
    private String resolution;

    public Video(int id, String titre, double tailleMo, double duree, String resolution) {
        super(id, titre, tailleMo);
        this.duree = duree;
        this.resolution = resolution;
    }

    public double getDuree() { return duree; }
    public void setDuree(double duree) { this.duree = duree; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    @Override
    public void afficherInformations() {
        super.afficherInformations();
        System.out.println("   -> Video : " + duree + " min, " + resolution);
    }
}