package br.com.fiap.smartcities.ejb;

import java.util.List;

public interface EstabelecimentosServiceLocal {

    List<String> pesquisar(String termo);

}
