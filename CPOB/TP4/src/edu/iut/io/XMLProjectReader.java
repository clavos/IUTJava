package edu.iut.io;

import java.io.IOException;
import java.util.ArrayList;

import edu.iut.app.ExamEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// EX 1 Completer la classe

public class XMLProjectReader {
	public XMLProjectReader() {

	}

	public ArrayList<ExamEvent> load(java.io.File xmlfile) throws IOException {
		ArrayList<ExamEvent> data = new ArrayList<ExamEvent>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlfile);
			System.out.println("version : " + document.getXmlVersion());
			System.out.println("encoding : " + document.getXmlEncoding());
			System.out.println("standalone : " + document.getXmlStandalone());
			System.out.println();
			Element root = document.getDocumentElement();
			NodeList rootChildren = root.getElementsByTagName("Event");

			for (int ci = 0; ci < rootChildren.getLength(); ci++) {
				System.out.println("Event : ");
				if (rootChildren.item(ci).getNodeType() == Node.ELEMENT_NODE) {
					Node child = (Element) rootChildren.item(ci);
					if (child.hasAttributes()) {
						NamedNodeMap attributes = child.getAttributes();

						for (int att_i = 0; att_i < attributes.getLength(); att_i++) {
							Attr attribute = (Attr) attributes.item(att_i);
							System.out.println(attribute);
						}

					}
					NodeList person = ((Element) child).getElementsByTagName("Personne");
					NodeList jury = ((Element) child).getElementsByTagName("Jury");
					NodeList documents = ((Element) child).getElementsByTagName("Documents");
					for (int si = 0; si < jury.getLength(); si++) {
						Node subNode = jury.item(si);
						Element titleElement = (Element) subNode;
					}
					for (int di = 0; di < person.getLength(); di++) {
						if (person.item(di).getNodeType() == Node.ELEMENT_NODE) {
							Node childs = (Element) person.item(di);
							if (childs.hasAttributes()) {
								NamedNodeMap attributes = childs.getAttributes();
								for (int att_i = 0; att_i < attributes.getLength(); att_i++) {
									Attr attribute = (Attr) attributes.item(att_i);
									System.out.println(attribute);
								}
							}

						}
					}
					System.out.println();
				}
			}

		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return data;

	}
}
