package com.example.OrderService.external.client.decoder;

import com.example.OrderService.exception.QuantityErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper=new ObjectMapper();

        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());

        try {
            ErrorMessage errorMessage=objectMapper.readValue(response.body().asInputStream(), ErrorMessage.class);

            throw new QuantityErrorException(errorMessage.getMessage(),errorMessage.getHttpStatus());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
