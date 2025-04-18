package ru.bay.calculator.config.property;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplicationPropertiesMapper {
    ApplicationPropertiesMapper INSTANCE = Mappers.getMapper(ApplicationPropertiesMapper.class);

    ApplicationProperties copy(ApplicationProperties original);
}
