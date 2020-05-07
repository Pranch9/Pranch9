package ru.pranch.testtaskrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.service.ArtifactService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ArtifactControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Mock
    private ArtifactService artifactService;

    @Test
    @WithMockUser
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/artifact/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is("privet")))
                .andExpect(jsonPath("$.category", is("test1")))
                .andExpect(jsonPath("$.description", is("qqqqq")));
    }


    @Test
    @WithMockUser
    public void findAllArtifacts() throws Exception {
        List<Artifact> artifactList = Arrays.asList(
                new Artifact(1L, "Book A", "AhPig", "1.99", LocalDateTime.now()),
                new Artifact(2L, "Book B", "Ah Dog", "2.99", LocalDateTime.now()));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    @WithMockUser
    public void findAllArtifactsPageable() throws Exception {
        List<Artifact> artifactList = Arrays.asList(
                new Artifact(1L, "Book A", "AhPig", "1.99", LocalDateTime.now()),
                new Artifact(2L, "Book B", "Ah Dog", "2.99", LocalDateTime.now()));

        when(artifactService.findAll(Pageable.unpaged())).thenReturn(artifactList);

        mockMvc.perform(get("/artifact"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    @WithMockUser
    public void pageIsOk() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}