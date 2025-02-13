/*
  By Sósthenes Oliveira Lima
 */

package br.com.lima.screenmatch;
import br.com.lima.screenmatch.principal.Principal;
import br.com.lima.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ScreenmatchApplication  {


	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchApplication.class, args);
	}


}
