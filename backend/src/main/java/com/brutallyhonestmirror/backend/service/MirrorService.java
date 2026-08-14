package com.brutallyhonestmirror.backend.service;

import com.brutallyhonestmirror.backend.dto.HistoryItemResponse;
import com.brutallyhonestmirror.backend.dto.ReflectionResponse;
import com.brutallyhonestmirror.backend.model.Entry;
import com.brutallyhonestmirror.backend.model.Reflection;
import com.brutallyhonestmirror.backend.model.User;
import com.brutallyhonestmirror.backend.repository.EntryRepository;
import com.brutallyhonestmirror.backend.repository.ReflectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class MirrorService {

    private static final String SYSTEM_PROMPT = """
            You are the "Brutally Honest AI Mirror" — you reflect back an unfiltered,
            sharp, and witty observation about the pattern behind what someone describes
            about their habits, excuses, or self-sabotage.

            Your reflections should:
            - Be sharp, clever, and a little funny — insight that stings because it's
              accurate, not because it's mean
            - Stay short: 2-3 sentences maximum
            - Call out the specific pattern in what they wrote — avoid generic
              self-help language
            - Feel like a clever friend calling them out, not a therapist or a bully

            Never:
            - Mock, shame, or use cruel/degrading language
            - Make assumptions about mental health conditions, diagnoses, or trauma
            - Comment on physical appearance, worth as a person, or anything beyond
              the specific behavior/excuse described
            - Encourage harmful behavior, self-hatred, or hopelessness

            If someone's message suggests real distress, crisis, self-harm, or mental
            health struggles rather than everyday habits/excuses, drop the "brutal"
            framing entirely — respond with genuine warmth and encourage them to reach
            out to a trusted person or professional instead.
            """;


    private final EntryRepository entryRepository;
    private final ReflectionRepository reflectionRepository;
    private final AnthropicClient anthropicClient;


    public MirrorService(EntryRepository entryRepository,ReflectionRepository reflectionRepository){
        this.entryRepository = entryRepository;
        this.reflectionRepository = reflectionRepository;
        this.anthropicClient = AnthropicOkHttpClient.fromEnv();
    }
    @Transactional
    public Reflection createReflection(String rawText){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Entry entry = new Entry();
        entry.setRawText(rawText);
        entry.setUser(currentUser);
        entry = entryRepository.save(entry);
        
        String aiResponse = callClaude(rawText);

        Reflection reflection = new Reflection();
        reflection.setEntry(entry);
        reflection.setAiResponse(aiResponse);
        reflection = reflectionRepository.save(reflection);

        return reflection;
        
    }

    private String callClaude(String rawText) {
        try {
            MessageCreateParams params = MessageCreateParams.builder()
                    .model(Model.CLAUDE_SONNET_4_6)
                    .maxTokens(200L)
                    .system(SYSTEM_PROMPT)
                    .addUserMessage(rawText)
                    .build();

            Message message = anthropicClient.messages().create(params);

            return message.content().stream()
                    .filter(block -> block.text().isPresent())
                    .map(block -> block.text().get().text())
                    .findFirst()
                    .orElse("Couldn't generate a reflection right now — try again.");

        } catch (Exception e) {
            return "The mirror's a bit foggy right now — try again in a moment.";
        }
    }


    public List<HistoryItemResponse> getHistory(){

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Reflection> reflectionList =  reflectionRepository.findByEntry_User_IdOrderByCreatedAtDesc(currentUser.getId());

        List<HistoryItemResponse> historyItemResponseList = new ArrayList<>();
           for (Reflection reflection : reflectionList){
               HistoryItemResponse historyItemResponse = new HistoryItemResponse(
                       reflection.getId(),
                       reflection.getEntry().getRawText(),
                       reflection.getAiResponse(),
                       reflection.getCreatedAt());

               historyItemResponseList.add(historyItemResponse);
           }

           return historyItemResponseList;

    }


}
