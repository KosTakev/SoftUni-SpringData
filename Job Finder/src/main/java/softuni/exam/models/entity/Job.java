package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "hours_a_week", nullable = false)
    private Double hoursAWeek;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "jobs")
    private List<Company> companies;

    public Job() {
    }

    public String getTitle() {
        return title;
    }

    public Job setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Job setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Double getHoursAWeek() {
        return hoursAWeek;
    }

    public Job setHoursAWeek(Double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }
}
