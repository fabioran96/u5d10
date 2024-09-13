package fabioran.u5d10.services;

import fabioran.u5d10.entities.Prenotazione;
import fabioran.u5d10.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Prenotazione save(Prenotazione body) {
        return prenotazioneRepository.save(body);
    }
}
