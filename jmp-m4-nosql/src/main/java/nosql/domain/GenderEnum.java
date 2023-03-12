package nosql.domain;

/**
 * GenderEnum
 * Date: 02/18/2023
 *
 * @author Yauheni Antsipenka
 */
public enum GenderEnum {

    MALE("MALE"),

    FEMALE("FEMALE");

    private final String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
