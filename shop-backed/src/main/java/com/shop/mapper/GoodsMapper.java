package com.shop.mapper;
import com.shop.domain.Goods;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface GoodsMapper {
    List<Goods> selectAll();
    Goods selectById(Long id);
    List<Goods> selectLikeName(String name);
    int insertGoods(Goods goods);
    int updateGoods(Goods goods);
    int deleteGoods(Long id);
    List<Goods> selectMultiQuery(@Param("goodsName") String goodsName, @Param("categoryName") String categoryName);
    int batchInsert(List<Goods> goodsList);
}