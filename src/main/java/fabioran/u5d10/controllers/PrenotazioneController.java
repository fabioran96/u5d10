package fabioran.u5d10.controllers;


import fabioran.u5d10.entities.Prenotazione;
import fabioran.u5d10.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione nuovaPrenotazione(@RequestBody Prenotazione body) throws Exception {
        return prenotazioneService.save(body);
    }

    // 2. - GET http://localhost:3001/prenotazioni
    @GetMapping("")
    public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return prenotazioneService.getPrenotazioni(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/prenotazioni/{id}
    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById(@PathVariable int prenotazioneId){
        return prenotazioneService.findById(prenotazioneId);
    }

    // 4. - PUT http://localhost:3001/prenotazioni/{id} (+ req.body)
    @PutMapping("/{prenotazioneId}")
    public Prenotazione findAndUpdate(@PathVariable int prenotazioneId, @RequestBody Prenotazione body){
        return prenotazioneService.update(prenotazioneId, body);
    }

    // 5. - DELETE http://localhost:3001/viaggi/{id}
    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int prenotazioneId) {
        prenotazioneService.delete(prenotazioneId);
    }

}

