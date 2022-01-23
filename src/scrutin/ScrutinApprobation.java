package scrutin;

public class ScrutinApprobation extends Scrutin {

    public ScrutinApprobation(int nbE, int nbC){
        super(nbE,nbC);
    }

    @Override
    //Procédure qui permet de remplir tabVote de chaque électeur
    public void voter(){
        double moy=0;

        //On parcourt tabElecteur
        for(int i=0;i<getNbELecteur();i++){
            int iVote=0;
            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<getNbCandidat();j++){
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){
                    moy=moy+Math.abs(getCandidat(i).getRepresentation(r)-getElecteur(j).getRepresentation(r));
                }
                moy=moy/2;
                if(moy<getSeuil()){
                    getElecteur(i).setVote(iVote,getCandidat(j).getIdCandidat());
                    iVote++;
                }
            }
        }
    }
}