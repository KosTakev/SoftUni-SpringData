package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedDto {

    @XmlElement(name = "companyName")
    @Size(min = 2, max = 40)
    private String companyName;

    @XmlElement(name = "dateEstablished")
    private String dateEstablished;

    @XmlElement(name = "website")
    @Size(min = 2, max = 30)
    private String website;

    @XmlElement(name = "countryId")
    private Long countryId;

    public String getCompanyName() {
        return companyName;
    }

    public CompanySeedDto setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getDateEstablished() {
        return dateEstablished;
    }

    public CompanySeedDto setDateEstablished(String dateEstablished) {
        this.dateEstablished = dateEstablished;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CompanySeedDto setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Long getCountryId() {
        return countryId;
    }

    public CompanySeedDto setCountryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }
}
