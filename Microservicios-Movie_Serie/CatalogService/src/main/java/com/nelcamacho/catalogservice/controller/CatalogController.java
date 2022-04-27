package com.nelcamacho.catalogservice.controller;

import com.nelcamacho.catalogservice.models.*;
import com.nelcamacho.catalogservice.service.CatalogService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    /* ========================= Attributes ==============================*/

    private final CatalogService catalogService;
    private static final Logger log = LoggerFactory.getLogger(CatalogController.class);

    /* ========================= Methods ==============================*/
    /* =============== [GET] ================ */

    @GetMapping("/{genre}")
    @Retry(name = "breaker-backend", fallbackMethod = "fallback") // Retry → Si el usuario obtiene una respuesta inesperada del recurso, entonces automáticamente vuelve a ejecutar el recurso, de lo contrario, se ejecuta el fallback
    @CircuitBreaker(name = "breaker-backend", fallbackMethod = "fallback") // CircuitBreaker → Si el usuario obtiene una respuesta inesperada del recurso, automáticamente rompe el circuito para evitar que el recurso se ejecute de nuevo y se ejecute el fallback
    public ResponseEntity<?> getGenre(@PathVariable String genre) throws RuntimeException{
        return ResponseEntity.ok().body(catalogService.getByGenre(genre));
    }

    @GetMapping("/fallback")
    public ResponseEntity<?> fallback(Throwable e) {
        Movie fallbackMovie = new Movie();
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

        fallbackMovie.setId(0L);
        fallbackMovie.setName("Movie without content");
        fallbackMovie.setGenre("Unknown");
        fallbackMovie.setUrlStream("https://cdn.dribbble.com/users/1249496/screenshots/14000121/media/47911b2981d74902155919dc58762d01.gif");

        log.error("[FALLBACK METHOD CALLED] -> {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(Catalog.builder().series(List.of(serieFallback)).movies(List.of(fallbackMovie)).build());
    }

    /* ========================= Constructors ==============================*/

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}
