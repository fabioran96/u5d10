package fabioran.u5d10.repositories;

import fabioran.u5d10.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
    Optional<Dipendente> findByEmail(String email);
}
