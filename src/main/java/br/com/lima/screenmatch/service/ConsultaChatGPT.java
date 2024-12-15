package br.com.lima.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");


        CompletionRequest requisicao = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}