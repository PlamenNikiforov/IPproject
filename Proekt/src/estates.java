import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class estates {
	
	private static Document openDocument(String filename)
			throws ParserConfigurationException, SAXException, IOException {

		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		final DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new File(filename));
	}

	private static void saveDocument(Document document, String filename)
			throws FileNotFoundException, TransformerException {
		final TransformerFactory factory = TransformerFactory.newInstance();
		
		final Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		final StreamResult out = new StreamResult(new FileOutputStream(
				new File(filename)));
		transformer.transform(new DOMSource(document), out);
	}
	
	private static void addEstate(String name, String city) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		final Document document = openDocument("WebContent/input.xml");
		Element estate =  document.createElement("estate");
		Element address =  document.createElement("address");
		Element cityel =  document.createElement("city");
		address.setTextContent(" ");
		
		cityel.setTextContent(city);
		
		estate.setTextContent(" ");
		estate.setAttribute("name", name);
		estate.appendChild(address);
		address.appendChild(cityel);
		
		document.getFirstChild().appendChild((Node) estate);
		saveDocument(document, "WebContent/input.xml");
	}
	
	private static void getEstate(String name) throws ParserConfigurationException, SAXException, IOException{
		final Document document = openDocument("WebContent/input.xml");
		Node root = document.getFirstChild();
		NodeList estates = root.getChildNodes();
		int count = 0;
		for (int i = 0; i < estates.getLength(); i++) {
			Node estate = estates.item(i);
			if (estate.getNodeType() == 1) {
				String estateName = estate.getAttributes().getNamedItem("name").getNodeValue();
				if(estateName.equals(name)){
					System.out.print(estateName+" ");
					NodeList city = document.getElementsByTagName("city");
					String cityName = city.item(count).getTextContent();
					System.out.println(cityName);
					count++;
				}
			}
		}
		
	}
	private static void getAllEstates() throws ParserConfigurationException, SAXException, IOException{
		final Document document = openDocument("WebContent/input.xml");
		Node root = document.getFirstChild();
		NodeList estates = root.getChildNodes();
		int count = 0;
		for (int i = 0; i < estates.getLength(); i++) {
			Node estate = estates.item(i);
			if (estate.getNodeType() == 1) {
				String name = estate.getAttributes().getNamedItem("name").getNodeValue();
				System.out.print(name+" ");
				NodeList city = document.getElementsByTagName("city");
				String cityName = city.item(count).getTextContent();
				System.out.println(cityName);
				count++;
				}
			}
	}
	
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TransformerException {
		//addEstate("Estate1", "Sofia");
		//addEstate("Estate2", "Plovdiv");
		//getAllEstates();
		//getEstate("Estate2");
	}
	

}
