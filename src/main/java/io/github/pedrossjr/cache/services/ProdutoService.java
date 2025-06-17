package io.github.pedrossjr.cache.services;

import io.github.pedrossjr.cache.entities.Produto;
import io.github.pedrossjr.cache.repositories.ProdutoRepository;
import lombok.Value;
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
    static final String cacheKey = "1234567890";

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @CacheEvict(value = cacheName, key = cacheKey)
    public Produto adicionar(Produto produto ){
        return produtoRepository.save(produto);
    }

    @Cacheable(value = cacheName, key = cacheKey)
    public List<Produto> listAll(){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            return produtoRepository.findAll();
//        }

        return produtoRepository.findAll();
    }

    @Cacheable(value = cacheName, key = cacheKey)
    public Optional<Produto> listId(String sku) {
        return produtoRepository.findById(sku);
    }

    @CacheEvict(value = cacheName, key = cacheKey)
    public Produto atualizarId(Produto produto) {
        return produtoRepository.save(produto);
    }

}
