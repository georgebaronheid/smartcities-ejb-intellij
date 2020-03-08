package br.com.fiap.smartcities.ejb;

import lombok.Getter;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Stateful garante que cada usuario que invocar terá uma única instância para si.
 */
@Stateful
@Getter
public class HistoricoPesquisasService {

    /**
     * Injeção de instância do EJB.
     */
    @EJB
    private ContadorPesquisasService contadorService;

    // Set evita a repetição de termos pesquisados no historico. Não há duplicidade em set.
    private Set<String> historico = new LinkedHashSet<String>();

    public void registrarPesquisa(String termo) {
        this.historico.add(termo);
        this.contadorService.novaPesquisa();
    }

}
