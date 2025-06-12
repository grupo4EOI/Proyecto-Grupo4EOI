package com.atm.buenas_practicas_java.controllers;


import com.atm.buenas_practicas_java.PostgreSQLContainerTest;
import com.atm.buenas_practicas_java.entities.EntidadHija;
import com.atm.buenas_practicas_java.entities.EntidadPadre;
import com.atm.buenas_practicas_java.repositories.EntidadHijaRepository;
import com.atm.buenas_practicas_java.repositories.EntidadPadreRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Log4j2
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class DefaultControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EntidadPadreRepository entidadPadreService;

    @Autowired
    EntidadHijaRepository entidadHijaService;


    @Autowired
    EntidadPadreRepository entidadPadreRepository;

    @Autowired
    EntidadHijaRepository entidadHijaRepository;


    @Test
    void shouldReturnProtectedView() throws Exception {
        log.info("Probando el acceso a la vista protegida sin autorización.");
        // Act & Assert
        mockMvc.perform(get("/protected"))
                .andExpect(status().isUnauthorized());
        log.info("El acceso no autorizado a la vista protegida se verificó correctamente.");
    }

    @Test
    void shouldAddProtectedEntitiesToModel() throws Exception {
        log.info("Probando el acceso autorizado a la vista protegida y verificando los atributos del modelo.");
        // Act & Assert
        mockMvc.perform(get("/protected")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("entidades"))
                .andExpect(view().name("entidadesPadre"));
        log.info("El acceso a la vista protegida y los atributos del modelo se verificaron correctamente.");
    }
}