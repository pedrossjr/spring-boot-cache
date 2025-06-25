package io.github.pedrossjr.cache.services;

import io.github.pedrossjr.cache.entities.Produto;
import io.github.pedrossjr.cache.repositories.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;


    static final String cacheName = "cache-produto";
    static final String cacheKey = "";

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @CacheEvict(value = cacheName, key = cacheKey)
    public Produto add(Produto produto ){
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

    @CacheEvict(value = cacheName, key = cacheKey)
    public Produto updateId(Produto produto) {
        return produtoRepository.save(produto);
    }

}
