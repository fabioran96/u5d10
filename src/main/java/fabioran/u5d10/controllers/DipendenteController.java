package fabioran.u5d10.controllers;

import fabioran.u5d10.entities.Dipendente;
import fabioran.u5d10.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente nuovoDipendente (@RequestBody Dipendente body) throws Exception {
        return dipendenteService.save(body);
    }

    // 2. - GET http://localhost:3001/dipendenti
    @GetMapping("")
    public Page<Dipendente> getDipendente(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.getDipendente(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/dipendenti/{id}
    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable int dipendenteId){
        return dipendenteService.findById(dipendenteId);
    }

    // 4. - PUT http://localhost:3001/dipendenti/{id} (+ req.body)
    @PutMapping("/{dipendenteId}")
    public Dipendente update(@PathVariable int dipendenteId, @RequestBody Dipendente body){
        return dipendenteService.update(dipendenteId, body);
    }

    // 5. - DELETE http://localhost:3001/dipendenti/{id}
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int dipendenteId) {
        dipendenteService.delete(dipendenteId);
    }

    //6. - POST IMAGE
    @PostMapping("/{dipendenteId}/immagine")
    public Dipendente uploadImmagine(@RequestParam("immagine") MultipartFile file, @PathVariable int dipendenteId){
        try {
            return dipendenteService.uploadImmagine(dipendenteId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

