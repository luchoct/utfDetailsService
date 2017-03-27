package com.luchoct.utf;

import com.luchoct.utf.junit.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@Category(IntegrationTest.class)
public class Utf8DetailsControllerIntegrationTest {

    private static final String BASE_URI = "http://localhost:8080";

    @Test
    public void getUtf8DetailsOfPoundSymbol() throws Exception {
        //given
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        final ResponseEntity<String> response = restTemplate.getForEntity(BASE_URI + "/utf8Details/£",
                String.class);
        //when and then
        assertThat(response.getStatusCode().is2xxSuccessful(), is(true));
        assertThat(response.getBody(), is("{\"unicode\":\"U+00A3\",\"dec\":\"194 163\",\"hex\":\"C2A3\"}"));
    }

    @Test
    public void getBadRequestOnSending2Characters() throws Exception {
        //given
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        //when
        try {
            restTemplate.getForEntity(BASE_URI + "/utf8Details/££", String.class);
        } catch (HttpClientErrorException cee) {
            //then
            assertThat(cee.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        }
    }
}