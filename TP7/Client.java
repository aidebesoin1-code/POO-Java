public class Client {
    private int id;
    private String nom;
    private String email;
    private int age;
    private boolean interdit;   // interdit de séjour

    public Client(int id, String nom, String email, int age) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        if (age < 0) {
            throw new IllegalArgumentException("L'âge ne peut pas être négatif");
        }
        this.age = age;
        this.interdit = false;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public boolean isMajeur() { return age >= 18; }
    public boolean isInterdit() { return interdit; }
    public void setInterdit(boolean interdit) { this.interdit = interdit; }

    @Override
    public String toString() {
        return "Client " + id + " - " + nom + " (" + email + ")";
    }
}