package co.istad.cardservice.controller;

import co.istad.cardservice.domain.Card;
import co.istad.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
@RefreshScope
public class CardController {

    private final CardService cardService;

    @GetMapping("/public")
    public Map<String,String> getPublicApi() {
        return Map.of("message", "Public Api");
    }

    @GetMapping("/private")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public Map<String, String> getPrivateApi(@RequestHeader Map<String, String> headers) {
        // Log the headers to see the Authorization header and other details
        headers.forEach((key, value) -> System.out.println(key + ": " + value));

        return Map.of("message", "Private Api");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public List<Card> getCards() {
        // return list of cards
        return new ArrayList<>(){{
            add(new Card(1l, java.util.UUID.randomUUID().toString(),"1234-1234-1234-1234"));
        }};
    }

}
