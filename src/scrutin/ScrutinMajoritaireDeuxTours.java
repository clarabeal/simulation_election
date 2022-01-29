package scrutin;

import personnes.*;

public class ScrutinMajoritaireDeuxTours extends ScrutinMajoritaireUnTour{

    private int tabIdCandidats[]; //tableau qui permettra d'avoir l'id des candidats pour les résultats

    public ScrutinMajoritaireDeuxTours(int nbE, int nbC) {
        super(nbE,nbC);

        this.tabIdCandidats = new int[2];
        for(int i=0;i<2;i++){
			tabIdCandidats[i]=0;
        }
    }

    //Fonction qui modifie le tabCandidat en prenant les 2 gagnants du 1er tour
    public void modifTabCandidat(int[] tabResultat){

        Candidat[] tabCandidatTour2 = new Candidat[2];

        tabCandidatTour2[0]=getCandidat(tabResultat[0]-1);//-1 à cause du décalage dans tabCandidat
        tabCandidatTour2[1]=getCandidat(tabResultat[1]-1); 

        deleteTabCandidat();
        setNewTabCandidat(tabCandidatTour2);

        //On remplit tabIdCandidats
        tabIdCandidats[0]=getCandidat(0).getIdCandidat();
        tabIdCandidats[1]=getCandidat(1).getIdCandidat();
    }

    //Fonction qui retourne un tab des résultats du scrutin du 2ème tour
    public int[] getResultat2(){
        int tabRang[]=new int[getNbCandidat()];
        tabRang=getRangIndice();

        //On modifie tabRang pour enlever les égalités possibles
        changeEgalite(tabRang);

        int tabResult[]=new int[getNbCandidat()];

        for(int i=1;i<getNbCandidat()+1;i++){
            for(int j=0;j<getNbCandidat();j++){
                if(tabRang[j]==i){
                    System.out.println("Candidat "+tabIdCandidats[j]+" est "+i);
                    tabResult[i-1]=tabIdCandidats[j];
                }
            }
        }
        return tabResult;
    }

}