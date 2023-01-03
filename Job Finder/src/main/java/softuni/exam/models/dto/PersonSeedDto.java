package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.enums.StatusType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class PersonSeedDto {

    @Expose
    @Email
    private String email;

    @Expose
    @Size(min = 2, max = 30)
    private String firstName;

    @Expose
    @Size(min = 2, max = 30)
    private String lastName;

    @Expose
    @Size(min = 2, max = 13)
    private String phone;

    @Expose
    private StatusType statusType;

    @Expose
    private Long country;

    public String getEmail() {
        return email;
    }

    public PersonSeedDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonSeedDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonSeedDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PersonSeedDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public PersonSeedDto setStatusType(StatusType statusType) {
        this.statusType = statusType;
        return this;
    }

    public Long getCountry() {
        return country;
    }

    public PersonSeedDto setCountry(Long country) {
        this.country = country;
        return this;
    }
}
