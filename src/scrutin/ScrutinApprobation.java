package scrutin;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * ScrutinApprobation est la classe qui hérite de la classe Scrutin
 * Le système du vote approbation est que chaque électeur peut voter pour le nombre de candidats qu’il souhaite, le vainqueur
 * est celui qui a récolté le plus de voix
 */
public class ScrutinApprobation extends Scrutin {

    /**
	 * Le construteur de la classe
	 * @param nbE
	 * 		Le nombre d'électeurs
	 * @param nbC
	 * 		Le nombre de candidats
	 */
    public ScrutinApprobation(int nbE, int nbC){
        super(nbE,nbC);
    }

    /**
    * Remplit tabVote de chaque Electeur avec les identifiants des Candidats pour lequel il souhaite voter
    * @return Le nombre d'abstentions
    */
    @Override
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
    
    /**
	 * Affichage du gagnant et du nombre de voix par candidat
     *  @param abstention
	 * 		Le nombre d'abstention
	 */  
    public void ToString(int abstention){
        System.out.println("");
        System.out.println("");
        System.out.println("Nombre de voix : ");
        System.out.print("Abstention : ");
        System.out.println(abstention);

        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();

        
        for(int i=0;i<getNbCandidat();i++){
            System.out.print("Candidat "+(i+1)+" :");
            System.out.println(tabVoix[i]+" ");
        }
        System.out.println("");

        int tabResult[] = new int[getNbCandidat()];
        tabResult=getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabResult[i]+" ");
        }
        System.out.println("");
        System.out.println("Le gagnant est : Candidat "+tabResult[0]);
        System.out.println("");
    }
    
    /**
	 * Permet la création d'un fichier CSV
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getCSV() {
		ArrayList value1 = new ArrayList();
        ArrayList value2 = new ArrayList();
        ArrayList value3 = new ArrayList();
        ArrayList value4 = new ArrayList();
        ArrayList value5 = new ArrayList();
        ArrayList value6 = new ArrayList();
        ArrayList value7 = new ArrayList();
        ArrayList value8 = new ArrayList();

        
        int tabVoix2[] = new int[getNbCandidat()];
        tabVoix2=getNbVoix();
               
		int tabResult2[] = new int[getNbCandidat()];
        for(int i = 0;i<getNbCandidat();i++)
        {
            tabResult2[i]=i+1;
        }
        
        int tabElecteur[]=new int[getNbElecteur()];
        for(int i=0;i<getNbElecteur();i++)
        {
            tabElecteur[i]=getElecteur(i).getIdElecteur();
        }
        
        double[] tabRep1=new double[getNbElecteur()];
        for(int i=0;i<getNbElecteur();i++)
        {
            tabRep1[i]=getElecteur(i).getRepresentation(0);
        }
        
        
        double[] tabRep2=new double[getNbElecteur()];
        for(int i=0;i<getNbElecteur();i++)
        {
            tabRep2[i]=getElecteur(i).getRepresentation(1);
        }
        
        int tabCandidat[]=new int[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++)
        {
            tabCandidat[i]=getCandidat(i).getIdCandidat();
        }
        
        double[] tabCRep1=new double[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++)
        {
            tabCRep1[i]=getCandidat(i).getRepresentation(0);
        }
        
        
        double[] tabCRep2=new double[getNbCandidat()];
        for(int i=0;i<getNbCandidat();i++)
        {
            tabCRep2[i]=getCandidat(i).getRepresentation(1);
        }
                
        for(int i = 0; i < tabResult2.length; i++) {
			value1.add("Candidat "+tabResult2[i]);
		}
                
        for(int i = 0; i < tabVoix2.length; i++) {
			value2.add(tabVoix2[i]);
		}
                
        for(int i = 0; i < tabElecteur.length; i++) {
			value3.add("Electeur "+tabElecteur[i]);
		}
                
        for(int i = 0; i < tabRep1.length; i++) {
			value4.add(tabRep1[i]);
		}
                
        for(int i = 0; i < tabRep2.length; i++) {
			value5.add(tabRep2[i]);
		}
                
        for(int i = 0; i < tabCandidat.length; i++) {
			value6.add("Candidat "+tabCandidat[i]);
		}
                
        for(int i = 0; i < tabCRep1.length; i++) {
			value7.add(tabCRep1[i]);
		}
                
        for(int i = 0; i < tabCRep2.length; i++) {
			value8.add(tabCRep2[i]);
		}
                
		FileWriter file = null;
		try {
			file = new FileWriter("RESULTAT VOTE APPROBATION.csv");
                        file.append("Résultat VOTE\n");
			for(int i = 0; i < value1.size(); i++) {
				file.append(String.valueOf(value1.get(i)));
				file.append(";");
			}
            file.append("\n");
			for(int i = 0; i < value2.size(); i++) {
				file.append(String.valueOf(value2.get(i)));
				file.append(";");
			}
            file.append("\n\n");
            file.append("Représentation Electeur\n");
            for(int i = 0; i < value3.size(); i++) {
				file.append(String.valueOf(value3.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value4.size(); i++) {
				file.append(String.valueOf(value4.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value5.size(); i++) {
				file.append(String.valueOf(value5.get(i)));
				file.append(";");
			}
                        
            file.append("\n\n");
            file.append("Représentation Candidat\n");
            for(int i = 0; i < value6.size(); i++) {
				file.append(String.valueOf(value6.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value7.size(); i++) {
				file.append(String.valueOf(value7.get(i)));
				file.append(";");
			}
            file.append("\n");
            for(int i = 0; i < value8.size(); i++) {
				file.append(String.valueOf(value8.get(i)));
				file.append(";");
			}
	
			file.append("\n");
            file.close();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}    
}