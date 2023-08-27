package dev.akif.library.pix;

import dev.akif.crud.CRUDUpdateModel;

public record UpdatePix(
        String isbn,
        String title
) implements CRUDUpdateModel {
}
