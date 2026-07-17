package com.brutallyhonestmirror.backend.controller;

import com.brutallyhonestmirror.backend.dto.CreateEntryRequest;
import com.brutallyhonestmirror.backend.dto.ReflectionResponse;
import com.brutallyhonestmirror.backend.model.Reflection;
import com.brutallyhonestmirror.backend.service.MirrorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entries")
public class MirrorController {

    private MirrorService mirrorService;


    public MirrorController(MirrorService mirrorService) {
        this.mirrorService = mirrorService;
    }


    @PostMapping
    public ReflectionResponse createdEntry(@RequestBody CreateEntryRequest request){
        Reflection reflection = mirrorService.createReflection(request.getRawText());

        return new ReflectionResponse(
                reflection.getId(),
                reflection.getEntry().getRawText(),
                reflection.getAiResponse(),
                reflection.getCreatedAt()
        );

    }
}
