import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
* Simple program to read RSS-feed from Kuntarekry page and list all the
* Kuopio citys open jobs in Kuntarekry.
*
***/
public class KuntarekryKuopio {
		
	public static void main(String[]args) throws IOException {
		// Kuopion kaupungin työpaikat Kuntarekry-järjestelmässä
		String url = "https://www.kuntarekry.fi/fi/tyopaikat/?organisation=12&lang=fi_FI&sort=-changetime&limit=500&format=rss";
		
		URL uri = new URL(url);
		//uri = uri.replaceFirst("\\<[^>]*>","");
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");
		
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		
		try {
			
			InputStream xml = conn.getInputStream();
			DocumentBuilder builder = fact.newDocumentBuilder();
			Document doc = builder.parse(xml);
			
			NodeList jobs = doc.getElementsByTagName("title");
			NodeList descr = doc.getElementsByTagName("description");
			NodeList links = doc.getElementsByTagName("link");

			
			for(int i = 0; i < jobs.getLength(); i++) {
				// Title
				String title = jobs.item(i).getTextContent();
				System.out.println("Title: " + title);
				// Description
				String desc = descr.item(i).getTextContent();
				desc = desc.replaceAll("\\<[^>]*>",""); // Get rid of html-tags
				System.out.println("Desc: " + desc);
				// Link
				String link = links.item(i).getTextContent();
				System.out.println("Link: " + link + "\n");
			}
			
		} catch(SAXException | IOException ie) {
			ie.printStackTrace();
		} catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
