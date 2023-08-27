package dev.akif.library.pix;

import dev.akif.crud.CRUDUpdateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePixDTO(
        @NotNull String isbn,
        @NotBlank String title
) implements CRUDUpdateDTO {
}
