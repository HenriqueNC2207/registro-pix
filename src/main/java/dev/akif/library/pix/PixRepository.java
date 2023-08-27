package dev.akif.library.pix;

import dev.akif.crud.CRUDRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends CRUDRepository<UUID, PixEntity> {

   boolean existsByValorChave(String valorChave);
   long countByNumContaAndNumAgencia(String numConta, String numAgencia);
}
