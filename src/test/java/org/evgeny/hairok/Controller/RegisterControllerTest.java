package org.evgeny.hairok.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterButtonSubmit() throws Exception {
        mockMvc.perform(
                post("/register")
                .param("name", "Иван")
                .param("surname", "Иванов")
                .param("patronymic", "Иванович")
                .param("birthday", "2000-01-01")
                .param("phone", "1234567890")
                .param("city", "Москва")
                .param("password", "secret123"))
                .andExpect(status().isOk())
                .andExpect(view().name("/Register/client-register"));

    }

    @Test
    void whenPasswordDontMatch() throws Exception {
        mockMvc.perform(
            post("/register")
            .param("password", "1234567")
            .param("passwordRep", "2234567"))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("registerClientDTO"))
            .andExpect(model().attributeHasFieldErrors("registerClientDTO", "passwordRep"));

    }
}