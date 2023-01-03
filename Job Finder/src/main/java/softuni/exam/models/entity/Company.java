package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "date_established")
    private LocalDate dateEstablished;

    @ManyToMany
    private List<Job> jobs;

    @ManyToOne
    private Country country;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Company setWebsite(String website) {
        this.website = website;
        return this;
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public Company setDateEstablished(LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
        return this;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Company setJobs(List<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public Company setCountry(Country country) {
        this.country = country;
        return this;
    }
}
