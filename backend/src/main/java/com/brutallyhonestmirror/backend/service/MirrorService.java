package com.brutallyhonestmirror.backend.service;

import com.brutallyhonestmirror.backend.model.Entry;
import com.brutallyhonestmirror.backend.model.Reflection;
import com.brutallyhonestmirror.backend.repository.EntryRepository;
import com.brutallyhonestmirror.backend.repository.ReflectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MirrorService {

    private final EntryRepository entryRepository;
    private final ReflectionRepository reflectionRepository;

    public MirrorService(EntryRepository entryRepository,ReflectionRepository reflectionRepository){
        this.entryRepository = entryRepository;
        this.reflectionRepository = reflectionRepository;
    }
    @Transactional
    public Reflection createReflection(String rawText){

        Entry entry = new Entry();
        entry.setRawText(rawText);
        entry = entryRepository.save(entry);
        
        String aiResponse = callClaude(rawText);

        Reflection reflection = new Reflection();
        reflection.setEntry(entry);
        reflection.setAiResponse(aiResponse);;
        reflection = reflectionRepository.save(reflection);

        return reflection;
        
    }

    private String callClaude(String rawText) {
        //placeholder
        return "place holder for Reflection: " + rawText;

    }


}
