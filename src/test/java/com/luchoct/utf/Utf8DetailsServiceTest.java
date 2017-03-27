package com.luchoct.utf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class Utf8DetailsServiceTest {

    @Autowired
    private Utf8DetailsService service;

        @Test
    public void getUtf8DetailsOfPoundSymbol() {
        final Utf8Details utf8Details = service.getDetails("£");
        assertThat(utf8Details, samePropertyValuesAs(
                new Utf8Details("U+00A3", "194 163", "C2A3")));
    }

    @Test
    public void getUtf8DetailsOfLinearBSyllableB008A() {
        final Utf8Details utf8Details = service.getDetails("𐀀");
        //The surrogate pair D800 + DC00 is hexadecimal unicode 10000 of character 𐀀
        //The surrogate pair D800 + DC00 is hexadecimal 0xF0908080 of character 𐀀
        //The surrogate pair D800 + DC00 is decimal 4036001920 of character 𐀀
        assertThat(utf8Details, samePropertyValuesAs(
                new Utf8Details("U+10000", "240 144 128 128", "F0908080")));
    }

    @Test(expected = WrongUtf8CharacterException.class)
    public void shouldThrowWrongUtf8CharacterExceptionIfGiven2Characters() {
        service.getDetails("££");
    }

    @Test(expected = WrongUtf8CharacterException.class)
    public void shouldThrowUtf8CharacterExceptionIfNoCharacterGiven() {
        service.getDetails("");
    }
}