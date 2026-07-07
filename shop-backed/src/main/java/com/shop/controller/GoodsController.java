package com.shop.controller;

import com.shop.common.Result;
import com.shop.domain.Goods;
import com.shop.service.GoodsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    // 查询全部商品
    @GetMapping("/list")
    public Result<List<Goods>> list() {
        return Result.success(goodsService.findAll());
    }
    // 根据id查单个
    @GetMapping("/{id}")
    public Result<Goods> getById(@PathVariable Long id) {
        return Result.success(goodsService.findById(id));
    }

    // 单条件商品名模糊搜索
    @GetMapping("/search")
    public Result<List<Goods>> search(@RequestParam String name) {
        return Result.success(goodsService.searchByName(name));
    }

    // 多表联合双条件查询
    @GetMapping("/multiSearch")
    public Result<List<Goods>> multiSearch(
            @RequestParam(required = false) String goodsName,
            @RequestParam(required = false) String categoryName
    ) {
        List<Goods> list = goodsService.multiSearch(goodsName, categoryName);
        return Result.success(list);
    }

    // 新增商品
    @PostMapping("/add")
    public Result<String> add(@RequestBody Goods goods) {
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goodsService.addGoods(goods);
        return Result.success("新增成功");
    }

    // 修改商品
    @PutMapping("/update")
    public Result<String> update(@RequestBody Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        goodsService.updateGoods(goods);
        return Result.success("修改成功");
    }

    // 删除商品
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id){
        goodsService.deleteGoods(id);
        return Result.success("删除商品成功");
    }

    // 批量新增商品
    @PostMapping("/batchAdd")
    public Result<String> batchAdd(@RequestBody List<Goods> goodsList) {
        if (goodsList == null || goodsList.isEmpty()) {
            return Result.fail("导入数据不能为空");  // ← 这里把 error 改成 fail
        }
        for (Goods goods : goodsList) {
            goods.setCreateTime(LocalDateTime.now());
            goods.setUpdateTime(LocalDateTime.now());
        }
        int count = goodsService.batchInsert(goodsList);
        return Result.success("成功导入 " + count + " 条数据");
    }
}