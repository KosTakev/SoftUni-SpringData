package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.JobSeedRootDto;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.service.JobService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class JobServiceImpl implements JobService {

    private static final String JOB_FILE_PATH = "src/main/resources/files/xml/jobs.xml";

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final JobRepository jobRepository;
    private final CompanyService companyService;

    public JobServiceImpl(XmlParser xmlParser, ModelMapper modelMapper,
                          ValidationUtil validationUtil, JobRepository jobRepository,
                          CompanyService companyService) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public boolean areImported() {
        return jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files
                .readString(Path.of(JOB_FILE_PATH));
    }

    @Override
    public String importJobs() throws IOException, JAXBException {

        StringBuilder stringBuilder = new StringBuilder();

        xmlParser
                .fromFile(JOB_FILE_PATH, JobSeedRootDto.class)
                .getJobs()
                .stream()
                .filter(jobSeedDto -> {
                    boolean isValid = validationUtil.isValid(jobSeedDto);

                    stringBuilder
                            .append(isValid
                            ? String.format("Successfully imported job %s",
                                    jobSeedDto.getJobTitle())
                                    : "Invalid job")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(jobSeedDto -> modelMapper
                        .map(jobSeedDto, Job.class))
                .forEach(jobRepository::save);
        return stringBuilder.toString();
    }

    @Override
    public String getBestJobs() {

        StringBuilder stringBuilder = new StringBuilder();

        jobRepository
                .findJobsBySalaryAndAndHoursAWeek()
                .forEach(job -> {
                    stringBuilder
                            .append(String.format(
                                    "Job title %s\n" +
                                            "-Salary: %s$\n" +
                                            "--Hours a week: %.2fh.\n",
                                    job.getTitle(),
                                    job.getSalary(),
                                    job.getHoursAWeek()
                            ))
                            .append(System.lineSeparator());
                });

        return stringBuilder.toString();
    }
}
