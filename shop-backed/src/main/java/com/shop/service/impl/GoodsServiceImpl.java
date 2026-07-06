package com.shop.service.impl;

import com.shop.domain.Goods;
import com.shop.mapper.GoodsMapper;
import com.shop.service.GoodsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectAll();
    }
    @Override
    public Goods findById(Long id) {
        return goodsMapper.selectById(id);
    }
    @Override
    public List<Goods> searchByName(String name) {
        return goodsMapper.selectLikeName(name);
    }
    @Override
    public List<Goods> multiSearch(String goodsName, String categoryName) {
        return goodsMapper.selectMultiQuery(goodsName, categoryName);
    }
    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.insertGoods(goods);
    }
    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }
    @Override
    public int deleteGoods(Long id) {
        return goodsMapper.deleteGoods(id);
    }
}