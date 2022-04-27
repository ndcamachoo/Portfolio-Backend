package com.nelcamacho.serieservice.service;

import com.nelcamacho.serieservice.message.MessageSender;
import com.nelcamacho.serieservice.models.Chapter;
import com.nelcamacho.serieservice.models.Season;
import com.nelcamacho.serieservice.models.Serie;
import com.nelcamacho.serieservice.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService implements IEntityService<Serie> {

    /* ================ Attributes ====================== */

    private final SerieRepository serieRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final MessageSender messageSender;

    /* ================ Methods ====================== */

    @Override
    public List<Serie> getAll() {
        return serieRepository.findAll();
    }

    @Override
    public Serie getById(Long id) {
        return serieRepository.findById(id).orElse(null);
    }

    @Override
    public Serie upsert(Serie entity) {

        entity.setId(sequenceGeneratorService.getSequenceNumber(Serie.SEQUENCE_NAME));
        entity.getSeasons().forEach(season -> {
            season.setId(sequenceGeneratorService.getSequenceNumber(Season.SEQUENCE_NAME));
            season.getChapters().forEach(chapter -> {
                chapter.setId(sequenceGeneratorService.getSequenceNumber(Chapter.SEQUENCE_NAME));
            });
        });

        messageSender.send(entity);
        return serieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        serieRepository.deleteById(id);
    }

    public List<Serie> getSeriesByGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    public void deleteAll() {
        serieRepository.deleteAll();
    }

    /* ================ Constructor ====================== */

    @Autowired
    public SerieService(SerieRepository serieRepository, SequenceGeneratorService sequenceGeneratorService, MessageSender messageSender) {
        this.serieRepository = serieRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.messageSender = messageSender;
    }

}
