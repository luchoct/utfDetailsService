package com.luchoct.utf;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WrongUtf8CharacterExceptionTest {

    @Test
    public void constructorSetsRightMessage() {
        final WrongUtf8CharacterException exception = new WrongUtf8CharacterException("anycharacter");
        assertThat(exception.getMessage(), is("Expected one utf8 character. Found anycharacter"));
    }
}