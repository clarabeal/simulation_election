package scrutin;

import personnes.*;

public class ScrutinMajoritaireDeuxTours extends ScrutinMajoritaireUnTour{

    public ScrutinMajoritaireDeuxTours(int nbE, int nbC) {
        super(nbE,nbC);
    }

    //Fonction qui modifie le tabCandidat en prenant les 2 gagnants du 1er tour
    public void modifTabCandidat(int[] tabResultat){

        Candidat[] tabCandidatTour2 = new Candidat[2];

        tabCandidatTour2[0]=getCandidat(tabResultat[0]-1);//-1 à cause du décalage dans tabCandidat
        tabCandidatTour2[1]=getCandidat(tabResultat[1]-1); 

        deleteTabCandidat();
        setNewTabCandidat(tabCandidatTour2);
    }

}