package ge.ufc.webapps.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.model.Persons;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PersonCrudServiceImpl implements PersonCrudService {

    private Map<String, Person> inventory = new HashMap<>();

    public PersonCrudServiceImpl() {
        try
        {
            InputStream input = new FileInputStream("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\conf\\config.properties");
            Properties prop = new Properties();
            prop.load(input);
            String database = prop.getProperty("database");
            File file = new File(database);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("person");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                Element eElement = (Element) node;
                String a =(eElement.getElementsByTagName("id").item(0).getTextContent());
                String b = (eElement.getElementsByTagName("first-name").item(0).getTextContent());
                inventory.put(String.valueOf(a), new Person(String.valueOf(a), String.valueOf(b)));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Response personByImdbId(String imdbId) {
        if (inventory.containsKey(imdbId)) {
            return Response.status(Status.OK).entity(inventory.get(imdbId)).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @Override
    public Persons listPersons() {
        Persons persons = new Persons();
        persons.setPersons(inventory.values().stream().collect(Collectors.toCollection(ArrayList::new)));

        return persons;
    }

    @Override
    public Response addPerson(Person person) {
        if (null != inventory.get(person.getImdbId())) {
            return Response.status(Response.Status.CONFLICT).entity("Person is Already in the database.").build();
        }

        inventory.put(person.getImdbId(), person);

        Persons persons = new Persons();
        persons.setPersons(inventory.values().stream().collect(Collectors.toCollection(ArrayList::new)));

        return Response.status(Response.Status.CREATED).entity(persons).build();
    }

    @Override
    public Response updatePerson(Person person) {
        if (null == inventory.get(person.getImdbId())) {
            return Response.status(Response.Status.NOT_FOUND).entity("person is not in the database.\nUnable to Update")
                    .build();
        }

        inventory.put(person.getImdbId(), person);

        Persons persons = new Persons();
        persons.setPersons(inventory.values().stream().collect(Collectors.toCollection(ArrayList::new)));

        return Response.status(Response.Status.OK).entity(persons).build();
    }

    @Override
    public Response deletePerson(@PathParam("id") String imdbId) {
        if (null == inventory.get(imdbId)) {
            return Response.status(Response.Status.NOT_FOUND).entity("person is not in the database.\nUnable to Delete")
                    .build();
        }

        inventory.remove(imdbId);

        Persons persons = new Persons();
        persons.setPersons(inventory.values().stream().collect(Collectors.toCollection(ArrayList::new)));

        return Response.status(Response.Status.OK).entity(persons).build();
    }

}
