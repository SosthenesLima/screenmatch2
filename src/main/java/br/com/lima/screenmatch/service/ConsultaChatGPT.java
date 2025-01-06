/*
  By Sósthenes Oliveira Lima
 */

package br.com.lima.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");


        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-4")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}



