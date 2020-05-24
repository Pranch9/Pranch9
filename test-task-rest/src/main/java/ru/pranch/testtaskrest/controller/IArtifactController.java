package ru.pranch.testtaskrest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.pranch.testtaskrest.model.Artifact;

import java.util.Optional;

@Api(tags = "Artifact")
@RequestMapping("/default")
public interface IArtifactController {

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "GET",
            tags = "Artifact")
    @GetMapping
    Iterable<Artifact> findAll(Pageable pageable);


    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "GET",
            tags = "Artifact")
    @GetMapping("/artifact")
    Iterable<Artifact> listBy(String category,
                              String userId,
                              String description,
                              Pageable pageable);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "GET",
            tags = "Artifact")
    @GetMapping("/artifact/{id}")
    Optional<Artifact> getOne(@PathVariable("id") Long id);

    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "POST",
            tags = "Artifact")
    @PostMapping("/artifact")
    Artifact create(@RequestBody Artifact artifact);


    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "PUT",
            tags = "Artifact")
    @PutMapping("/artifact/{id}")
    Artifact update(@PathVariable("id") Artifact artifactFromDb,
                    @RequestBody Artifact artifact);


    @ApiOperation(
            value = "",
            notes = "",
            httpMethod = "DELETE",
            tags = "Artifact")
    @DeleteMapping("/artifact/{id}")
    void delete(@PathVariable("id") Long id);


}
