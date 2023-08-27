package dev.akif.library.pix;

import dev.akif.crud.CRUDDTO;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record PixDTO(
        @NotNull UUID id,
        @NotNull Instant createdAt,
        @NotNull Instant updatedAt,
        Instant deletedAt,
        @NotNull String tipoChave,
        @NotNull String valorChave,
        @NotNull String tipoConta,
        @NotNull String numAgencia,
        @NotNull String numConta,
        @NotNull String nomeCorrentista,
        String sobrenomeCorrentista,
        @NotNull String tipoPessoa
) implements CRUDDTO<UUID> {
}
