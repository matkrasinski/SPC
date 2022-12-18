package cloud.lambdas.dto;

import cloud.lambdas.pojo.City;

public class Mapper {

    public CityDto mapCity(City city) {
        return new CityDto(city.getName());
    }
}
