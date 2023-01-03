package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;

public class CountrySeedDto {


    @Expose
    @Size(min = 2, max = 30)
    private String name;


    @Expose
    @Size(min = 2, max = 19)
    private String code;


    @Expose
    @Size(min = 2, max = 19)
    private String currency;

    public CountrySeedDto() {
    }

    public String getName() {
        return name;
    }

    public CountrySeedDto setName(String name) {
        this.name = name;
        return this;
    }


    public String getCode() {
        return code;
    }


    public CountrySeedDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public CountrySeedDto setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
