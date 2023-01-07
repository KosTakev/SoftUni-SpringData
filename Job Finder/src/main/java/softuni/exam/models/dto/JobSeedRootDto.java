package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobSeedRootDto {

    @XmlElement(name = "job")
    private List<JobSeedDto> jobs;

    public List<JobSeedDto> getJobs() {
        return jobs;
    }

    public JobSeedRootDto setJobs(List<JobSeedDto> jobs) {
        this.jobs = jobs;
        return this;
    }
}
