package org.elsys.entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement
public class Estate {
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String city;

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	
}