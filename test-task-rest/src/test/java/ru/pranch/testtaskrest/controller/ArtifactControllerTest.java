package ru.pranch.testtaskrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.pranch.testtaskrest.model.Artifact;
import ru.pranch.testtaskrest.service.ArtifactService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Mock
    private ArtifactService artifactService;

    @Before
    public void init() {
        Artifact artifact = new Artifact(1L, "Book Name", "Mkyong", "9.99");

    }

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
    public void findAllArtifactsTest() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    @WithMockUser
    public void findAllArtifactsPageableTest() throws Exception {

        mockMvc.perform(get("/artifact"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    @WithMockUser
    public void deleteByIdTest() throws Exception {

        doNothing().when(artifactService).delete(1L);

        mockMvc.perform(delete("/artifact/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void addArtifactTest() throws Exception {
        Artifact artifact = new Artifact("Spring", "Boot", "Test");
        when(artifactService.save(artifact)).thenReturn(artifact);

        mockMvc.perform(post("/artifact")
                .content(om.writeValueAsString(artifact))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void updateArtifactTest() throws Exception {
        Artifact artifact = new Artifact(1L, "SpringUpdate", "BootUpdate", "TestUpdate");
        when(artifactService.save(artifact)).thenReturn(artifact);

        mockMvc.perform(put("/artifact/1")
                .content(om.writeValueAsString(artifact))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("SpringUpdate")));
    }
}