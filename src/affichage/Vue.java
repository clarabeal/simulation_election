package Affichage;
import scrutin.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Classe qui permer d'afficher l'application.
 */
public class Vue implements ActionListener {
	
    // attributs :

    public final static int NBITERATIONS = 0;

    private JFrame frame;
    private JPanel panelConsigne;
    private JLabel labelConsigne;
    private JPanel panelChoixModele;
    private ChoixModele choixModele;
    private JPanel panelChoixSondage;
    private ChoixSondage choixSondage;
    private JPanel panelBoutons;
    private JButton bLancer;
    private JButton bReinitialiser;
    private JButton bQuitter;
    private JDialog dialog;
    private JPanel panelDialog;
    private JLabel labelDialog;
    private JPanel panelCentre;


    // constructeur :
    /**
     * Le constructeur de la classe Vue génère tous les composants nécessaires à un affichage de l'application.
     */
    public Vue() {
        frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Projet APO");
        frame.setResizable(false);

        labelConsigne = new JLabel("Veuillez choisir un modèle et ses paramètres :");
        labelConsigne.setFont(new Font("Dialog", Font.BOLD, 20));
        panelConsigne = new JPanel();
        panelConsigne.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 100));
        panelConsigne.add(labelConsigne);
        frame.add(panelConsigne, BorderLayout.NORTH);

        panelCentre = new JPanel(new GridLayout(2, 1, 0, 20));

        choixModele = new ChoixModele();
        panelChoixModele = new JPanel(new BorderLayout());
        panelChoixModele.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        choixModele.addComponentToPane(panelChoixModele);
        panelCentre.add(panelChoixModele);

        choixSondage = new ChoixSondage();
        panelChoixSondage = new JPanel(new BorderLayout());
        panelChoixSondage.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));
        choixSondage.addComponentToPane(panelChoixSondage);
        panelCentre.add(panelChoixSondage);


        panelCentre.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        frame.add(panelCentre, BorderLayout.CENTER);

        bLancer = new JButton("Lancer la simulation");
        bLancer.addActionListener(this);
        bReinitialiser = new JButton("Réinitialiser");
        bReinitialiser.addActionListener(this);
        bQuitter = new JButton("Quitter");
        bQuitter.addActionListener(this);
        panelBoutons = new JPanel(new GridLayout(1, 3, 30, 0));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        panelBoutons.add(bLancer);
        panelBoutons.add(bReinitialiser);
        panelBoutons.add(bQuitter);
        frame.add(panelBoutons, BorderLayout.SOUTH);

        panelDialog = new JPanel();
        panelDialog.setBorder(BorderFactory.createEmptyBorder(10, 250, 40, 250));
        labelDialog = new JLabel();
        labelDialog.setForeground(Color.RED);
        panelDialog.add(labelDialog);
        dialog = new JDialog(frame, "Erreur");
        dialog.add(panelDialog);
        dialog.pack();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
	
	
	
	// méthodes :
    
    /**
     * Réalise une action en fonction du bouton sur lequel l'utilisateur clique :<br>
     * - Bouton "Lancer la simulation" : affiche un graphique en fonction des paramètres entrés.<br>
     * - Bouton "Réinitialiser" : remet les paramètres de base (ceux qu'on a à l'initialisation).<br>
     * - Bouton "Quitter" : ferme l'application (termine le programme).
     */	
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.dispose();
        dialog.setLocationRelativeTo(frame);
        if (ae.getSource() == bLancer) {
            try {
                if (choixModele.getSelection() == ChoixModele.SCRUTIN_MAJORITAIRE_UN_TOUR) {


                        String[] tabString = choixModele.getMUTValues();
                        int[] tabDouble = new int[4];

                        for (int i = 0; i < tabString.length-2; i++) {
                                tabDouble[i] = Integer.parseInt(tabString[i]);
                        }

                        double getSeuil = Double.parseDouble(tabString[2]);

                        ScrutinMajoritaireUnTour s1 = new ScrutinMajoritaireUnTour(tabDouble[0],tabDouble[1]);
                        s1.setSeuil(getSeuil);
                        int nbAbstention=0;
                        for(int i=0;i<Integer.parseInt(tabString[3]);i++)
                        {
                           s1.interactions();
                        }

                        int[] countVote;
                        if(choixSondage.getSelection()==ChoixSondage.SONDAGE_PREFERENCE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getPREFERENCEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s1.affTabElecteur();
                                    System.out.println("");
                                    s1.modifTabElecteurSondage();
                                    s1.voter();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s1.getNbVoix()));
                                    s1.calculSondage();
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s1.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEValue());i++)
                                {         
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s1.affTabElecteur();
                                    System.out.println("");
                                    s1.modifTabElecteurSondage();
                                    s1.voter();
                                    countVote=s1.getNbVoix();
                                    s1.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s1.getNbVoix()));
                                    s1.CalculSondageUtilite(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s1.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE_PORPORTION){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEPROPORValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s1.affTabElecteur();
                                    System.out.println("");
                                    s1.modifTabElecteurSondage();
                                    s1.voter();
                                    countVote=s1.getNbVoix();
                                    s1.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s1.getNbVoix()));
                                    s1.CalculSondageUtilitePropor(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s1.affTabElecteur();
                                    System.out.println("");
                                }
                        }


                        nbAbstention=s1.voter();
                        s1.ToString(nbAbstention);
                        AffichageHist.createAndShowGui(s1.getNbVoix());
                        s1.getCSV();

                }
                
                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_MAJORITAIRE_DEUX_TOUR) {


                        String[] tabString = choixModele.getMDTValues();
                        int[] tabDouble = new int[4];

                        for (int i = 0; i < tabString.length-2; i++) {
                                tabDouble[i] = Integer.parseInt(tabString[i]);
                        }

                        double getSeuil = Double.parseDouble(tabString[2]);

                        ScrutinMajoritaireDeuxTours s2 = new ScrutinMajoritaireDeuxTours(tabDouble[0],tabDouble[1]);
                        s2.setSeuil(getSeuil);

                        int nbAbstention=0;
                        for(int i=0;i<Integer.parseInt(tabString[3]);i++)
                        {
                            s2.interactions();
                        }
                        int[] countVote;
                        if(choixSondage.getSelection()==ChoixSondage.SONDAGE_PREFERENCE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getPREFERENCEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s2.affTabElecteur();
                                    System.out.println("");
                                    s2.modifTabElecteurSondage();
                                    s2.voter();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s2.getNbVoix()));
                                    s2.calculSondage();
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s2.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s2.affTabElecteur();
                                    System.out.println("");
                                    s2.modifTabElecteurSondage();
                                    s2.voter();
                                    countVote=s2.getNbVoix();
                                    s2.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s2.getNbVoix()));
                                    s2.CalculSondageUtilite(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s2.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE_PORPORTION){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEPROPORValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s2.affTabElecteur();
                                    System.out.println("");
                                    s2.modifTabElecteurSondage();
                                    s2.voter();
                                    countVote=s2.getNbVoix();
                                    s2.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s2.getNbVoix()));
                                    s2.CalculSondageUtilitePropor(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s2.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        nbAbstention=s2.voter();
                        int[] arrayVote1 = s2.getNbVoix();



                        AffichageHist.createAndShowGuiName(s2.getNbVoix(),"Histogramme du nombre de vote par candidat (majoritaire TOUR 1)");
                        s2.modifTabCandidat(s2.ToString1erTour(nbAbstention));      
                        nbAbstention=s2.voter();
                        s2.ToString2eTour(nbAbstention);
                        AffichageHist.createAndShowGuiName(s2.getNbVoix(),"Histogramme du nombre de vote par candidat (majoritaire TOUR 2)");
                        s2.getCSV(arrayVote1);



                }
                
                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_APPROBATION) {

                        String[] tabString = choixModele.getAPPROBATIONValues();
                        int[] tabDouble = new int[4];
                        for (int i = 0; i < tabString.length-2; i++) {
                                tabDouble[i] = Integer.parseInt(tabString[i]);
                        }
                        double getSeuil = Double.parseDouble(tabString[2]);

                        ScrutinApprobation s3 = new ScrutinApprobation(tabDouble[0],tabDouble[1]);
                        s3.setSeuil(getSeuil);     

                        int nbAbstention=0;
                        for(int i=0;i<Integer.parseInt(tabString[3]);i++)
                        {
                            s3.interactions();
                        }
                        int[] countVote;
                        if(choixSondage.getSelection()==ChoixSondage.SONDAGE_PREFERENCE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getPREFERENCEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s3.affTabElecteur();
                                    System.out.println("");
                                    s3.modifTabElecteurSondage();
                                    s3.voter();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s3.getNbVoix()));
                                    s3.calculSondage();
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s3.affTabElecteur();
                                    System.out.println("");                                                      
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s3.affTabElecteur();
                                    System.out.println("");
                                    s3.modifTabElecteurSondage();
                                    s3.voter();
                                    countVote=s3.getNbVoix();
                                    s3.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s3.getNbVoix()));
                                    s3.CalculSondageUtilite(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s3.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE_PORPORTION){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEPROPORValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s3.affTabElecteur();
                                    System.out.println("");
                                    s3.modifTabElecteurSondage();
                                    s3.voter();
                                    countVote=s3.getNbVoix();
                                    s3.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s3.getNbVoix()));
                                    s3.CalculSondageUtilitePropor(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s3.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        nbAbstention=s3.voter();
                        s3.ToString(nbAbstention);
                        AffichageHist.createAndShowGui(s3.getNbVoix());
                        s3.getCSV();

                }

                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_BORDA) {

                        String[] tabString = choixModele.getBORDAValues();
                        int[] tabDouble = new int[3];

                        for (int i = 0; i < tabString.length-1; i++) {
                                tabDouble[i] = Integer.parseInt(tabString[i]);
                        }


                        ScrutinBorda s4 = new ScrutinBorda(tabDouble[0],tabDouble[1]);
                        for(int i=0;i<Integer.parseInt(tabString[2]);i++)
                        {
                            s4.interactions();
                        }
                        int[] countVote;
                        if(choixSondage.getSelection()==ChoixSondage.SONDAGE_PREFERENCE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getPREFERENCEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s4.affTabElecteur();
                                    System.out.println("");
                                    s4.modifTabElecteurSondage();
                                    s4.voter();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s4.getNbVoix()));
                                    s4.calculSondage();
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s4.affTabElecteur();
                                    System.out.println(""); 
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s4.affTabElecteur();
                                    System.out.println("");
                                    s4.modifTabElecteurSondage();
                                    s4.voter();
                                    countVote=s4.getNbVoix();
                                    s4.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s4.getNbVoix()));
                                    s4.CalculSondageUtilite(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s4.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE_PORPORTION){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEPROPORValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s4.affTabElecteur();
                                    System.out.println("");
                                    s4.modifTabElecteurSondage();
                                    s4.voter();
                                    countVote=s4.getNbVoix();
                                    s4.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s4.getNbVoix()));
                                    s4.CalculSondageUtilitePropor(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s4.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        s4.voter();
                        s4.ToString();
                        AffichageHist.createAndShowGui(s4.getNbVoix());
                        s4.getCSV();



                }
                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_ALTERNATIF) {

                        String[] tabString = choixModele.getALTERNATIFValues();
                        int[] tabDouble = new int[3];

                        for (int i = 0; i < tabString.length-1; i++) {
                                tabDouble[i] = Integer.parseInt(tabString[i]);
                        }



                        ScrutinAlternatif s5 = new ScrutinAlternatif(tabDouble[0],tabDouble[1]);
                        int nbAbstention=0;
                        for(int i=0;i<Integer.parseInt(tabString[2]);i++)
                        {
                            s5.interactions();
                        }
                        int[] countVote;
                        if(choixSondage.getSelection()==ChoixSondage.SONDAGE_PREFERENCE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getPREFERENCEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s5.affTabElecteur();
                                    System.out.println("");
                                    s5.modifTabElecteurSondage();
                                    s5.voter();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s5.getNbVoix()));
                                    s5.calculSondage();
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s5.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s5.affTabElecteur();
                                    System.out.println("");
                                    s5.modifTabElecteurSondage();
                                    s5.voter();
                                    countVote=s5.getNbVoix();
                                    s5.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s5.getNbVoix()));
                                    s5.CalculSondageUtilite(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s5.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        else if(choixSondage.getSelection()==ChoixSondage.SONDAGE_UTILITE_PORPORTION){
                                for(int i=0;i<Integer.parseInt(choixSondage.getUTILITEPROPORValue());i++)
                                {
                                    System.out.println("Electeur AVANT SONDAGE");
                                    System.out.println("");
                                    s5.affTabElecteur();
                                    System.out.println("");
                                    s5.modifTabElecteurSondage();
                                    s5.voter();
                                    countVote=s5.getNbVoix();
                                    s5.resetTabElecteur();
                                    System.out.println("Résultat vote sondage : "+Arrays.toString(s5.getNbVoix()));
                                    s5.CalculSondageUtilitePropor(countVote);
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Electeur APRES SONDAGE");
                                    System.out.println("");
                                    s5.affTabElecteur();
                                    System.out.println("");
                                }
                        }
                        nbAbstention=s5.voter();
                        s5.voterElimine();
                        s5.ToString();
                        s5.getCSV();
                }
            } catch (Exception e) {
                    labelDialog.setText("Vérifiez que les valeurs saisies soient justes.");
                    dialog.setVisible(true);
            }
        } else if (ae.getSource() == bReinitialiser) {
                choixModele.reinitialiser();
                choixSondage.reinitialiser();
        } else {
                frame.dispose();
        }
    }

}