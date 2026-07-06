package com.shop.domain;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Goods {
    private Long id;
    @NotBlank(message = "商品名称不能为空")
    private String goodsName;
    @DecimalMin(value = "0.01", message = "商品价格必须大于0")
    private BigDecimal goodsPrice;
    @Min(value = 0, message = "库存不能为负数")
    private Integer goodsStock;
    private String goodsDesc;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long categoryId;
    private String categoryName;
}