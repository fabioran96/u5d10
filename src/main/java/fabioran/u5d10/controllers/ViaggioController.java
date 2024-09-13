package fabioran.u5d10.controllers;


import fabioran.u5d10.entities.Viaggio;
import fabioran.u5d10.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio nuovoViaggio(@RequestBody Viaggio body) throws Exception {
        return viaggioService.save(body);
    }

    // 2. - GET http://localhost:3001/viaggi
    @GetMapping("")
    public Page<Viaggio> getViaggio(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return viaggioService.getViaggio(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/viaggi/{id}
    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable int viaggioId){
        return viaggioService.findById(viaggioId);
    }

    // 4. - PUT http://localhost:3001/viaggi/{id} (+ req.body)
    @PutMapping("/{viaggioId}")
    public Viaggio findAndUpdate(@PathVariable int viaggioId, @RequestBody Viaggio body){
        return viaggioService.update(viaggioId, body);
    }

    // 5. - DELETE http://localhost:3001/viaggi/{id}
    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int viaggioId) {
        viaggioService.delete(viaggioId);
    }

}
