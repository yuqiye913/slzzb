package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbProduct;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 酒店商品视图对象 tb_product
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbProduct.class)
public class TbProductVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    @ExcelProperty(value = "商品编号")
    private Long id;

    /**
     * 酒店编号
     */
    @ExcelProperty(value = "酒店编号")
    private Long hotelId;

    /**
     * 酒店名称
     */
    @ExcelProperty(value = "酒店名称")
    private String hotelName;

    /**
     * 商品名称 中文
     */
    @ExcelProperty(value = "商品名称 中文")
    private String nameZh;

    /**
     * 商品名称 维文
     */
    @ExcelProperty(value = "商品名称 维文")
    private String nameUg;

    /**
     * 商品简介 中文
     */
    @ExcelProperty(value = "商品简介 中文")
    private String introductionZh;

    /**
     * 商品简介 维文
     */
    @ExcelProperty(value = "商品简介 维文")
    private String introductionUg;

    /**
     * 商品详情 中文
     */
    @ExcelProperty(value = "商品详情 中文")
    private String descriptionZh;

    /**
     * 商品详情 维文
     */
    @ExcelProperty(value = "商品详情 维文")
    private String descriptionUg;

    /**
     * 分类编号
     */
    @ExcelProperty(value = "分类编号")
    private Long categoryId;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 封面图
     */
    @ExcelProperty(value = "封面图")
    private String pic;

    /**
     * 商品状态: 0 上架（开启） 1 下架（禁用）-1 回收
     */
    @ExcelProperty(value = "商品状态: 0 上架", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "business_product_status")
    private Long status;

    /**
     * 市场价
     */
    @ExcelProperty(value = "市场价")
    private Long marketPrice;

    /**
     * 成本价
     */
    @ExcelProperty(value = "成本价")
    private Long costPrice;

    /**
     * 销售价
     */
    @ExcelProperty(value = "销售价")
    private Long price;

    /**
     * 库存
     */
    @ExcelProperty(value = "库存")
    private Long stock;

    /**
     * 商品销量
     */
    @ExcelProperty(value = "商品销量")
    private Long salesCount;

    /**
     * 商品点击量
     */
    @ExcelProperty(value = "商品点击量")
    private Long browseCount;

    /**
     * 排序字段
     */
    @ExcelProperty(value = "排序字段")
    private Long sort;


}
