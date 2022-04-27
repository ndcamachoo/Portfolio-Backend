package com.nelcamacho.serieservice.controller;

import com.nelcamacho.serieservice.models.Chapter;
import com.nelcamacho.serieservice.models.Season;
import com.nelcamacho.serieservice.models.Serie;
import com.nelcamacho.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    /* ================== Attributes ================== */

    private final SerieService serieService;

    /* ================== Methods ================== */
    /* ============= [GET] ============= */

    @GetMapping
    public ResponseEntity<?> getAllSeries() {
        return ResponseEntity.ok(serieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSerieById(@PathVariable Long id) {
        return ResponseEntity.ok(serieService.getById(id));
    }

    @GetMapping("/genres/{genre}")
    public ResponseEntity<?> getSeriesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(serieService.getSeriesByGenre(genre));
    }

    @GetMapping("/fallback")
    public ResponseEntity<?> fallback() {
        Serie serieFallback = new Serie();
        Chapter chapterFallback = new Chapter();
        Season seasonFallback = new Season();

        serieFallback.setId(0L);
        serieFallback.setName("Serie without content");
        serieFallback.setGenre("Unknown");

        chapterFallback.setId(0L);
        chapterFallback.setName("Chapter without content");
        chapterFallback.setNumber(0L);
        chapterFallback.setUrlStream("https://cdn.dribbble.com/users/1249496/screenshots/14000121/media/47911b2981d74902155919dc58762d01.gif");

        seasonFallback.setId(0L);
        seasonFallback.setSeasonNumber(0);
        seasonFallback.setChapters(List.of(chapterFallback));
        serieFallback.setSeasons(List.of(seasonFallback));

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(serieFallback);
    }

    /* ============= [POST] ============= */

    @PostMapping
    public ResponseEntity<?> createSerie(@RequestBody Serie serie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serieService.upsert(serie));
    }

    /* ============= [DELETE] ============= */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSerie(@PathVariable Long id) {
        serieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ================== Constructor ================== */

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }
}
