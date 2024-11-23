package br.com.lima.screenmatch.principal;

import br.com.lima.screenmatch.model.DadosSerie;
import br.com.lima.screenmatch.service.ConsumoApi;
import br.com.lima.screenmatch.service.ConverteDados;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO =  "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para buscar: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace("", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        //https://omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c

    }
}
