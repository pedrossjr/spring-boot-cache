package io.github.pedrossjr.cache.services;

import io.github.pedrossjr.cache.entities.Produto;
import io.github.pedrossjr.cache.exception.ProdutoNotFoundException;
import io.github.pedrossjr.cache.repositories.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;


    static final String cacheName = "cache-produto";
    static final String cacheKey = "";

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Com o a anotation @CacheEvict habilitada, logo ao atualizar o produto o cache é limpo
    // atualizando automaticamente a lista de produtos no cache.
    // Se não é utilizado o time para limpeza do cache, é uma boa estratégia para atualizar os dados no cache
    // logo após adicionar um novo registro.
    // @CacheEvict(value = cacheName, key = cacheKey)
    public Produto add(Produto produto ) throws ProdutoNotFoundException {
        verifyByExists(produto.getSku());
        return produtoRepository.save(produto);
    }

    @Cacheable(value = cacheName, key = cacheKey)
    public List<Produto> listAll(){
        System.out.println("Se esta linha for impressa, significa que a consulta está sendo realizada no banco de dados.");
        System.out.println("As próximas consultas durantes os próximos 15 segundos serão realizadas no cache do Redis.");
        return produtoRepository.findAll();
    }

    @Cacheable(value = cacheName, key = cacheKey)
    public Optional<Produto> listId(String sku) {
        System.out.println("Se esta linha for impressa, significa que a consulta está sendo realizada no banco de dados.");
        System.out.println("As próximas consultas durantes os próximos 15 segundos serão realizadas no cache do Redis.");
        return produtoRepository.findById(sku);
    }

    // Mesmo caso da inclusão porém, na atualização de um produto
    // @CacheEvict(value = cacheName, key = cacheKey)
    public Produto updateId(Produto produto) throws ProdutoNotFoundException {
        verifyByExists(produto.getSku());
        return produtoRepository.save(produto);
    }

    // Mesmo caso da inclusão porém, na exclusão de um produto
    // @CacheEvict(value = cacheName, key = cacheKey)
    public void delete(String sku) throws ProdutoNotFoundException {
        verifyByExists(sku);
        produtoRepository.deleteById(sku);
    }

    private Produto verifyByExists(String sku) throws ProdutoNotFoundException {
        return produtoRepository.findById(sku)
                .orElseThrow(() -> new ProdutoNotFoundException(sku));
    }

}
