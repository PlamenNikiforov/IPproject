package org.elsys.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.elsys.entities.Estate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;






@Path("/rest")
public class Rest {
	final static List<Estate> Estates = new ArrayList<Estate>();
	
	@GET
	@Path("/showall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estate> showAllEstates() throws ParserConfigurationException, SAXException, IOException{
		Estates.clear();
		final Document document = openDocument("plamen/Jersey/WebContent/estates.xml");
		Node root = document.getFirstChild();
		NodeList estates = root.getChildNodes();
		int count = 0;
		for (int i = 0; i < estates.getLength(); i++) {
			Node estate = estates.item(i);
			if (estate.getNodeType() == 1) {
				String name = estate.getAttributes().getNamedItem("name").getNodeValue();
				NodeList city = document.getElementsByTagName("city");
				String cityName = city.item(count).getTextContent();
				Estate foundEstate = new Estate();
				foundEstate.setName(name);
				foundEstate.setCity(cityName);
				Estates.add(foundEstate);
				count++;
				}
			}
		return Estates;
	}
	
	@GET
	@Path("/search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estate> search(@PathParam("name") String name) throws ParserConfigurationException, SAXException, IOException{
		Estates.clear();
		final Document document = openDocument("plamen/Jersey/WebContent/estates.xml");
		Node root = document.getFirstChild();
		NodeList estates = root.getChildNodes();
		int count = 0;
		for (int i = 0; i < estates.getLength(); i++) {
			Node estate = estates.item(i);
			if (estate.getNodeType() == 1) {
				String estateName = estate.getAttributes().getNamedItem("name").getNodeValue();
				if(estateName.equals(name)){
					NodeList city = document.getElementsByTagName("city");
					String cityName = city.item(count).getTextContent();
					Estate foundEstate = new Estate();
					foundEstate.setName(name);
					foundEstate.setCity(cityName);
					Estates.add(foundEstate);
					count++;
				}else{
					count++;
				}
			}
		}
		
		return Estates;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEstate(Estate Estate) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		
		final Document document = openDocument("plamen/Jersey/WebContent/estates.xml");
		Element estate =  document.createElement("estate");
		Element address =  document.createElement("address");
		Element cityel =  document.createElement("city");
		address.setTextContent(" ");
		
		cityel.setTextContent(Estate.getCity());
		
		estate.setTextContent(" ");
		estate.setAttribute("name", Estate.getName());
		estate.appendChild(address);
		address.appendChild(cityel);
		
		document.getFirstChild().appendChild((Node) estate);
		saveDocument(document, "plamen/Jersey/WebContent/estates.xml");
	}
	
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
}
