package fabioran.u5d10.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fabioran.u5d10.entities.Dipendente;
import fabioran.u5d10.exceptions.BadRequestException;
import fabioran.u5d10.exceptions.NotFoundException;
import fabioran.u5d10.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DipendenteService {
@Autowired
    private DipendenteRepository dipendenteRepository;
@Autowired
    private Cloudinary cloudinaryUploader;

    public Dipendente save(Dipendente body) {
        dipendenteRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è già stata utilizzata");
        });
        body.setImmagineProfilo("https://ui-avatars.com/api/?name=" + body.getNome().charAt(0) + "+" + body.getCognome().charAt(0));
        return dipendenteRepository.save(body);
    }

    public Page<Dipendente> getDipendente(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(int id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }

    public Dipendente findByIdAndUpdate(int id, Dipendente body) {

        Dipendente found = this.findById(id);
        found.setUsername(body.getUsername());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setImmagineProfilo(body.getImmagineProfilo());
        return dipendenteRepository.save(found);
    }

    public Dipendente uploadImmagine(int id, MultipartFile file) throws IOException {
        Dipendente found = this.findById(id);
        String immagineURL = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImmagineProfilo(immagineURL);
        return dipendenteRepository.save(found);
    }
}
