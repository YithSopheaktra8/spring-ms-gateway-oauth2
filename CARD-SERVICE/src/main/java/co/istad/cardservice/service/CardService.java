package co.istad.cardservice.service;

import co.istad.cardservice.dto.CardCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    void createCard(CardCreateRequest userCreateRequest);

}
