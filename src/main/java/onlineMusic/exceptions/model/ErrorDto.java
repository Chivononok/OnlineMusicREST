package onlineMusic.exceptions.model;

import lombok.Data;

@Data
public class ErrorDto {

    private String errorCode;
    private String errorMessage;

}
