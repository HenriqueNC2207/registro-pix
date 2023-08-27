package dev.akif.library.pix;

import dev.akif.crud.CRUDMapper;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class PixMapper implements CRUDMapper<UUID, PixEntity, Pix, CreatePix, UpdatePix> {  
    @Override
    public PixEntity entityToBeCreatedFrom(final CreatePix createPix, final Instant now) {
        return new PixEntity(createPix.tipoChave(), createPix.valorChave(), createPix.tipoConta(), createPix.numAgencia(), createPix.numConta(), createPix.nomeCorrentista(), createPix.sobrenomeCorrentista(), createPix.tipoPessoa(), now);
    }


    @Override
    public void updateEntityWith(PixEntity arg0, UpdatePix arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEntityWith'");
    }


    @Override
    public Pix entityToModel(final PixEntity pixEntity) {
        return new Pix(
                    pixEntity.getId(),
                    pixEntity.getVersion(),
                    pixEntity.getCreatedAt(),
                    pixEntity.getUpdatedAt(),
                    pixEntity.getDeletedAt(),
                    pixEntity.getTipoChave(),
                    pixEntity.getValorChave(),
                    pixEntity.getTipoConta(),
                    pixEntity.getNumAgencia(),
                    pixEntity.getNumConta(),
                    pixEntity.getNomeCorrentista(),
                    pixEntity.getSobrenomeCorrentista(),
                    pixEntity.getTipoPessoa()

                );
                
        
    }
}
