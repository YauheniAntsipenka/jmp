package nosql.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * SportProfiencyEnum
 * Date: 03/01/2023
 *
 * @author Yauheni Antsipenka
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SportProfiencyEnum {

    TOP("TOP"),
    NOT_TOP("NOT_TOP");

    private final String profiency;

    SportProfiencyEnum(String profiency) {
        this.profiency = profiency;
    }

    public String getProfiency() {
        return profiency;
    }
}
