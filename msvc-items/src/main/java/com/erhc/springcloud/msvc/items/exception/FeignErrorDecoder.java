package com.erhc.springcloud.msvc.items.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    //    @Override
//    public Exception decode(String methodKey, Response response) {
//        if(response.status() == HttpStatus.NOT_FOUND.value()){
//            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found from Products service");
//        }
//        return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Unexpected error occurred");
//    }
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        switch (status) {
            case NOT_FOUND:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no encontrado en el servicio");
            case INTERNAL_SERVER_ERROR:
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en el servicio remoto");
            // Maneja otros errores según sea necesario
            default:
                return new Exception("Error no especificado en el servicio: " + status);
        }
    }
}
