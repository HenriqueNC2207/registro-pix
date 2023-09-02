package dev.akif.library.pix;

import dev.akif.crud.CRUDUpdateModel;

public record UpdatePix(
        String tipoConta,
        String numAgencia,
        String numConta,
        String nomeCorrentista,
        String sobrenomeCorrentista,
        String tipoPessoa
) implements CRUDUpdateModel {
}
