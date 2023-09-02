package dev.akif.library.pix;

import dev.akif.crud.CRUDCreateDTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePixDTO(
        @NotNull (message = "O campo do tipo de chave não pode ser nulo")
        @Size(min = 0, max = 9, message = "O valor do tipo de chave deve ter no máximo 9 dígitos")
        String tipoChave,
        @NotNull (message = "O campo do valor da chave não pode ser nulo") 
        @Size(min = 0, max = 77, message = "O valor da chave deve ter no máximo 77 dígitos")
        String valorChave,
        @NotNull (message = "O campo do tipo de conta não pode ser nulo")
        @Size(min = 0, max = 10, message = "O valor do campo tipo de conta deve ter no máximo 10 dígitos")
        String tipoConta,
        @NotNull (message = "O campo do número da agência não pode ser nulo") 
        @Digits(integer = 4, fraction = 0, message = "O número da agência deve ser um número inteiro com até 4 dígitos")
        String numAgencia,
        @NotNull (message = "O campo do número da conta não pode ser nulo") 
        @Digits(integer = 8, fraction = 0, message = "O número da conta deve ser um número inteiro com até 8 dígitos")
        String numConta,
        @NotNull (message = "O campo do nome do correntista não pode ser nulo") 
        @Size(min = 0, max = 30, message = "O valor do nome do correntista deve ter no máximo 30 dígitos")
        String nomeCorrentista,
        @Size(min = 0, max = 45, message = "O valor do sobrenome do correntista deve ter no máximo 45 dígitos")
        String sobrenomeCorrentista,
        @NotNull (message = "O campo do tipo de pessoa não pode ser nulo")
        @Size(min = 0, max = 1, message = "O valor do tipo de pessoa deve ter no máximo 1 dígitos") 
        String tipoPessoa 
) implements CRUDCreateDTO {
}
