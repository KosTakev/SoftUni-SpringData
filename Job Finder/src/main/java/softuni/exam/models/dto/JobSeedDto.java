package softuni.exam.models.dto;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class JobSeedDto {

    @XmlElement(name = "jobTitle")
    @Size(min = 2, max = 40)
    private String jobTitle;

    @XmlElement(name = "hoursAWeek")
    @DecimalMin("10.00")
    private Double hoursAWeek;

    @XmlElement(name = "salary")
    @DecimalMin("300.00")
    private BigDecimal salary;

    @XmlElement(name = "description")
    @Size(min = 5)
    private String description;

    @XmlElement(name = "companyId")
    private Long companyId;

    public String getJobTitle() {
        return jobTitle;
    }

    public JobSeedDto setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public Double getHoursAWeek() {
        return hoursAWeek;
    }

    public JobSeedDto setHoursAWeek(Double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public JobSeedDto setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public JobSeedDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public JobSeedDto setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
}
