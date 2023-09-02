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
    public PixEntity entityToBeUpdateEntity(final UpdatePix updatePix, final Instant now) {
        return new PixEntity( null, null,updatePix.tipoConta(), updatePix.numAgencia(), updatePix.numConta(), updatePix.nomeCorrentista(), updatePix.sobrenomeCorrentista(), updatePix.tipoPessoa(),  now);
    }


    @Override
    public void updateEntityWith(final PixEntity pixEntity, final UpdatePix updatePix) {
        pixEntity.setTipoConta(updatePix.tipoConta());
        pixEntity.setNumAgencia(updatePix.numAgencia());
        pixEntity.setNumConta(updatePix.numConta());
        pixEntity.setNomeCorrentista(updatePix.nomeCorrentista());
        pixEntity.setSobrenomeCorrentista(updatePix.sobrenomeCorrentista());
        pixEntity.setTipoPessoa(updatePix.tipoPessoa());
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
