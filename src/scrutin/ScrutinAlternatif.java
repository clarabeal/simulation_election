package scrutin;

public class ScrutinAlternatif extends Scrutin {

    private int tabRang[];

    public ScrutinAlternatif(int nbE, int nbC){
        super(nbE,nbC);
    
        this.tabRang = new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++){
			tabRang[i]=0;
        }
    }

    /**
    * @Override
    */
    //Remplit tabVote de chaque Electeur en classant les Candidats
    public int voter(){

        //On parcourt tabElecteur
        for(int i=0;i<getNbElecteur();i++){
            //Tab qui contiendra la moy de l'électeur avec les candidats
            double[] tabMoy = new double [getNbCandidat()];

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

            //On remplit tabVote en fonction du rang des moyennes
            for(int n=0;n<getNbCandidat();n++){
                int compt = 0;
                for(int m=0;m<getNbCandidat();m++){
                    if(tabMoy[m]<tabMoy[n]){
                        compt++;
                    }
                }
                getElecteur(i).setVote(n,compt+1);
            }
        }

        return 0;
    }

    //Fonction qui retourne l'indice du Candidat qui a reçu le moins de 1er choix
    public int getIdElimine(){
        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();

        //Affichage tabVoix
        System.out.print("nbVoix : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println(" ");

        int min=getNbElecteur(); //Il ne peut pas y avoir plus de voix que d'électeurs
        int idElimine=0;

        for(int i=0;i<getNbCandidat();i++){
            if((tabVoix[i]<min)&&(tabVoix[i]!=0)){
                min=tabVoix[i];
                idElimine=i;
            }
        }

        return idElimine;
    }

    //Fonction qui remplit tabVote de chaque Electeur en prenant en compte le Candidat éliminé
    public int voterElimine(){

        int compt=getNbCandidat();

        for(int k=0;k<getNbCandidat()-1;k++){
            //On récupère l'indice du Candidat éliminé
            int idElimine = getIdElimine();
            System.out.println("L'éliminé : "+idElimine);

            //On remplit tabRang
            this.tabRang[idElimine]=compt;
            compt--;

            for(int i=0;i<getNbElecteur();i++){
                
                //On modifie le classement des candidats qui se situent après l'idElimine
                for(int p=0;p<getNbCandidat();p++){
                    if(getElecteur(i).getVote(p)>getElecteur(i).getVote(idElimine)){
                        getElecteur(i).setVote(p, getElecteur(i).getVote(p)-1);
                    }
                }
                //On met à 0 la case de l'idElimine pour chaque tabVote
                for(int l=0;l<getNbCandidat();l++){
                    if(l==idElimine){
                        getElecteur(i).setVote(l,0);
                    }
                }
            }

            affTabElecteur();
        }

        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();

        //On cherche l'indice du gagnant pour remplir la dernière case de tabRang
        for(int i=0;i<getNbCandidat();i++){
            if(tabVoix[i]!=0){
                this.tabRang[i]=compt;
            }
        }

        //Affichage tabVoix
        System.out.print("nbVoix : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println(" ");
        
        return 0;
    }

    //Redéfinition
    //Fonction qui retourne un tab des résultats du scrutin
    public int[] getRangIndice(){
        return this.tabRang;
    }

    //Redéfinition
    //Fonction qui retourne un tab contenant le nombre de fois qu'un Candidat a été placé 1er (indice 0 => candidat 1, indice 1 => candidat 2 ...)
    public int[] getNbVoix(){

        int tabVoix[] = new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++){
            tabVoix[i]=0;
        }

        for(int i=0;i<this.getNbElecteur();i++){
            //On parcourt tabVote de tous les électeurs
            for(int j=0;j<this.getNbCandidat();j++){
                if(getElecteur(i).getVote(j)==1){ //Si égal à 1 on lui rajoute une voix
                    tabVoix[j]++;
                }
            }
        }
        return tabVoix;
    }
}