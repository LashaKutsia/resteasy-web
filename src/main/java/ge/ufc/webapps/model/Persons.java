package ge.ufc.webapps.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persons {
    private List<Person> tutorial;

    public List<Person> getPersons() {
        return tutorial;
    }

    @XmlElement
    public void setPersons(List<Person> tutorial) {
        this.tutorial = tutorial;
    }
}
