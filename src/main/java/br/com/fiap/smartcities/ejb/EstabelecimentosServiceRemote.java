package br.com.fiap.smartcities.ejb;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface necessária para especificar quais método do service estão disponíveis para acesso remoto.
 */
public interface EstabelecimentosServiceRemote {
    List<String> pesquisar(String termo);
}
