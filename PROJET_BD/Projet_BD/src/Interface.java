import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Interface extends JFrame {
	public static Stage stage;
	public static JPanel panel;
	public static JLabel lb5 = new JLabel(" : ");
	public static JLabel lb8 = new JLabel(" ann�e : ");
	public static JLabel lb10 = new JLabel(" ann�e : ");
	public static JLabel lb12 = new JLabel(" : ");
	public static String[] tableauEntre;
	public static String[] tableauVille;

	
	
	Interface() {
		tableauEntre = new String[300];
		tableauVille = new String[300];

		stage = new Stage();
		this.setTitle("Statistiques des Stages");
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		this.setContentPane(panel);
		afficherSoutenance();
		set();

	}

	public void ajout(String s) {
		JLabel test = new JLabel(s);
		panel.add(test);

	}

	public void set() {
		this.setVisible(true);
		this.setResizable(false);

	}

	private void afficherSoutenance (){
		panel.removeAll();
		panel.setLayout(null);
		OutilsJDBC.nbEtudiantR();
		OutilsJDBC.nbStage();
		OutilsJDBC.nbNonStage();
		OutilsJDBC.entreprise();
		OutilsJDBC.ville();


		JLabel lb1 = new JLabel("Nombre d'�tudiants : "+  stage.nbEtudiant);
		JLabel lb2 = new JLabel("Nombre d'�tudiants avec un stage : "+  stage.nbStage);
		JLabel lb3 = new JLabel("Nombre d'�tudiants sans stage : "+  stage.nbNonStage);
		JLabel lb4 = new JLabel("Nombre d'�tudiants sans stage en ");
		JLabel lb6 = new JLabel("Nombre d'�tudiants en stage chez ");
		JLabel lb7 = new JLabel(" depuis ");
		JLabel lb9 = new JLabel("Nombre moyen d'�tudiants ayant un stage depuis ");
		JLabel lb11 = new JLabel("Nombre d'�tudiants ayant un stage dans la ville ");


		JComboBox<String> comboStage = new JComboBox();
		comboStage.addItem("2010");
		comboStage.addItem("2011");
		comboStage.addItem("2012");
		comboStage.addItem("2013");
		comboStage.addItem("2014");
		comboStage.addItem("2015");
		comboStage.addItem("2016");
		comboStage.setSelectedIndex(1);
		lb5 = new JLabel(" : "+ OutilsJDBC.nbNonStageD((String)comboStage.getSelectedItem()));

		class ItemAction implements ActionListener{
			public void actionPerformed(ActionEvent e){
				lb5.setText(" : "+ OutilsJDBC.nbNonStageD((String)comboStage.getSelectedItem()));
				
			}
		};
		comboStage.addActionListener(new ItemAction());

		JComboBox<String> comboEntreprise = new JComboBox();
		for (int i=0; i<stage.nbEntre; i++){
			comboEntreprise.addItem(tableauEntre[i]);
		}
		comboEntreprise.setSelectedIndex(1);

		
		
		
		JComboBox<String> comboAnnee = new JComboBox();
		comboAnnee.addItem("1");
		comboAnnee.addItem("2");
		comboAnnee.addItem("3");
		comboAnnee.addItem("4");
		comboAnnee.addItem("5");
		comboAnnee.addItem("6");
		comboAnnee.addItem("7");
		comboAnnee.setSelectedIndex(1);
		OutilsJDBC.nbStageEntr(comboAnnee.getSelectedIndex(), (String)comboEntreprise.getSelectedItem());
		lb8 = new JLabel(" ann�e : "+ stage.nbStageEntre);
		class ItemAction2 implements ActionListener{
			public void actionPerformed(ActionEvent e){
				OutilsJDBC.nbStageEntr(comboAnnee.getSelectedIndex(), (String)comboEntreprise.getSelectedItem());
				lb8.setText(" ann�e : "+ stage.nbStageEntre);
				
			}
		};
		comboAnnee.addActionListener(new ItemAction2());
		
		class ItemAction3 implements ActionListener{
			public void actionPerformed(ActionEvent e){
				OutilsJDBC.nbStageEntr(comboAnnee.getSelectedIndex(), (String)comboEntreprise.getSelectedItem());
				lb8.setText(" ann�e : "+ stage.nbStageEntre);
				
			}
		};
		comboEntreprise.addActionListener(new ItemAction3());

		
		
		JComboBox<String> comboMoyenne = new JComboBox();
		comboMoyenne.addItem("1");
		comboMoyenne.addItem("2");
		comboMoyenne.addItem("3");
		comboMoyenne.addItem("4");
		comboMoyenne.addItem("5");
		comboMoyenne.addItem("6");
		comboMoyenne.addItem("7");
		comboMoyenne.setSelectedIndex(1);
		OutilsJDBC.nbStageEntr(comboAnnee.getSelectedIndex(), (String)comboEntreprise.getSelectedItem());
		lb10 = new JLabel(" ann�e : "+ stage.nbMoyenne);
		class ItemAction4 implements ActionListener{
			public void actionPerformed(ActionEvent e){
				OutilsJDBC.nbStageMoyen(comboMoyenne.getSelectedIndex());
				lb10.setText(" ann�e : "+ stage.nbMoyenne);
				
			}
		};
		comboMoyenne.addActionListener(new ItemAction4());

		
		JComboBox<String> comboVille = new JComboBox();
		for (int i=0; i<stage.nbEntre; i++){
			comboVille.addItem(tableauVille[i]);
		}
		comboVille.setSelectedIndex(1);
		OutilsJDBC.nbStageVille((String)comboVille.getSelectedItem());
		lb12 = new JLabel(" : "+ stage.nbVille);
		class ItemAction5 implements ActionListener{
			public void actionPerformed(ActionEvent e){
				OutilsJDBC.nbStageVille((String)comboVille.getSelectedItem());
				lb12.setText(" : "+ stage.nbVille);
				
			}
		};
		comboVille.addActionListener(new ItemAction5());

		lb1.setBounds(10, 10, 450, 25);
		lb2.setBounds(10, 40, 450, 25);
		lb3.setBounds(10, 70, 450, 25);
		lb4.setBounds(10, 100, 450, 25);
		comboStage.setBounds(220, 105, 100, 20);
		lb5.setBounds(324, 100, 450, 25);
		lb6.setBounds(10, 130, 450, 25);
		comboEntreprise.setBounds(220, 135, 100, 20);
		lb7.setBounds(324, 130, 450, 25);
		comboAnnee.setBounds(370, 135, 100, 20);
		lb8.setBounds(474, 130, 450, 25);
		lb9.setBounds(10, 160, 450, 25);
		comboMoyenne.setBounds(300, 165, 100, 20);
		lb10.setBounds(404, 160, 450, 25);
		lb11.setBounds(10, 190, 450, 25);
		comboVille.setBounds(300, 195, 100, 20);
		lb12.setBounds(405, 190, 450, 25);


		panel.add(comboStage);
		panel.add(comboEntreprise);
		panel.add(comboAnnee);
		panel.add(comboMoyenne);
		panel.add(comboVille);

		panel.add(lb1);
		panel.add(lb2);
		panel.add(lb3);
		panel.add(lb4);
		panel.add(lb5);
		panel.add(lb6);
		panel.add(lb7);
		panel.add(lb8);
		panel.add(lb9);
		panel.add(lb10);
		panel.add(lb11);
		panel.add(lb12);
		

		Courbe courbe;
		courbe=new Courbe();
		 
		courbe.ajouterPoint(new Point(5, 6681.8929));
		courbe.ajouterPoint(new Point(25, 11834.3456));
		courbe.ajouterPoint(new Point(45, 37059.7267));
		courbe.ajouterPoint(new Point(65, 32249.5167));
		courbe.ajouterPoint(new Point(85, 11503.6712));
		courbe.ajouterPoint(new Point(105, 7485.3936));
		courbe.ajouterPoint(new Point(125, 5720.6952));
		courbe.setBounds(30, 270, 400, 300);
		panel.add(courbe);
 

		set();
		
	}
	
	
	

}
