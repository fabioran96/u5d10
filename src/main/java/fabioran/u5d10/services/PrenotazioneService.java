package fabioran.u5d10.services;

import fabioran.u5d10.entities.Prenotazione;
import fabioran.u5d10.entities.Viaggio;
import fabioran.u5d10.exceptions.NotFoundException;
import fabioran.u5d10.repositories.DipendenteRepository;
import fabioran.u5d10.repositories.PrenotazioneRepository;
import fabioran.u5d10.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;

    //crea prenotazione
    public Prenotazione save(Prenotazione body) {
        return prenotazioneRepository.save(body);
    }

    //get prenotazioni
    public Page<Prenotazione> getPrenotazioni(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return prenotazioneRepository.findAll(pageable);
    }

    //trova prenotazione per id
    public Prenotazione findById(int id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Non ho trovato nessuna prenotazione con questo id"));
    }

    //aggiorna prenotazione
    public Prenotazione update(int id, Prenotazione body){
        Prenotazione prenotazione = this.findById(id);
        prenotazione.setDataRichiesta(body.getDataRichiesta());
        prenotazione.setNote(body.getNote());
        return prenotazioneRepository.save(prenotazione);
    }


    //Elimina prenotazione

    public void delete(int id) {
        Prenotazione found = this.findById(id);
        prenotazioneRepository.delete(found);
    }
}
