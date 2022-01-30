

package Affichage;

import java.awt.Container;
import java.awt.event.ItemListener;

public abstract class Choix implements ItemListener {
	
	// méthodes :
	
	public abstract void reinitialiser();
	
	public abstract Object getSelection();
	
	public abstract void addComponentToPane(Container c);
	
}