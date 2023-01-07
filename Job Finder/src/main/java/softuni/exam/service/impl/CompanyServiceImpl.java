package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CompanySeedRootDto;
import softuni.exam.models.entity.Company;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final String COMPANIES_FILE_PATH = "src/main/resources/files/xml/companies.xml";

    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CountryService countryService;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              XmlParser xmlParser, ValidationUtil validationUtil,
                              ModelMapper modelMapper, CountryService countryService) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.countryService = countryService;
    }

    @Override
    public boolean areImported() {
        return companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files
                .readString(Path.of(COMPANIES_FILE_PATH));
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {

        StringBuilder stringBuilder = new StringBuilder();

        xmlParser
                .fromFile(COMPANIES_FILE_PATH, CompanySeedRootDto.class)
                .getCompanies()
                .stream()
                .filter(companySeedDto -> {
                    boolean isValid = validationUtil.isValid(companySeedDto);

                    Optional<Company> companyByName = companyRepository
                            .findCompanyByName(companySeedDto.getCompanyName());
                    if (companyByName.isPresent()) {
                        return isValid = false;
                    }

                    stringBuilder
                            .append(isValid
                            ? String.format("Successfully imported company %s - %d",
                                    companySeedDto.getCompanyName(),
                                    companySeedDto.getCountryId())
                            : "Invalid company")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(companySeedDto -> {
                    Company company = modelMapper.map(companySeedDto, Company.class);
                    return company;
                })
                .forEach(companyRepository::save);
        return stringBuilder.toString();
    }
}
