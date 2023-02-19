package nosql.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Objects;
import java.util.UUID;

/**
 * Sport
 * Date: 02/19/2023
 *
 * @author Yauheni Antsipenka
 */
@Document
public class Sport {

    @Id
    private String id;

    @Field
    private String sportName;

    @Field
    private String sportProficiency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportProficiency() {
        return sportProficiency;
    }

    public void setSportProficiency(String sportProficiency) {
        this.sportProficiency = sportProficiency;
    }

    public Sport(String sportName, String sportProficiency) {
        this.id = UUID.randomUUID().toString();
        this.sportName = sportName;
        this.sportProficiency = sportProficiency;
    }

    public Sport() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return id.equals(sport.id) && sportName.equals(sport.sportName) && sportProficiency.equals(sport.sportProficiency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sportName, sportProficiency);
    }
}
