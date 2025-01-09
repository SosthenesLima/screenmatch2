package br.com.lima.screenmatch.controller;

import br.com.lima.screenmatch.model.Serie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {

    @GetMapping("/series")
    public String obterSeries() {
        return  " Aqui vão ser listadas as Séries";
    }
}
