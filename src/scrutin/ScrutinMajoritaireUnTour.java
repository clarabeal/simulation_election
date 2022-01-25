package scrutin;

public class ScrutinMajoritaireUnTour extends Scrutin{

    public ScrutinMajoritaireUnTour(int nbE, int nbC){
        super(nbE,nbC);
    }

    /**
    * @Override
    */
    public void voter(){
        //On parcourt tabElecteur
        for(int i=0;i<this.getNbElecteur();i++){
            int iCandidat=-1;
            double moyMin=1;
            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<this.getNbCandidat();j++){
                double moy=0;
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){ 
                    moy=moy+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
                }
                moy=moy/2;

                //On cherche le candidat qui se rapproche le plus des opinions de l'électeur
                if(moy<moyMin){
                    moyMin=moy;
                    iCandidat=j;
                }
            }
            if(moyMin<getSeuil()){
                getElecteur(i).setVote(0,iCandidat+1);
            }
            else{ //Abstention
                getElecteur(i).setVote(0,-1);
            } 
        }
    }
}