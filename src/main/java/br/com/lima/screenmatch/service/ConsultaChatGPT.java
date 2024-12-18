package br.com.lima.screenmatch.service;

//import com.theokanning.openai.chat.*;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.Arrays;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");

        ChatCompletionRequest requisicao = ChatCompletionRequest.builder()
                .model("gpt-4") // Substitua pelo modelo correto
                .messages(Arrays.asList(
                        new ChatMessage("system", "Você é um assistente que traduz textos para português."),
                        new ChatMessage("user", "Traduza para o português o texto: " + texto)
                ))
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        ChatCompletionResult resposta = service.createChatCompletion(requisicao);

        return resposta.getChoices().get(0).getMessage().getContent();
    }
}
