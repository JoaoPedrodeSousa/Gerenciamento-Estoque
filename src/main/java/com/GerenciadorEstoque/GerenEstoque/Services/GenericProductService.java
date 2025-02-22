package com.GerenciadorEstoque.GerenEstoque.Services;

import java.util.List;

public interface GenericProductService<K,Y> {
    K findById(Y entityId);
    List<K> findAll();
    K insert(K entity);
    K update(K entity);
    void delete(Y entityId);
}
