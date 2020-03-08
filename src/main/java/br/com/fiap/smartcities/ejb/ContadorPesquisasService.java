package br.com.fiap.smartcities.ejb;

import lombok.Data;
import lombok.Getter;

import javax.ejb.Singleton;

/**
 * É um singleton pois apenas uma instancia desse objeto estará disponível para toda aplicação
 */
@Singleton
@Data
public class ContadorPesquisasService {

    private Integer pesquisas = 0;
    private Integer usuarios = 0;

    public void novoUsuario(){
        usuarios++;
    }

    public void usuarioSaiu(){
        usuarios--;
    }

    public void novaPesquisa(){
        pesquisas++;
    }

}
