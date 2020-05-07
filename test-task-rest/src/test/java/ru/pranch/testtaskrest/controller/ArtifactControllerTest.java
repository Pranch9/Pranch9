package ru.pranch.testtaskrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.repository.ArtifactRepos;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Artifact artifact = new Artifact("Book Name", "Mkyong", "9.99");
        artifact.setId(1L);
        when(artifactRepos.findById(1L)).thenReturn(Optional.of(artifact));
    }

    @Test
    @WithMockUser
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/artifact/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is("Book Name")))
                .andExpect(jsonPath("$.category", is("Mkyong")))
                .andExpect(jsonPath("$.description", is("9.99")));
        verify(artifactRepos, times(1)).findById(1L);
    }

    

//    @Test
//    @WithMockUser
//    public void findAllArtifacts() throws Exception {
//        List<Artifact> artifactList = Arrays.asList(
//                new Artifact(1L, "Book A", "AhPig", "1.99", LocalDateTime.now()),
//                new Artifact(2L, "Book B", "Ah Dog", "2.99", LocalDateTime.now()));
//
//        when(artifactRepos.findAll()).thenReturn(artifactList);
//
//        mockMvc.perform(get("/artifact"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.content", hasSize(2)));
//
//        verify(artifactRepos, times(1)).findAll();
//    }

    @Test
    @WithMockUser
    public void pageNotFound() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }
}