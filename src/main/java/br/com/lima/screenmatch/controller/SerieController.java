package br.com.lima.screenmatch.controller;
import br.com.lima.screenmatch.dto.SerieDTO;
import br.com.lima.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servico;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return servico.obterTodasAsSeries();

    }
        @GetMapping("/top5")
        public List<SerieDTO> obterTop5Series() {
            return servico.obterTop5Series();



        // @GetMapping("/inicio")
        // public String retornarInicio() {
        //return "Bem Vindo ao ScreeMatch - 2025";
        // }
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return servico.obterLancamentos();

    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }
}