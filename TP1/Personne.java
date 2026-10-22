public class Personne {
    // attributs (obligatoires)
    private String nom;
    private int age;
    private int note;

    // Constructeur (obligatoire)
    public Personne(String nom, int age) { 
        this.nom = nom;
        if (age < 0) {
            System.out.println("Erreur : l'âge ne peut pas être négatif. L'âge sera défini à 0.");
            this.age = 0;
        } else {
            this.age = age;
        }        
        this.note = 0;
    }

    // getters (optionnels)
    public String getNom() {
        return nom;
    }
    public int getAge() {
        return age;
    }
    public int getNote() {
        return note;
    }

    // setters (optionnels)
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setNote(int note) {
        if (note < 0 || note > 20) {
            System.out.println("Erreur : la note doit être comprise entre 0 et 20. La note sera définie à 0.");
            return;
        }
        this.note = note;
    }

    // méthodes (optionnels)
    public void afficherInfos() {
        System.out.println("Nom : " + nom + ", Âge : " + age); 
    }

}


//Personne achraf = new Personne(age:24, nom:"Achraf");
//Personne jp = new Personne(age:17, nom:"Jean-Pierre");
//Personne sofi = new Personne(age:19, nom:"Sofia");