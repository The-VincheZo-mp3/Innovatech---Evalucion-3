package com.innovatech.backend.controller;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private static final String FILE = "/app/data/data.txt";

    @PostMapping
    public String add(@RequestBody String value) {
    try {
        Path dir = Paths.get("/app/data");
        Path file = Paths.get("/app/data/data.txt");

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        Files.write(
                file,
                (value + System.lineSeparator()).getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );

        return "Guardado: " + value;

    } catch (Exception e) {
        return "ERROR: " + e.getMessage();
    }
}

    @GetMapping
    public List<String> get() throws IOException {
        if (!Files.exists(Paths.get(FILE))) return new ArrayList<>();
        return Files.readAllLines(Paths.get(FILE));
    }
}