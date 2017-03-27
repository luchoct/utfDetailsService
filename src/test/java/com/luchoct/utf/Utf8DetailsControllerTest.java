package com.luchoct.utf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class Utf8DetailsControllerTest {

    @MockBean
    private Utf8DetailsService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUtf8DetailsOfPoundSymbol() throws Exception {
        //given
        given(this.service.getDetails("£")).willReturn(
                new Utf8Details("U+00A3", "194 163", "C2A3"));
        //when and then
        mvc.perform(MockMvcRequestBuilders.get("/utf8Details/£").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{unicode:'U+00A3', dec:'194 163', hex: 'C2A3'}"));
    }

    @Test
    public void getUtf8DetailsOfWrongUtf8Character() throws Exception {
        //given
        given(this.service.getDetails("££")).willThrow(WrongUtf8CharacterException.class);
        //when and then
        mvc.perform(MockMvcRequestBuilders.get("/utf8Details/££").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}