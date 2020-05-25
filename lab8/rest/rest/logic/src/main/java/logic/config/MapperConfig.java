package logic.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class MapperConfig {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
