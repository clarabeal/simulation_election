package personnes;

public class Electeur extends Personne{
    private static int stidElecteur=0; // Ne se remettra jamais à 0 grâce au static
    private int idElecteur;
    private int nbElecteur;
    public int[] tabVote;

    public Electeur(int n){
        super();

        this.idElecteur=stidElecteur;
        stidElecteur++;

        int[] tabVote = new int[n];
        this.nbElecteur=n;
    }
}