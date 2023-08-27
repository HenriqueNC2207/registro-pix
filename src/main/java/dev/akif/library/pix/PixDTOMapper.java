package dev.akif.library.pix;

import dev.akif.crud.CRUDDTOMapper;
import dev.akif.crud.common.Parameters;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class PixDTOMapper implements CRUDDTOMapper<UUID, Pix, PixDTO, CreatePix, UpdatePix, CreatePixDTO, UpdatePixDTO> {
    @Override
    public CreatePix createDTOToCreateModel(final CreatePixDTO createPixDTO, final Parameters parameters) {
        return new CreatePix(createPixDTO.tipoChave(), createPixDTO.valorChave(), createPixDTO.tipoConta(), createPixDTO.numAgencia(), createPixDTO.numConta(),createPixDTO.nomeCorrentista(),createPixDTO.sobrenomeCorrentista(),createPixDTO.tipoPessoa());
    }



    @Override
    public PixDTO modelToDTO(final Pix pix, final Parameters parameters) {
        return new PixDTO(
                pix.id(),
                pix.createdAt(),
                pix.updatedAt(),
                pix.deletedAt(),
                pix.tipoChave(),
                pix.valorChave(),
                pix.tipoConta(),
                pix.numAgencia(),
                pix.numConta(),
                pix.nomeCorrentista(),
                pix.sobrenomeCorrentista(),
                pix.tipoPessoa()
        );
    }




    @Override
    public UpdatePix updateDTOToUpdateModel(UpdatePixDTO arg0, Parameters arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDTOToUpdateModel'");
    }



    

}
