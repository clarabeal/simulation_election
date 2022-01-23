package personnes;

public class Electeur extends Personne{
    private static int stidElecteur=1; //Ne se remettra jamais à 0 grâce au static
    private int idElecteur;
    private int nbCandidat;
    public int[] tabVote;

    //Constructeur a besoin du nb de Candidat afin d'initialiser le tabVote
    public Electeur(int n){
        super();

        this.idElecteur=this.stidElecteur;
        this.stidElecteur++;

        this.nbCandidat=n;
        this.tabVote = new int[this.nbCandidat];

        for(int i=0;i<this.nbCandidat;i++){
            this.tabVote[i]=0;
        }
    }

    //Retourne l'identifiant de l'électeur
    public int getIdElecteur(){
        return this.idElecteur;
    }

    //Affiche le tableau des votes
    public void affTabVote(){
        for(int i=0;i<this.nbCandidat;i++){
            //if(this.tabVote[i]!=0){ //On affiche seulement les cases remplies
              //  System.out.println(this.tabVote[i]);
            //}
            System.out.println(this.tabVote[i]);
        }
    }
}