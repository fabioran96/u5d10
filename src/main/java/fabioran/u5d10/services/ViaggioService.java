package fabioran.u5d10.services;

import fabioran.u5d10.entities.Viaggio;
import fabioran.u5d10.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio save(Viaggio body){
        return viaggioRepository.save(body);
    }
}
