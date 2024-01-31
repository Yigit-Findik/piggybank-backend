package com.testing.piggybank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.piggybank.account.UpdateAccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegratieTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAccountTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/{accountId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void getAccountsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts")
                        .header("X-User-Id", 123))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accounts").isArray());
    }

    @Test
    public void updateAccountTest() throws Exception {
        UpdateAccountRequest updateRequest = new UpdateAccountRequest();
        updateRequest.setAccountId(1L);
        updateRequest.setAccountName("UpdatedName");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
