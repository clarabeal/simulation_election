package scrutin;

import java.io.FileWriter;
import java.util.ArrayList;
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

    public int[] ToString1erTour(int abstention){
        System.out.print("Abstention : ");
        System.out.println(abstention);

        int tabResult[] = new int[getNbCandidat()];
        tabResult=getNbVoix();

        System.out.print("nbVoix : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabResult[i]+" ");
        }
        System.out.println("");
        tabResult=getResultat();

        System.out.print("Résultats : ");
        for(int i=0;i<getNbCandidat();i++){
            System.out.print(tabResult[i]+" ");
        }
        return tabResult;
        
    }

    public void ToString2eTour(int abstention){
        System.out.print("Abstention : ");
        System.out.println(abstention);

        int tabVoix[] = new int[getNbCandidat()];
        tabVoix=getNbVoix();

        System.out.print("nbVoix : ");
        for(int i=0;i<2;i++){
            System.out.print(tabVoix[i]+" ");
        }
        System.out.println("");

        int tabResult[] = new int[getNbCandidat()];
        tabResult=getResultat2();

        System.out.print("Résultats : ");
        for(int i=0;i<2;i++){
            System.out.print(tabResult[i]+" ");
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void getCSV() {
		ArrayList value1 = new ArrayList();
                ArrayList value2 = new ArrayList();
                ArrayList value3 = new ArrayList();
                ArrayList value4 = new ArrayList();
                
                int tabResult1[] = new int[getNbCandidat()];
                tabResult1=getResultat();
                int tabVoix1[] = new int[getNbCandidat()];
                tabVoix1=getNbVoix();
                int tabResult2[] = new int[getNbCandidat()];
                tabResult2=getResultat2();
                int tabVoix2[] = new int[getNbCandidat()];
                tabVoix2=getNbVoix();
               
		for(int i = 0; i < tabResult1.length; i++) {
			value1.add("Candidat "+tabResult1[i]);
		}
                
        for(int i = 0; i < tabVoix1.length; i++) {
			value2.add(tabVoix1[i]);
		}
                
        for(int i = 0; i < tabResult2.length; i++) {
			value3.add("Candidat "+tabResult2[i]);
		}
                
        for(int i = 0; i < tabVoix2.length; i++) {
			value4.add(tabVoix2[i]);
		}
               

		FileWriter file = null;
		try {
                    
			file = new FileWriter("RESULTAT VOTE MAJORITAIRE A 2 TOUR.csv");
            file.append("Résultat vote 1er tour");
            file.append("\n");
                        
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
            file.append("Résultat vote 2e tour");
            file.append("\n");
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
			file.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}