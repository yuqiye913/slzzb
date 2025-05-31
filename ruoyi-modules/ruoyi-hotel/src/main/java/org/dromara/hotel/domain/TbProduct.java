package org.dromara.hotel.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 酒店商品对象 tb_product
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_product")
public class TbProduct extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 酒店编号
     */
    private Long hotelId;

    /**
     * 商品名称 中文
     */
    private String nameZh;

    /**
     * 商品名称 维文
     */
    private String nameUg;

    /**
     * 商品简介 中文
     */
    private String introductionZh;

    /**
     * 商品简介 维文
     */
    private String introductionUg;

    /**
     * 商品详情 中文
     */
    private String descriptionZh;

    /**
     * 商品详情 维文
     */
    private String descriptionUg;

    /**
     * 分类编号
     */
    private Long categoryId;

    /**
     * 封面图
     */
    private String pic;

    /**
     * 商品状态: 0 上架（开启） 1 下架（禁用）-1 回收
     */
    private Long status;

    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 成本价
     */
    private Long costPrice;

    /**
     * 销售价
     */
    private Long price;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 商品销量
     */
    private Long salesCount;

    /**
     * 商品点击量
     */
    private Long browseCount;

    /**
     * 排序字段
     */
    private Long sort;


}
