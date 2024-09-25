package co.istad.cardservice.service.imp;

import co.istad.cardservice.domain.Card;
import co.istad.cardservice.dto.CardCreateRequest;
import co.istad.cardservice.service.CardRepository;
import co.istad.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public void createCard(CardCreateRequest userCreateRequest) {
        if(cardRepository.existsByCardNumber(userCreateRequest.cardNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Card number already exists"
            );
        }
        Card card = new Card();
        card.setUuid(UUID.randomUUID().toString());
        card.setCardNumber(userCreateRequest.cardNumber());
        cardRepository.save(card);
    }
}
