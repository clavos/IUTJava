package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.ComboBox;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;

import edu.iut.app.ApplicationSession;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPanel;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	
	
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPanel) {

		this.agendaViewLayout = layerLayout;
		this.contentPanel = contentPanel;
		GridLayout grid = new GridLayout(0,1);
		/** EX3: REMPLACEMENT DU BOUTON NEXT */
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		String[] mois = {"Janvier", "Février", "Mars","Avril", "Mai","Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
		String[] jours = {"Lundi", "Mardi", "Mercredi","Jeudi", "Vendredi","Samedi", "Dimanche"};
		JComboBox comboBox = new JComboBox(mois);
		JComboBox comboBoxJ = new JComboBox(jours);
		comboBox.setSelectedItem(mois[currentMonth]);
		comboBoxJ.setSelectedItem(jours[currentDay]);
		SpinnerModel model =
		        new SpinnerNumberModel(currentYear,
		                               currentYear - 5,
		                               currentYear + 5,
		                               1); 
		JSpinner spinner = new JSpinner(model);
		this.setLayout(grid);
		this.add(comboBox);
		this.add(comboBoxJ);
		this.add(spinner);
	}
	
	public int getYear() {
		return selectedYear;
	}
	public int getMonth() {
		return selectedMonth;
	}
	public int getDay() {
		return selectedDay;
	}
	
}
