package personnes;

/**
 * Electeur est la classe qui hérite de Personne et qui désigne un électeur
 */
public class Electeur extends Personne{
    private static int stidElecteur=1; //Ne se remettra jamais à 0 grâce au static
    private int idElecteur;
    private int nbCandidat;
    private int[] tabVote;

    /**
	 * Le construteur de la classe
     * @param n 
     *      Nombre de candidats
	 */
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

    /**
     * Fonction qui retourne l'identifiant de l'électeur
     * @return idElecteur, l'id de l'électeur
     */
    public int getIdElecteur(){
        return this.idElecteur;
    }

    /**
     * Fonction qui retourne le vote à l'indice i
     * @param i
     *      L'indice du vote
     * @return tabVote[i], le vote
     */
    public int getVote(int i){
        return this.tabVote[i];
    }

    /**
     * Fonction qui insère une valeur dans tabVote à l'indice i
     * @param i
     *      L'indice du vote
     * @param v
     *      La valeur du vote
     */
    public void setVote(int i,int v){
        this.tabVote[i]=v;
    }

    /**
     * Fonction qui affiche le tabVote de l'électeur
     */
    public void affTabVote(){
        for(int i=0;i<this.nbCandidat;i++){
            if(this.tabVote[i]!=0){ //On affiche seulement les cases remplies
              System.out.print(this.tabVote[i]+" ");
            }
        }
    }
}