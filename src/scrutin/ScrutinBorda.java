package scrutin;
import java.util.Map; 
import java.util.HashMap;

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
            Map<Integer,Double> map= new HashMap<>();
            //On parcourt tabCandidat pour chaque électeur
            for(int j=0;j<getNbCandidat();j++){
                double moy=0;
                //On parcourt toutes les représentations
                for(int r=0;r<2;r++){ 
                    moy=moy+Math.abs(getCandidat(j).getRepresentation(r)-getElecteur(i).getRepresentation(r));
                }
                moy=moy/2;
                map.put(getCandidat(j).getIdCandidat(),moy);

                //On trie 
            }
            System.out.println(map);
        }
    }
}