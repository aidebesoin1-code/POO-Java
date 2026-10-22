public class LivraisonStandard {
    //attributs 
    private int numero;
    private String destination;
    private int poids;
    private int prixBase;

    //Constructeur 
    public LivraisonStandard(int n, string d, int p, int pr){
        this.numero = n;
        this.destination =  d;
        this.poids = p;
        this.prixBase = pr;
    }

    //getters et setters
    public int getnumero(){
        return numero;
    }

    public int getdestination(){
        return destination;
    }

    public int getpoids(){
        return poids;
    }

    public int getprixBase(){
        return prixBase;
    }  
}