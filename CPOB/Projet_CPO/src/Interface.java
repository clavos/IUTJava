import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	public static Session session;
	public static JPanel panel;
	
	Interface() {
		session = new Session();
		this.setTitle("Gestion des Stages");
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		this.setContentPane(panel);
		login();

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
	    this.setSize(500, 150);

		JLabel userLabel = new JLabel("Votre Soutenance est :  "+session.soutenance.periode+" "+session.soutenance.jour+" "+session.soutenance.mois);
		userLabel.setBounds(10, 10, 450, 25);
		panel.add(userLabel);
		
		JButton change = new JButton("Modifier");
		change.setBounds(180, 80, 110, 25);
		panel.add(change);
		
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifSoutenance();
				set();

			}
		});
	}
	// Quand un utilisateur modifie sa date de soutenance
	private void modifSoutenance (){
		panel.removeAll();
		String[] periodeString = { "Le matin","L Apr�s-midi"};
		String[] moisString = { "Juin","Juillet"};
		String[] jourString = { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};


		   JComboBox periode = new JComboBox(periodeString);
		   JComboBox jour = new JComboBox(jourString);
		   JComboBox mois = new JComboBox(moisString);
		   JLabel label = new JLabel("Votre date :");
		   JLabel labell = new JLabel(" du ");





		   
		    this.setSize(500, 150);
		    panel.setLayout(new BorderLayout());
		    periode.setPreferredSize(new Dimension(100, 20));
		    jour.setPreferredSize(new Dimension(100, 20));
		    mois.setPreferredSize(new Dimension(100, 20));

		    JButton save = new JButton("Enregistrer");
		    save.setBounds(180, 80, 110, 25);
			panel.add(save);
			
			save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String periodeS = (String) periode.getSelectedItem();
					String jourS = (String)jour.getSelectedItem();
					String moisS = (String)mois.getSelectedItem();

					OutilsJDBC.insererSoutenance(session,periodeS,jourS,moisS);
					afficherSoutenance();
					set();
				}
			});
			
		    JPanel top = new JPanel();
		    top.add(label);
		    top.add(periode);
		    top.add(labell);
		    top.add(jour);
		    top.add(mois);
		    panel.add(save);
		    panel.add(top, BorderLayout.CENTER);
	}
	
	// Quand un utilisateur se connecte via l'application
	private void login() {
		panel.removeAll();
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Login :");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(180, 80, 80, 25);
		panel.add(exitButton);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				session.login =userText.getText();
				session.mdp =passwordText.getText();
				if(OutilsJDBC.login(session)){
				afficherSoutenance();
				set();}
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}


}
