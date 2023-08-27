package dev.akif.library.pix;

import dev.akif.crud.CRUDEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "pix")
@ToString
public class PixEntity extends CRUDEntity<UUID> {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    private Integer version;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    private String tipoChave;

    private String valorChave;

    private String tipoConta;

    private String numAgencia;

    private String numConta;

    private String nomeCorrentista;

    private String sobrenomeCorrentista;

    private  String tipoPessoa;

    public PixEntity(
            final UUID id,
            final Integer version,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt,
            final String tipoChave,
            final String valorChave,
            final String tipoConta,
            final String numAgencia,
            final String numConta,
            final String nomeCorrentista,
            final String sobrenomeCorrentista,
            final String tipoPessoa 
    ) {
        this.id = id;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.tipoChave = tipoChave;
        this.valorChave = valorChave;
        this.tipoConta = tipoConta;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.nomeCorrentista = nomeCorrentista;
        this.sobrenomeCorrentista = sobrenomeCorrentista;
        this.tipoPessoa = tipoPessoa;

    }

    public PixEntity(
            final String tipoChave,
            final String valorChave,
            final String tipoConta,
            final String numAgencia,
            final String numConta,
            final String nomeCorrentista,
            final String sobrenomeCorrentista,
            final String tipoPessoa,
            final Instant now
    ) {
        this(null, 0, now, now, null, tipoChave, valorChave, tipoConta, numAgencia , numConta, nomeCorrentista, sobrenomeCorrentista, tipoPessoa);
    }
    public PixEntity() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null);
    } 
}
 