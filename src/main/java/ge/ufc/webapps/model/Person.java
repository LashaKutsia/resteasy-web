package ge.ufc.webapps.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = { "id", "First-Name" })
public class Person {

    protected String imdbId;
    protected String title;

    public Person(String imdbId, String title) {
        this.imdbId = imdbId;
        this.title = title;
    }

    public Person() {
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person person = (Person) o;

        if (imdbId != null ? !imdbId.equals(person.imdbId) : person.imdbId != null)
            return false;
        return title != null ? title.equals(person.title) : person.title == null;

    }

    @Override
    public int hashCode() {
        int result = imdbId != null ? imdbId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" + "ID='" + imdbId + '\'' + ", First-Name='" + title + '\'' + '}';
    }

}

