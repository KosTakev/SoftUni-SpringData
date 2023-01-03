package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonSeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.models.entity.enums.StatusType;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.CountryService;
import softuni.exam.service.PersonService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private static final String PEOPLE_FILE_PATH = "src/main/resources/files/json/people.json";
    private final PersonRepository personRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CountryService countryService;

    public PersonServiceImpl(PersonRepository personRepository,
                             Gson gson,
                             ModelMapper modelMapper,
                             ValidationUtil validationUtil,
                             CountryService countryService) {
        this.personRepository = personRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.countryService = countryService;
    }

    @Override
    public boolean areImported() {
        return personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files
                .readString(Path.of(PEOPLE_FILE_PATH));
    }

    @Override
    public String importPeople() throws IOException, JAXBException {

        StringBuilder stringBuilder = new StringBuilder();

        Arrays
                .stream(gson.fromJson(readPeopleFromFile(), PersonSeedDto[].class))
                .filter(personSeedDto -> {
                    boolean isValid = validationUtil.isValid(personSeedDto);
                    Optional<Person> personByFirstName = personRepository
                            .findPersonByFirstName(personSeedDto.getFirstName());
                    Optional<Person> personByEmail = personRepository
                            .findPersonByEmail(personSeedDto.getEmail());
                    if (personByFirstName.isPresent()) {
                        isValid = false;
                    }
                    if (personByEmail.isPresent()) {
                        isValid = false;
                    }
                    stringBuilder
                            .append(isValid ?
                                    String.format("Successfully imported person %s %s",
                                            personSeedDto.getFirstName(),
                                            personSeedDto.getLastName())
                                    : "Invalid person")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(personSeedDto -> {
                    Person person =
                    modelMapper.map(personSeedDto, Person.class);
                    Optional<Country> countryById = countryService.getCountryById(personSeedDto.getCountry());
                    if (countryById.isEmpty()) {
                        System.out.println("ERROR:  " + personSeedDto.getCountry());
                        return person;
                    }
                    person.setCountry(countryById.get());
                    return person;
                })
                .forEach(personRepository::save);
        return stringBuilder.toString();

    }
}
