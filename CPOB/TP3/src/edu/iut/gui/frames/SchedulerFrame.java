package edu.iut.gui.frames;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.iut.gui.widget.agenda.AgendaPanelFactory;
import edu.iut.gui.widget.agenda.ControlAgendaViewPanel;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class SchedulerFrame extends JFrame {
	JPanel contentPanel;
	CardLayout layerLayout;
	AgendaPanelFactory agendaPanelFactory;
	JPanel dayView;
	JPanel weekView;
	JPanel monthView;

	protected void setupUI() {

		contentPanel = new JPanel();
		layerLayout = new CardLayout();
		contentPanel.setLayout(layerLayout);
		ControlAgendaViewPanel agendaViewPanel = new ControlAgendaViewPanel(layerLayout, contentPanel);
		agendaPanelFactory = new AgendaPanelFactory();
		dayView = agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW);
		weekView = agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW);
		monthView = agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW);

		contentPanel.add(dayView, ActiveView.DAY_VIEW.name());
		contentPanel.add(weekView, ActiveView.WEEK_VIEW.name());
		contentPanel.add(monthView, ActiveView.MONTH_VIEW.name());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				agendaViewPanel, contentPanel);
		this.setContentPane(splitPane);

		JMenuBar menuBar = new JMenuBar();
		JMenu file, help, edit, view;
		JMenuItem load, save, quit, month, week, day, display, about;

		file = new JMenu("File");
		edit = new JMenu("Edit");
		view = new JMenu("View");
		help = new JMenu("Help");

		load = new JMenuItem("Load");
		save = new JMenuItem("Save");
		quit = new JMenuItem("Quit");
		month = new JMenuItem("Month");
		week = new JMenuItem("Week");
		day = new JMenuItem("Day");
		display = new JMenuItem("Display");
		about = new JMenuItem("About");
		// /////////////////////////////////////////ActionListener\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		JOptionPane jop = new JOptionPane();

		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jop.showMessageDialog(null, "Fonctionnalité non implémentée","Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jop.showMessageDialog(null, "Fonctionnalité non implémentée","Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jop.showMessageDialog(null, "Fonctionnalité non implémentée","Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jop.showMessageDialog(null, "Fonctionnalité non implémentée","Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        System.exit(0);

			}
		});
		
		day.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.next(contentPanel);
			}
		});
		day.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.next(contentPanel);
			}
		});
		month.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.next(contentPanel);
			}
		});
		week.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.next(contentPanel);
			}
		});

		file.add(load);
		file.add(save);
		file.add(quit);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		edit.add(view);
		view.add(month);
		view.add(week);
		view.add(day);
		help.add(display);
		help.add(about);
		this.setJMenuBar(menuBar);
		this.pack();
		layerLayout.next(contentPanel);
	}

	public SchedulerFrame() {
		super();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		contentPanel = null;
		dayView = null;
		weekView = null;
		monthView = null;
		agendaPanelFactory = null;
		setupUI();

	}

	public SchedulerFrame(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setupUI();
	}

}
