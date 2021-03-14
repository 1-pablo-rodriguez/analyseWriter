package MEPTL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class fenetre extends JFrame {
	
	private JButton bouton = new JButton("Mon bouton");
	private JPanel conteneur = new JPanel();
	private JTextField texte = new JTextField("zone de de texte");
	

	fenetre(){
		setTitle("analyseWriter");
		setSize(400,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		conteneur.add(bouton);
		conteneur.add(texte);
		setContentPane(conteneur);
	}
	
	
}
