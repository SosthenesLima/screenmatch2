/*
  By Sósthenes Oliveira Lima
 */

package br.com.lima.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsultaChatGPT {

    @Value("${openai.api.key}")
    private String apiKey;

    public String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService(apiKey);

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-4o") // Utilize gpt-4-turbo se gpt-4 falhar
                .prompt("Traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText().trim();
    }
}




