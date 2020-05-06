package ru.pranch.testtaskrest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.repository.ArtifactRepos;


import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ArtifactControllerTest {

    private static final ObjectMapper om = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtifactRepos artifactRepos;

    @Before
    public void init() {
        Artifact artifact = new Artifact( "Book Name", "Mkyong","9.99");
        when(artifactRepos.findById(1L)).thenReturn(Optional.of(artifact));
    }

    @Test
    @WithMockUser
    public void listBy() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }
}