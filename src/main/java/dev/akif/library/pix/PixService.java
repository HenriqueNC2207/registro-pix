package dev.akif.library.pix;

import dev.akif.crud.CRUDService;
import dev.akif.crud.common.InstantProvider;
import dev.akif.crud.common.Parameters;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PixService extends CRUDService<UUID, PixEntity,Pix, CreatePix, UpdatePix, PixRepository, PixMapper> {
    public PixService(final InstantProvider instantProvider, final PixRepository repository, final PixMapper mapper) {
        super("Pix", instantProvider, repository, mapper);
    }
    
    protected  Object createPix(final PixEntity pixEntity, final Parameters parameters) {
        Object retornoValidacao = validaChavePix(pixEntity);
        if(retornoValidacao == null){
            return getRepository().save(pixEntity);
        }
        return retornoValidacao.toString();
        
    }

    public Boolean verificarCPF (String cpf) {
        // Remover caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcular os dígitos verificadores
        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += numeros[i] * (10 - i);
        }

        int resto1 = soma1 % 11;
        int digito1 = (resto1 < 2) ? 0 : 11 - resto1;

        if (numeros[9] != digito1) {
            return false;
        }

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += numeros[i] * (11 - i);
        }

        int resto2 = soma2 % 11;
        int digito2 = (resto2 < 2) ? 0 : 11 - resto2;

        return numeros[10] == digito2;
    }

    public static boolean validarCNPJ(String cnpj) {

        // Remover caracteres não numéricos do CNPJ
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificar se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Calcular os dígitos verificadores
        int[] numeros = new int[14];
        for (int i = 0; i < 14; i++) {
            numeros[i] = Character.getNumericValue(cnpj.charAt(i));
        }

        int soma1 = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma1 += numeros[i] * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }

        int resto1 = soma1 % 11;
        int digito1 = (resto1 < 2) ? 0 : 11 - resto1;

        if (numeros[12] != digito1) {
            return false;
        }

        int soma2 = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma2 += numeros[i] * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }

        int resto2 = soma2 % 11;
        int digito2 = (resto2 < 2) ? 0 : 11 - resto2;

        return numeros[13] == digito2;
    }

    public static boolean validarEmail(String cnpj) {
        if(cnpj.length() < 77 && cnpj.contains("@")){
            return true;
        }
        return false;
    }

    public boolean validarDuplicados(String valorChave){
        if(getRepository().existsByValorChave(valorChave)){
            return false;
        }
        return true;
    }

    public Object validaChavePix (final PixEntity pixEntity){
        long limite = pixEntity.getTipoPessoa().equals("F") ? 5 : 20;
        if(validarDuplicados(pixEntity.getValorChave()) ){
            if(pixEntity.getTipoPessoa().equals("J") || pixEntity.getTipoPessoa().equals("F")){
                if(validaQtdChavesConta(pixEntity)){
            switch(pixEntity.getTipoChave()){
            case "cpf":
                if(verificarCPF(pixEntity.getValorChave())){
                    return null;
                }
                else{
                    return "Erro na verificação de CPF" +  pixEntity.getValorChave();
                }
            case "cnpj":
                if(validarCNPJ(pixEntity.getValorChave())){
                    return null;
                }
                else{
                    return "Erro na verificação de CNPJ" +  pixEntity.getValorChave();
                }
            case "email":
                if(validarEmail(pixEntity.getValorChave()) ){
                    return null;
                }
                else{
                    return "Erro na verificação de Email" +  pixEntity.getValorChave();
                }          
        }
        }
        else{
            return "Erro na verificação, Conta já possui o limite de  chaves cadastradas, limite de chaves para conta: " +  limite;
        }
        }
        else{
            return "Erro na verificação, tipo de pessoa precisa ser J ou F" +  pixEntity.getTipoPessoa();
        }
        
        }
        else{
            return "Erro na verificação, chave pix duplicada:  " +  pixEntity.getValorChave();
        }
       
        return null;
        
    }

    public boolean validaQtdChavesConta (final PixEntity pixEntity){
        long limite = pixEntity.getTipoPessoa().equals("F") ? 5 : 20;
        if(getRepository().countByNumContaAndNumAgencia(pixEntity.getNumConta(), pixEntity.getNumAgencia()) >= limite){
            return false;
        }
        return true;
    }

    

    @Override
    protected PixEntity getUsingRepository(UUID arg0, Parameters arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsingRepository'");
    }

    @Override
    protected int updateUsingRepository(PixEntity arg0, Parameters arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUsingRepository'");
    }


    @Override
    protected Page<PixEntity> listUsingRepository(final Pageable pageable, final Parameters parameters) {
        return getRepository().findAllByDeletedAtIsNull(pageable);
    }

    @Override
    protected PixEntity createUsingRepository(PixEntity arg0, Parameters arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUsingRepository'");
    }
}
