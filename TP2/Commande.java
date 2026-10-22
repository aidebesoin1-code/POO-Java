import java.util.List;
import java.util.Date;


public class Commande {
    private int numero;
    private Date date;
    private List<Produit> produits;
    
    public Commande (int numero, Date date, List<Produit> produits){
        this.numero = numero;
        this.date = date;
        if(produits == null) {
            throw new IllegalArgumentException("Le produit ne peut pas etre nul.");
        }     
    }
}