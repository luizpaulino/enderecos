package com.monitoramento.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Validacao {

    private Validacao() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean campoNaoFoiAlterado(String valorAtual, String valorNovo) {
        return valorAtual.equals(valorNovo) || valorNovo == null;
    }

    public static Boolean dataNaoFoiAlterada(LocalDate dataAtual, LocalDate novaData) {
        return dataAtual.equals(novaData) || novaData == null;
    }

    public static Boolean valorNaoFoiAlterada(BigDecimal valorAtual, BigDecimal novoValor) {
        return valorAtual.equals(novoValor) || novoValor == null;
    }
}
