package ge.ufc.webapps.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.model.Persons;

@Path("/persons")
public interface PersonCrudService {

    @GET
    @Path("/getinfo")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response personByImdbId(@QueryParam("id") String imdbId);

    @GET
    @Path("/listpersons")
    @Produces({ "application/json", "application/xml" })
    Persons listPersons();

    @POST
    @Path("/addperson")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response addPerson(Person person);

    @PUT
    @Path("/updateinfo")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response updatePerson(Person person);

    @DELETE
    @Path("/deleteperson")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response deletePerson(@org.jboss.resteasy.annotations.jaxrs.PathParam("id") String imdbId);


}
