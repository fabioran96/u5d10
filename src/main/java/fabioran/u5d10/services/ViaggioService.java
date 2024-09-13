package fabioran.u5d10.services;

import fabioran.u5d10.entities.StatoViaggio;
import fabioran.u5d10.entities.Viaggio;
import fabioran.u5d10.exceptions.NotFoundException;
import fabioran.u5d10.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    //crea viaggio
    public Viaggio save(Viaggio body){
        body.setStato(StatoViaggio.IN_PROGRAMMA);
        return viaggioRepository.save(body);
    }

    //findById

    public Viaggio findById(int id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nessun viaggio con questo ID è stato trovato") );
    }

    //aggiorna viaggio

    public Viaggio update(int id, Viaggio body){
        Viaggio found = this.findById(id);

        return viaggioRepository.save(found);
    }

    //aggiorna stato viaggio

    public Viaggio updateStato(int id, StatoViaggio stato){
        Viaggio found = this.findById(id);
        found.setStato(stato);
        return viaggioRepository.save(found);
    }

    //Elimina viaggio

    public void delete(int id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }




}
