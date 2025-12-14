package fr.forum.faluchard.errors;

import lombok.Data;

@Data
public class ErrorResponse {
    
    private Integer errorCode;

    private String message;

    private Object details;

}
