package personnes;

public class Electeur extends Personne{
    private static int stidElecteur=0; // Ne se remettra jamais à 0 grâce au static
    private int idElecteur;
    public int[] tabVote;

    public Electeur(){
        super();

        idElecteur=stidElecteur;
        stidElecteur++;

        int[] tabVote = new int[8]; //Mettre le nb de candidats avec constante?
    }
}