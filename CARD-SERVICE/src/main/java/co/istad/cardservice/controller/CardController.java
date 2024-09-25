package co.istad.cardservice.controller;

import co.istad.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
@RefreshScope
public class CardController {

    @Value("${owner.name}")
    private String ownerName;

    private final CardService cardService;


    @GetMapping("/public")
    public Map<String,String> getPublicApi() {
        return Map.of("message", "Public Api");
    }

    @GetMapping("/private")
    public Map<String,String> getPrivateApi() {
        return Map.of("message", "Private Api");
    }

}
