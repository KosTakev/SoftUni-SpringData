package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.*;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRIES_FILE_PATH =
            "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CountryServiceImpl(CountryRepository countryRepository,
                              ValidationUtil validationUtil,
                              ModelMapper modelMapper,
                              Gson gson) {
        this.countryRepository = countryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFileContent() throws IOException {
        return Files
                .readString(Path.of(COUNTRIES_FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        Arrays
                .stream(gson.fromJson(readCountriesFileContent(), CountrySeedDto[].class))
                .filter(countrySeedDto -> {
                    boolean isValid = validationUtil.isValid(countrySeedDto);
                    Optional<Country> countryByName = countryRepository
                            .findCountryByName(countrySeedDto.getName());
                    if (countryByName.isPresent()) {
                        return isValid = false;
                    }
                    Optional<Country> countryByCode = countryRepository
                            .findCountryByCode(countrySeedDto.getCode());
                    if (countryByCode.isPresent()) {
                        return isValid = false;
                    }
                    stringBuilder
                            .append(isValid
                                    ? String.format("Successfully imported country %s - %s",
                                    countrySeedDto.getName(),
                                    countrySeedDto.getCode())
                                    : "Invalid country")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(countrySeedDto -> modelMapper
                                .map(countrySeedDto, Country.class)
//                    Converter<String, String> toUpperCase = cxt ->
//                            cxt.getSource() == null ? null
//                            : cxt.getSource().toUpperCase();
//
//                    TypeMap<CountrySeedDto, Country> typeMap =
//                            modelMapper.createTypeMap(
//                                    CountrySeedDto.class, Country.class)
//                                    .addMappings(mapper ->
//                                            mapper.using(toUpperCase)
//                                                    .map(CountrySeedDto::getCountryCode,
//                                                            Country::setCode));
//                   return typeMap.map(new CountrySeedDto());
                )
                .forEach(countryRepository::save);
        return stringBuilder.toString();
    }

    @Override
    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }
}
