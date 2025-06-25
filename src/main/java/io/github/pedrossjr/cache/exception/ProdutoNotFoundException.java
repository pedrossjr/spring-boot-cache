package io.github.pedrossjr.cache.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends Exception {
    public ProdutoNotFoundException(String sku) {
        super("Produto não encontrado com o código " + sku);
    }
}
