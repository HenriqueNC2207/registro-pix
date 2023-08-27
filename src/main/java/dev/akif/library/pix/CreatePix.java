package dev.akif.library.pix;

import dev.akif.crud.CRUDCreateModel;

public record CreatePix(
        String tipoChave,
        String valorChave,
        String tipoConta,
        String numAgencia,
        String numConta,
        String nomeCorrentista,
        String sobrenomeCorrentista,
        String tipoPessoa
) implements CRUDCreateModel {
}
