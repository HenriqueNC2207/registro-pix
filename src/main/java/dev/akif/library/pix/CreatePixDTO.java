package dev.akif.library.pix;

import dev.akif.crud.CRUDCreateDTO;
import jakarta.validation.constraints.NotNull;

public record CreatePixDTO(
       @NotNull (message = "O campo do tipo de chave não pode ser nulo")
        String tipoChave,
        @NotNull (message = "O campo do valor da chave não pode ser nulo") 
        String valorChave,
        @NotNull (message = "O campo do tipo de conta não pode ser nulo") 
        String tipoConta,
        @NotNull (message = "O campo do número da agência não pode ser nulo") 
        String numAgencia,
        @NotNull (message = "O campo do número da conta não pode ser nulo") 
        String numConta,
        @NotNull (message = "O campo do nome do correntista não pode ser nulo") 
        String nomeCorrentista,
        String sobrenomeCorrentista,
        @NotNull (message = "O campo do tipo de pessoa não pode ser nulo") 
        String tipoPessoa 
) implements CRUDCreateDTO {
}
