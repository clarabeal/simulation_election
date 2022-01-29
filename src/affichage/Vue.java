package affichage;
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


public class Vue implements ActionListener {
	
	// attributs :
	
	public final static int NBITERATIONS = 1000;
	
	private JFrame frame;
	private JPanel panelConsigne;
	private JLabel labelConsigne;
	private JPanel panelChoixModele;
	private ChoixModele choixModele;
	private JPanel panelChoixSpatialisation;
	//private ChoixSpatialisation choixSpatialisation;
	private JPanel panelBoutons;
	private JButton bLancer;
	private JButton bReinitialiser;
	private JButton bQuitter;
	private JDialog dialog;
	private JPanel panelDialog;
	private JLabel labelDialog;
	private JPanel panelCentre;
	private AffichageHist Affi;

	
	// constructeur :
	
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
        
		/*
        choixSpatialisation = new ChoixSpatialisation();
		panelChoixSpatialisation = new JPanel(new BorderLayout());
		panelChoixSpatialisation.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));
		choixSpatialisation.addComponentToPane(panelChoixSpatialisation);
		panelCentre.add(panelChoixSpatialisation);
		*/
		
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
	
    @Override
	public void actionPerformed(ActionEvent ae){
		dialog.dispose();
		dialog.setLocationRelativeTo(frame);
		if(ae.getSource() == bLancer){
			try{
				if(choixModele.getSelection() == ChoixModele.SCRUTIN_MAJORITAIRE_UN_TOUR){
					String[] tabString = choixModele.getMUTValues();
					int[] tabDouble = new int[3];
											
					for (int i = 0; i < tabString.length-1; i++){
						tabDouble[i] = Integer.parseInt(tabString[i]);
					}
											
					double getSeuil = Double.parseDouble(tabString[2]);

					ScrutinMajoritaireUnTour s1 = new ScrutinMajoritaireUnTour(tabDouble[0],tabDouble[1]);
					s1.setSeuil(getSeuil);
					int nbAbstention=s1.voter();
					s1.ToString(nbAbstention);
					System.out.println("electeur : "+s1.getNbElecteur()+" candidat: "+s1.getNbCandidat());
					if (s1.bonsParametres()){
						AffichageHist.createAndShowGui(s1.getNbVoix());
						s1.getCSV();
					} 
					else{
						labelDialog.setText("<html>Veuillez entrer un entier supérieur ou égal à 0 pour les nombres S0, E0, I0, et R0.<br>Veuillez entrer un réel compris entre 0 et 1 (inclus) pour les probabilités.</html>");
						dialog.setVisible(true);
					}
				}
                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_MAJORITAIRE_DEUX_TOUR){
					String[] tabString = choixModele.getMDTValues();
					int[] tabDouble = new int[3];
											
					for (int i = 0; i < tabString.length-1; i++){
						tabDouble[i] = Integer.parseInt(tabString[i]);
					}
											
					double getSeuil = Double.parseDouble(tabString[2]);

					ScrutinMajoritaireDeuxTours s2 = new ScrutinMajoritaireDeuxTours(tabDouble[0],tabDouble[1]);
					s2.setSeuil(getSeuil);
					int nbAbstention=s2.voter();
					System.out.println("electeur : "+s2.getNbElecteur()+" candidat: "+s2.getNbCandidat());
					if (s2.bonsParametres()){
						AffichageHist.createAndShowGui(s2.getNbVoix());
						s2.modifTabCandidat(s2.ToString1erTour(nbAbstention));      
						nbAbstention=s2.voter();
						s2.ToString2eTour(nbAbstention);
						AffichageHist.createAndShowGui(s2.getNbVoix());
						s2.getCSV();
					} else {
						labelDialog.setText("<html>Veuillez entrer un entier supérieur ou égal à 0 pour les nombres S0, E0, I0, et R0.<br>Veuillez entrer un réel compris entre 0 et 1 (inclus) pour les probabilités.</html>");
						dialog.setVisible(true);
					}
				}
                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_APPROBATION){
					String[] tabString = choixModele.getAPPROBATIONValues();
					int[] tabDouble = new int[3];
											
					for (int i = 0; i < tabString.length-1; i++){
						tabDouble[i] = Integer.parseInt(tabString[i]);
					}
											
					double getSeuil = Double.parseDouble(tabString[2]);
					
					ScrutinApprobation s3 = new ScrutinApprobation(tabDouble[0],tabDouble[1]);
					s3.setSeuil(getSeuil);                                                 
					int nbAbstention=s3.voter();
					
					s3.ToString(nbAbstention);
												
					System.out.println("electeur : "+s3.getNbElecteur()+" candidat: "+s3.getNbCandidat());
					if (s3.bonsParametres()){
						AffichageHist.createAndShowGui(s3.getNbVoix());
						s3.getCSV();
						
					}
					else{
						labelDialog.setText("<html>Veuillez entrer un entier supérieur ou égal à 0 pour les nombres S0, E0, I0, et R0.<br>Veuillez entrer un réel compris entre 0 et 1 (inclus) pour les probabilités.</html>");
						dialog.setVisible(true);
					}
				}
            	else if (choixModele.getSelection() == ChoixModele.SCRUTIN_BORDA){
											
					String[] tabString = choixModele.getBORDAValues();
					int[] tabDouble = new int[2];
											
					for (int i = 0; i < tabString.length; i++){
						tabDouble[i] = Integer.parseInt(tabString[i]);
					}

					ScrutinBorda s4 = new ScrutinBorda(tabDouble[0],tabDouble[1]);
					s4.voter();
					s4.ToString();
					System.out.println("electeur : "+s4.getNbElecteur()+" candidat: "+s4.getNbCandidat());
					if (s4.bonsParametres()){
						AffichageHist.createAndShowGui(s4.getNbVoix());
					} 
					else{
						labelDialog.setText("<html>Veuillez entrer un entier supérieur ou égal à 0 pour les nombres S0, E0, I0, et R0.<br>Veuillez entrer un réel compris entre 0 et 1 (inclus) pour les probabilités.</html>");
						dialog.setVisible(true);
					}
				}
                else if (choixModele.getSelection() == ChoixModele.SCRUTIN_ALTERNATIF){
					
					String[] tabString = choixModele.getALTERNATIFValues();
					int[] tabDouble = new int[2];
											
					for (int i = 0; i < tabString.length; i++){
						tabDouble[i] = Integer.parseInt(tabString[i]);
					}

					ScrutinAlternatif s5 = new ScrutinAlternatif(tabDouble[0],tabDouble[1]);
					s5.voter();
					s5.voterElimine();
					s5.ToString();
					System.out.println("electeur : "+s5.getNbElecteur()+" candidat: "+s5.getNbCandidat());
					if (s5.bonsParametres()){
						//AffichageHist.createAndShowGui(s5.getNbVoix());
					} 
					else{
						labelDialog.setText("<html>Veuillez entrer un entier supérieur ou égal à 0 pour les nombres S0, E0, I0, et R0.<br>Veuillez entrer un réel compris entre 0 et 1 (inclus) pour les probabilités.</html>");
						dialog.setVisible(true);
					}
				}
			} 
			catch (Exception e){
				labelDialog.setText("Veuillez entrer tous les paramètres nécessaires.");
				dialog.setVisible(true);
			}
		}
		else if (ae.getSource() == bReinitialiser){
			choixModele.reinitialiser();
		} 
		else{
			frame.dispose();
		}
	}
}