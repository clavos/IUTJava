package edu.iut.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import edu.iut.app.Agenda;
import edu.iut.app.Classroom;
import edu.iut.app.CommandLineOption;
import edu.iut.app.CommandLineParser;
import edu.iut.app.Document;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.io.XMLProjectReader;
import edu.iut.io.XMLProjectWriter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class IUTScheduler {

	public static void main(String[] args) {
		Locale.setDefault(Locale.FRANCE);
		Person student = new Person(PersonFunction.STUDENT,"student1","student1","0606060606","test@hotmail.fr");
		Person jury1 = new Person(PersonFunction.JURY,"jury1","jury1","657484684","jury1@hotmail.fr");
		Person jury2 = new Person(PersonFunction.JURY,"jury2","jury2","68464649","jury2@hotmail.fr");
		
		ArrayList<Person> jury = new ArrayList<Person>();
		jury.add(jury1);
		jury.add(jury2);
		
		Classroom classRoom = new Classroom("I208");
		
		Document document1 = new Document("https//cours.iut-orsay.fr");
		Document document2 = new Document("http://wikipedia.fr");
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(document1);
		documents.add(document2);
		
		Date date = new Date();
		
		ExamEvent event1 = new ExamEvent(date,student,jury,classRoom, documents);
		ExamEvent event2 = new ExamEvent(date,student,jury,classRoom, documents);
		ArrayList<ExamEvent> agenda = new ArrayList<ExamEvent>();
		agenda.add(event1);
		agenda.add(event2);
		
		
		XMLProjectWriter writer = new XMLProjectWriter();
		XMLProjectReader reader = new XMLProjectReader();
		java.io.File xmlfile = new java.io.File("save.xml");
		writer.save(agenda, xmlfile);
		
		try {
			reader.load(xmlfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		Locale.setDefault(Locale.FRANCE);
		
		
		
		CommandLineParser commandLineParser = new CommandLineParser();
		CommandLineOption<java.io.File> configOption = new CommandLineOption<java.io.File>(CommandLineOption.OptionType.FILE, 
				                                                                           "config","configuration file",
				                                                                           new java.io.File("/tmp"));
		CommandLineOption<String> localeOption = new CommandLineOption<String>(CommandLineOption.OptionType.STRING, 
																			   "locale","en|fr",
																			   new String("fr"));

		CommandLineOption<java.io.File> projectOption = new CommandLineOption<java.io.File>(CommandLineOption.OptionType.FILE, 
				   																			"project file","xml project file",
				   																		    new java.io.File("/tmp"));
		
		commandLineParser.addOption(configOption);
		commandLineParser.addOption(localeOption);
		commandLineParser.addOption(projectOption);
		
		
		commandLineParser.parse(args);
		
		
		
		System.err.println("Option:"+commandLineParser.getOption("config").getValue());
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        JFrame mainFrame = new edu.iut.gui.frames.SchedulerFrame("IUT Scheduler");
		        mainFrame.setVisible(true);		        
		    }
		});*/
		
	}
	
}
