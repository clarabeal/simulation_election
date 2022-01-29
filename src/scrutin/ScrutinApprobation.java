package scrutin;

public class ScrutinApprobation extends Scrutin {

    public ScrutinApprobation(int nbE, int nbC){
        super(nbE,nbC);
    }

    /**
    * @Override
    */
    //Remplit tabVote de chaque Electeur avec les identifiants des Candidats pour lequel il souhaite voter et retourne le nb d'abstention
    public int voter(){
        int nbAbstention=0;
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

            //S'il n'y a pas de vote, il y a abstention donc -1
            if(vide){
                getElecteur(i).setVote(0,-1);
                nbAbstention++;
            }
        }
        return nbAbstention;
    }

    public void ToString(int abstention){
        System.out.print("Abstention : ");
        System.out.println(abstention);
        
        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();
        System.out.print("nbVoix : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println("");
        
        int tabRang[] = new int[getNbCandidat()];
        tabRang=getRangIndice();
        
        System.out.print("Rang : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabRang[i]+" ");
        }
        System.out.println("");
        
        int tabResult[] = new int[getNbCandidat()];
        tabResult=getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabResult[i]+" ");
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void getCSV(){
		ArrayList value1 = new ArrayList();
                ArrayList value2 = new ArrayList();
                
                int tabResult[] = new int[getNbCandidat()];
                tabResult=getResultat();
                int tabVoix[] = new int[getNbCandidat()];
                tabVoix=getNbVoix();
               
		for(int i = 0; i < tabResult.length; i++) {
			value1.add("Candidat "+tabResult[i]);
		}
                
                for(int i = 0; i < tabVoix.length; i++) {
			value2.add(tabVoix[i]);
		}
               

		FileWriter file = null;
		try {
			file = new FileWriter("RESULTAT VOTE APPROBATION.csv");
			for(int i = 0; i < value1.size(); i++) {
				file.append(String.valueOf(value1.get(i)));
				file.append(";");
			}
                        file.append("\n");
			for(int i = 0; i < value2.size(); i++) {
				file.append(String.valueOf(value2.get(i)));
				file.append(";");
			}
	
			file.append("\n");
			file.close();
        } 
        catch(Exception e) {
			e.printStackTrace();
		}
	}
}