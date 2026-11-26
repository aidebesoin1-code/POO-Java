public class Chambre {
    private int numero;
    private String type;          // "SIMPLE", "DOUBLE", "SUITE"
    private double prixParNuit;
    private boolean disponible;   // false = actuellement occupée (check-in fait)
    private int capacite;

    public Chambre(int numero, String type, double prixParNuit, int capacite) {
        this.numero = numero;
        this.type = type;
        setPrixParNuit(prixParNuit);   // vérification dans le constructeur via le setter
        setCapacite(capacite);
        this.disponible = true;
    }

    public int getNumero() { return numero; }
    public String getType() { return type; }
    public double getPrixParNuit() { return prixParNuit; }
    public boolean isDisponible() { return disponible; }
    public int getCapacite() { return capacite; }

    public boolean isSuite() { return "SUITE".equalsIgnoreCase(type); }

    public void setPrixParNuit(double prix) {
        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être strictement positif");
        }
        this.prixParNuit = prix;
    }

    public void setCapacite(int capacite) {
        if (capacite <= 0) {
            throw new IllegalArgumentException("La capacité doit être strictement positive");
        }
        this.capacite = capacite;
    }

    public void reserver() throws ChambreIndisponibleException {
        if (!disponible) {
            throw new ChambreIndisponibleException("La chambre " + numero + " est déjà occupée.");
        }
        disponible = false;
    }

    public void liberer() {
        disponible = true;
    }

    @Override
    public String toString() {
        return "Chambre " + numero + " (" + type + ", " + prixParNuit + " DH/nuit, capacité " + capacite
                + ", " + (disponible ? "libre" : "occupée") + ")";
    }
}