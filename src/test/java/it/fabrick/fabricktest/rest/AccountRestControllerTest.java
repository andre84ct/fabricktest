package it.fabrick.fabricktest.rest;

import it.fabrick.fabricktest.common.FabrickSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FabrickSpringBootTest
@AutoConfigureMockMvc
public class AccountRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetAccountBalance() throws Exception {

        MvcResult res = mockMvc.perform(
                MockMvcRequestBuilders.get("/accounts/{accountId}/balance", 14537780)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        String content = res.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }

    @Test
    void shouldGetAccountBalanceFail() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/accounts/{accountId}/balance", "")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is4xxClientError());

    }


}
