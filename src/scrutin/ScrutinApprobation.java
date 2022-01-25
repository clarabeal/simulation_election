package scrutin;

public class ScrutinApprobation extends Scrutin {

    public ScrutinApprobation(int nbE, int nbC){
        super(nbE,nbC);
    }

    /**
    * @Override
    */
    public void voter(){
        //On parcourt tabElecteur
        for(int i=0;i<getNbElecteur();i++){
            int iVote=0;
            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<getNbCandidat();j++){
                double moy=0;
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){ 
                    moy=moy+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
                }
                
                moy=moy/2;

                if(moy<getSeuil()){
                    getElecteur(i).setVote(iVote,getCandidat(j).getIdCandidat());
                    iVote++;
                }
            }

            //On parcourt tabVote
            boolean vide=true;
            for(int c=0;c<getNbCandidat();c++){
                if(getElecteur(i).getVote(c)!=0){
                    vide=false;
                }
            }

            // S'il n'y a pas de vote, il y a abstention donc -1
            if(vide){
                getElecteur(i).setVote(0,-1);
            }
        }
    }
}