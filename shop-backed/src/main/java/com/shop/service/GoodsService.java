package com.shop.service;

import com.shop.domain.Goods;
import java.util.List;

public interface GoodsService {
    List<Goods> findAll();
    Goods findById(Long id);
    List<Goods> searchByName(String name);
    List<Goods> multiSearch(String goodsName, String categoryName);
    int addGoods(Goods goods);
    int updateGoods(Goods goods);
    int deleteGoods(Long id);
}