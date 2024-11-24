/*
 By Sósthenes Oliveira Lima
 Palmas-TO, 23/11/2024 - Sábado
 */
package br.com.lima.screenmatch.principal;

import br.com.lima.screenmatch.model.DadosEpisodio;
import br.com.lima.screenmatch.model.DadosSerie;
import br.com.lima.screenmatch.model.DadosTemporada;
import br.com.lima.screenmatch.service.ConsumoApi;
import br.com.lima.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e4c92d6d";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para a busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> tempradas = new ArrayList<>();

		for ( int i = 1; i<= dados.totalTemporadas(); i++){
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
			DadosTemporada dadosTemprada = conversor.obterDados(json, DadosTemporada.class);
			tempradas.add(dadosTemprada);
	}
		tempradas.forEach(System.out::println);

        //for(int  i = 0; i < dados.totalTemporadas(); i++){
            //List<DadosEpisodio> episodiosTemporada = tempradas.get(i).episodios();
           // for(int j = 0; j < episodiosTemporada.size(); j++){
              //  System.out.println(episodiosTemporada.get(j).titulo());
           // }
       // }

        tempradas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<String> nomes = Arrays.asList("Sósthenes", "Sandra", "Catherine", "Yonnara");



    }
}
