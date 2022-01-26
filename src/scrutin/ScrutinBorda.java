package scrutin;

public class ScrutinBorda extends Scrutin{

    public ScrutinBorda(int nbE, int nbC){
        super(nbE,nbC);
    }

    /**
    * @Override
    */
    public void voter(){
        //On parcourt tabElecteur
        for(int i=0;i<getNbElecteur();i++){
            //Tab qui contiendra la moy de l'électeur avec les candidats
            double[] tabMoy = new double [getNbCandidat()];

            //On créé un tab qui va renseigner le rang des moyennes
            int tabRang[]=new int[getNbCandidat()];

            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<getNbCandidat();j++){
                double moy=0;
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){ 
                    moy=moy+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
                }
                moy=moy/2;
                tabMoy[j]=moy; 
            }

            //On remplit tabRang
            for(int n=0;n<getNbCandidat();n++){
                int compt = 0;
                for(int m=0;m<getNbCandidat();m++){
                    if(tabMoy[m]<tabMoy[n]){
                        compt++;
                    }
                }
                tabRang[n]=compt+1;
            }

            //On remplit tabVote de chaque Electeur
            int points = getNbCandidat();

            for(int p=0;p<getNbCandidat();p++){
                getElecteur(i).setVote(p,points-tabRang[p]+1);
            }

            //Affichage test 
            for(int l=0;l<getNbCandidat();l++){
                System.out.println(tabMoy[l]);
            }
            System.out.println(" ");
            for(int l=0;l<getNbCandidat();l++){
                System.out.println(tabRang[l]);
            }
            System.out.println(" ");
        }
    }
}