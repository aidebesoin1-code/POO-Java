public class TestPersonne {
    public static void main(String[] args) {
    Personne achraf = new Personne("achraf", 25); // Instanciation dynamique
    // Personne juliette = new Personne("juliette", 20); // Instanciation dynamique
    
    System.out.println(achraf.getNom());
    System.out.println(achraf.getAge());
    System.out.println(achraf.getNote());

    }
}