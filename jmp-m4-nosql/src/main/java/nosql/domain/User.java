package nosql.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * User
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
@Document
public class User {

    @Id
    private String id;

    @Field
    private String email;

    @Field
    private String fullName;

    @Field
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    @Field
    private GenderEnum gender;

    @Field
    private List<Sport> sports;

    public User(String email, String fullName, LocalDate birthDate, GenderEnum gender, List<Sport> sports) {
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.sports = sports;
    }

    public User() {
    }

    public User(String email, String fullName, LocalDate birthDate, GenderEnum gender) {
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(email, user.email) && fullName.equals(user.fullName) &&
            birthDate.equals(user.birthDate) && gender == user.gender && sports.equals(user.sports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, fullName, birthDate, gender, sports);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
            .add("id='" + id + "'")
            .add("email='" + email + "'")
            .add("fullName='" + fullName + "'")
            .add("birthDate=" + birthDate)
            .add("gender=" + gender)
            .add("sports=" + sports)
            .toString();
    }
}
