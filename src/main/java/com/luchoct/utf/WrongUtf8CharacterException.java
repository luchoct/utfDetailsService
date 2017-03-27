package com.luchoct.utf;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongUtf8CharacterException extends IllegalArgumentException {

    public WrongUtf8CharacterException(final String character) {
        super("Expected one utf8 character. Found " + character);
    }
}
