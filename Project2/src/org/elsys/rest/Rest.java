package org.elsys.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.elsys.entities.Estate;





@Path("/rest")
public class Rest {
	final static List<Estate> Estates = new ArrayList<Estate>();
	
	@GET
	@Path("/showall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estate> showAllEstates(){
		return Estates;
	}
	
	@GET
	@Path("/search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estate> search(@PathParam("name") String name){
		final List<Estate> results = new ArrayList<Estate>();
		
		for (int i = 0; i < Estates.size(); i++) {
			if(Estates.get(i).getName().equals(name)){ 
				results.add(Estates.get(i));
			}
		}
		
		return results;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEstate(Estate Estate) {
		Estates.add(Estate);
	}
}
