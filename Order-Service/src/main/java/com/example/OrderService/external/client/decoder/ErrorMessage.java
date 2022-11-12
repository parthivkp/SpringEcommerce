package com.example.OrderService.external.client.decoder;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ErrorMessage {
private String message;
private HttpStatus httpStatus;
public ErrorMessage(String m, HttpStatus s) {
	message=m;
	httpStatus=s;
}

}
