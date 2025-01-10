package br.com.lima.screenmatch.controller;

import br.com.lima.screenmatch.dto.SerieDTO;
import br.com.lima.screenmatch.model.Serie;
import br.com.lima.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository repositorio;

    @GetMapping("/series")
    public List<SerieDTO> obterSeries() {
        return repositorio.findAll()
                .stream()
                .map(s -> SerieDTO(s.getId()));
    }
}
