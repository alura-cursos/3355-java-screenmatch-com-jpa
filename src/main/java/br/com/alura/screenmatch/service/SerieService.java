package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
    private final SerieRepository serieRepository;

//   cria serie
    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }
}
