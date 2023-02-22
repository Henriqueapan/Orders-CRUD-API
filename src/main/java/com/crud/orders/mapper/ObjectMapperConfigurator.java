package com.crud.orders.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.inject.Singleton;

@Singleton
public class ObjectMapperConfigurator implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        // Utilized in UpdateDeliveryStatusDTO
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }
}
