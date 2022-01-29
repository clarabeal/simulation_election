package affichage;

import java.awt.*;
import java.awt.event.ItemEvent;
import javax.swing.*;

public class ChoixModele extends Choix {

	// attributs :
	
    private JTextField tfElecteurMUT, tfElecteurMDT, tfElecteurBORDA, tfElecteurAPPROBATION, tfElecteurALTERNATIF;
    private JTextField tfCandidatMUT, tfCandidatMDT, tfCandidatBORDA, tfCandidatAPPROBATION, tfCandidatALTERNATIF;
    private JTextField tfSeuilMUT, tfSeuilMDT, tfSeuilAPPROBATION;
    public final static String SCRUTIN_MAJORITAIRE_UN_TOUR = "Scrutin majoritaire à un tour";
    public final static String SCRUTIN_MAJORITAIRE_DEUX_TOUR = "Scrutin majoritaire à deux tour";
    public final static String SCRUTIN_APPROBATION = "Vote par approbation";
    public final static String SCRUTIN_BORDA = "Méthode de Borda";
    public final static String SCRUTIN_ALTERNATIF = "Vote alternatif";
    private String comboBoxItems[] = { SCRUTIN_MAJORITAIRE_UN_TOUR, SCRUTIN_MAJORITAIRE_DEUX_TOUR, SCRUTIN_APPROBATION, SCRUTIN_ALTERNATIF, SCRUTIN_BORDA };
	private JPanel panelComboBox;
    private JComboBox<String> comboBox;
    private JPanel cards;
    private JPanel card1;
    private JPanel card2;
    private JPanel card3;
    private JPanel card4;
    private JPanel card5;
    
    
    
    // constructeur :
    
    public ChoixModele() {
    	panelComboBox = new JPanel();
    	comboBox = new JComboBox<String>(comboBoxItems);
    	comboBox.setEditable(false);
    	comboBox.addItemListener(this);
        panelComboBox.add(comboBox);
         
        card1 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelElecteurMUT = new JLabel("Nombre d'électeurs");
        card1.add(labelElecteurMUT);
        JLabel labelCandMUT = new JLabel("Nombre de candidats");
        card1.add(labelCandMUT);
        JLabel labelSeuilMUT = new JLabel("Seuil de vote");
        card1.add(labelSeuilMUT);
        tfElecteurMUT = new JTextField();
        card1.add(tfElecteurMUT);
        tfCandidatMUT = new JTextField();
        card1.add(tfCandidatMUT);
        tfSeuilMUT = new JTextField();
        card1.add(tfSeuilMUT);
     
        card2 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelElecteurMDT = new JLabel("Nombre d'électeurs");
        card2.add(labelElecteurMDT);
        JLabel labelCandMDT = new JLabel("Nombre de candidats");
        card2.add(labelCandMDT);
        JLabel labelSeuilMDT = new JLabel("Seuil de vote");
        card2.add(labelSeuilMDT);
        tfElecteurMDT = new JTextField();
        card2.add(tfElecteurMDT);
        tfCandidatMDT = new JTextField();
        card2.add(tfCandidatMDT);    
        tfSeuilMDT = new JTextField();
        card2.add(tfSeuilMDT);
        
        card3 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelElecteurAPPROBATION = new JLabel("Nombre d'électeurs");
        card3.add(labelElecteurAPPROBATION);
        JLabel labelCandAPPROBATION = new JLabel("Nombre de candidats");
        card3.add(labelCandAPPROBATION);
        JLabel labelSeuilAPPROBATION = new JLabel("Seuil de vote");
        card3.add(labelSeuilAPPROBATION);
        tfElecteurAPPROBATION = new JTextField();
        card3.add(tfElecteurAPPROBATION);
        tfCandidatAPPROBATION = new JTextField();
        card3.add(tfCandidatAPPROBATION);
        tfSeuilAPPROBATION = new JTextField();
        card3.add(tfSeuilAPPROBATION);
        
        card4 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelElecteurBORDA = new JLabel("Nombre d'électeurs");
        card4.add(labelElecteurBORDA);
        JLabel labelCandBORDA = new JLabel("Nombre de candidats");
        card4.add(labelCandBORDA);
        tfElecteurBORDA = new JTextField();
        card4.add(tfElecteurBORDA);
        tfCandidatBORDA = new JTextField();
        card4.add(tfCandidatBORDA);

        
        card5 = new JPanel(new GridLayout(2, 5, 10, 0));
        JLabel labelElecteurALTERNATIF = new JLabel("Nombre d'électeurs");
        card5.add(labelElecteurALTERNATIF);
        JLabel labelCandALTERNATIF = new JLabel("Nombre de candidats");
        card5.add(labelCandALTERNATIF);
        tfElecteurALTERNATIF = new JTextField();
        card5.add(tfElecteurALTERNATIF);
        tfCandidatALTERNATIF = new JTextField();
        card5.add(tfCandidatALTERNATIF);
        
        
        cards = new JPanel(new CardLayout());
        cards.add(card1, SCRUTIN_MAJORITAIRE_UN_TOUR);
        cards.add(card2, SCRUTIN_MAJORITAIRE_DEUX_TOUR);
        cards.add(card3, SCRUTIN_APPROBATION);
        cards.add(card4, SCRUTIN_BORDA);
        cards.add(card5, SCRUTIN_ALTERNATIF);
        //cards.add(card5, SCRUTIN_ALTERNATIF);   
    }
    
    // méthodes :
    
    @Override
    public void reinitialiser(){
    	tfElecteurMUT.setText("");
        tfElecteurMDT.setText("");
    	tfElecteurAPPROBATION.setText("");
        tfElecteurBORDA.setText("");
    	tfElecteurALTERNATIF.setText("");
    	tfCandidatMUT.setText("");
    	tfCandidatMDT.setText("");
    	tfCandidatAPPROBATION.setText("");
        tfCandidatBORDA.setText("");
    	tfCandidatALTERNATIF.setText("");
        tfSeuilMUT.setText("");
        tfSeuilMDT.setText("");
        tfSeuilAPPROBATION.setText("");

    	comboBox.setSelectedItem(SCRUTIN_MAJORITAIRE_UN_TOUR);
    }
    
    @Override
    public Object getSelection(){
    	return comboBox.getSelectedItem();
    }
    
    public String[] getMUTValues(){
    	String[] tab = new String[3];
    	tab[0] = tfElecteurMUT.getText();
    	tab[1] = tfCandidatMUT.getText();
        tab[2] = tfSeuilMUT.getText();
    	return tab;
    }
    
    public String[] getMDTValues(){
    	String[] tab = new String[3];
    	tab[0] = tfElecteurMDT.getText();
    	tab[1] = tfCandidatMDT.getText();
        tab[2] = tfSeuilMDT.getText();

    	return tab;
    }
    
    public String[] getBORDAValues(){
    	String[] tab = new String[2];
    	tab[0] = tfElecteurBORDA.getText();
    	tab[1] = tfCandidatBORDA.getText();

    	return tab;
    }
    
    public String[] getAPPROBATIONValues(){
    	String[] tab = new String[3];
    	tab[0] = tfElecteurAPPROBATION.getText();
    	tab[1] = tfCandidatAPPROBATION.getText();
        tab[2] = tfSeuilAPPROBATION.getText();

    	return tab;
    }
    
    public String[] getALTERNATIFValues(){
    	String[] tab = new String[2];
    	tab[0] = tfElecteurALTERNATIF.getText();
    	tab[1] = tfCandidatALTERNATIF.getText();
    	return tab;
    }
    
    @Override
    public void addComponentToPane(Container c){
        c.add(panelComboBox, BorderLayout.PAGE_START);
        c.add(cards, BorderLayout.CENTER);
    }

    @Override
    public void itemStateChanged(ItemEvent evt){
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

}