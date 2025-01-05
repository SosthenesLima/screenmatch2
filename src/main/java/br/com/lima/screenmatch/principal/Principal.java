/*
 By Sósthenes Oliveira Lima
 Palmas-TO, 23/11/2024 - Sábado
 Finalizado no dia 02/12/2024
 Recomeçando o Projeto em 03/01/2025
 */
package br.com.lima.screenmatch.principal;

import br.com.lima.screenmatch.model.*;
import br.com.lima.screenmatch.repository.SerieRepository;
import br.com.lima.screenmatch.service.ConsumoApi;
import br.com.lima.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e4c92d6d";
    private List<DadosSerie> dadosSeries  = new ArrayList<>();


    private SerieRepository repositorio;

    private List<Serie> series  = new ArrayList<>();

    private Optional<Serie> serieBusca;


    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por título
                    5 - Buscar série por ator
                    6 - Top 5 Séries
                    7 - Buscar séries por categoria
                    8 - Filtrar séries
                    9 - Buscar episódio por trecho
                    10 - Top episódios por Séries
                    11 - Buscar episódios a partir de uma data
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                   buscarSeriesPorCategoria();
                   break;
                case 8:
                    filtrarSeriesPorTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    topEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodiosDepoisDeUmaData();
                    break;
                    case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }



    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        //dadosSeries.add(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma Série pelo nome: ");
        //DadosSerie dadosSerie = getDadosSerie();
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

                //Optional<Serie> serie = series.stream()
                //.filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                //.findFirst();

        if (serie.isPresent()) {


            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodios> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodios(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);

        } else {
            System.out.println("Séries não encontrada!");
        }
    }
    private void listarSeriesBuscadas() {
       // List<Serie> series = repositorio.findAll();

        //List<Serie> series  = new ArrayList<>();
        //series = repositorio.findAll();

        //series = dadosSeries.stream()
                        //.map(d -> new Serie(d))
                       // .collect(Collectors.toList());
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma Série pelo nome: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da série: " + serieBusca.get());

        } else {
            System.out.println("Série não encontrada");

        }
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Qual o nome para a busca?");
        var nomeAtor = leitura.nextLine();
        System.out.println("Avalicações a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        List<Serie> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Séries em que " + nomeAtor + " trabalhou: ");
        seriesEncontradas.forEach(s ->
                System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));

    }

    private void buscarTop5Series() {
        List<Serie> serieTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach( s ->
        System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Deseja buscar séries de que categoria/gênero? ");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);
        System.out.println("Série da categoria: " + nomeGenero);
        seriesPorCategoria.forEach(System.out::println);

    }

    private void filtrarSeriesPorTemporadaEAvaliacao(){
        System.out.println("Filtrar séries até quantas temporadas? ");
        var totalTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Com avaliação a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSeries = repositorio.seriesPorTemporadaEAvaliacoa(totalTemporadas, avaliacao);
        System.out.println("*** Séries filtradas ***");
        filtroSeries.forEach(s ->
                System.out.println(s.getTitulo() + "  - avaliação: " + s.getAvaliacao()));
    }

    private void buscarEpisodioPorTrecho(){
        System.out.println("Qual o nome episódio para a busca?");
        var trechoEpisodio = leitura.nextLine();
        List<Episodios> episodiosEncontrados = repositorio.episodiosPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumeroEpisodio(), e.getTitulo()));
    }

    private void topEpisodiosPorSerie(){
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodios> topEpisodios = repositorio.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e ->
                    System.out.printf("Série: %s Temporada %s - Episódio %s - %s Avaliação %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(),
                            e.getNumeroEpisodio(), e.getTitulo(), e.getAvaliacao() ));
        }
    };

    private void buscarEpisodiosDepoisDeUmaData(){
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            System.out.println("Digite o ano limite de lançamento");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();

            List<Episodios> episodiosAno = repositorio.episodiosPorSerieeAno(serie, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }

}

/*
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

        // List<String> nomes = Arrays.asList("Sósthenes", "Sandra", "Catherine", "Yonnara", "Jacinones");

        //nomes.stream()
          //      .sorted()
            //    .limit(4)
              //  .filter(n -> n.endsWith("s"))
               // .map(n -> n.toUpperCase())
               // .forEach(System.out::println);


        List<DadosEpisodio> dadosEpisodios = tempradas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


        System.out.println("\n Top 10 episódios");
        dadosEpisodios.stream()
                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                        .peek(e -> System.out.println( "Primeiro Filtro(N/A) " + e))
                        .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                        .peek(e -> System.out.println( "Ordenação " + e))
                        .limit(10)
                        .peek(e -> System.out.println( "Limite " + e))
                        .map(e-> e.titulo().toUpperCase())
                        .peek(e -> System.out.println( "Mapeamento " + e))
                        .forEach(System.out::println);

        List<Episodios> episodios = tempradas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodios(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        //System.out.println("Digite um trecho do Título do episódio");
        //var trechoTitulo = leitura.nextLine();
       // Optional<Episodios> episodioBuscado = episodios.stream()
              //  .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
              //  .findFirst();
       // if(episodioBuscado.isPresent()){
           // System.out.println("Episódio encontrado!");
          //  System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
       // } else {
            //System.out.println("Episódio não encontrado!");
       // }

       //System.out.println("Apartir de que ano você deseja ver os episódios? ");
        //var ano = leitura.nextInt();
       // leitura.nextLine();

       // LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        //DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //episodios.stream()
                //.filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
               // .forEach(e -> System.out.println(
                    //"Temporada:   " + e.getTemporada() +
                            //"    Episódio:  " + e.getTitulo() +
                           // "  Data Lançamento:  " + e.getDataLancamento().format(formatador)

              //  ));


        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodios::getTemporada,
                        Collectors.averagingDouble(Episodios::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodios::getAvaliacao));
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor episódio: " + est.getMax());
        System.out.println("Pior episódio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());

 */




