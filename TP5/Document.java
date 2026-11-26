public abstract class Document {
    private int id;
    private String titre;
    private double tailleMo;

    public Document(int id, String titre, double tailleMo) {
        this.id = id;
        this.titre = titre;
        this.tailleMo = tailleMo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public double getTailleMo() { return tailleMo; }
    public void setTailleMo(double tailleMo) { this.tailleMo = tailleMo; }

    public void afficherInformations() {
        System.out.println("Document [id=" + id + ", titre=" + titre + ", taille=" + tailleMo + " Mo]");
    }
}