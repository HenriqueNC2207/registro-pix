package dev.akif.library.pix;

import dev.akif.crud.CRUDModel;

import java.time.Instant;
import java.util.UUID;

public record Pix(
        UUID id,
        int version,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt,
        String tipoChave,
        String valorChave,
        String tipoConta,
        String numAgencia,
        String numConta,
        String nomeCorrentista,
        String sobrenomeCorrentista,
        String tipoPessoa
) implements CRUDModel<UUID> {
}
