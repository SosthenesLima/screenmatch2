package br.com.lima.screenmatch;
import br.com.lima.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

//		List<DadosTemprada> tempradas = new ArrayList<>();
//
//		for ( int i = 1; i<= dados.totalTempadas(); i++){
//			json = consumoApi.obterDados("https://omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
//			DadosTemprada dadosTemprada = conversor.obterDados(json, DadosTemprada.class);
//			tempradas.add(dadosTemprada);
//		}
//		tempradas.forEach(System.out::println);
	}
}
