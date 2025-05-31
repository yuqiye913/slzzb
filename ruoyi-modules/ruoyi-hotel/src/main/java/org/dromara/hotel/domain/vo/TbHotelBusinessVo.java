package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbHotelBusiness;
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
 * 酒店服务视图对象 tb_hotel_business
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbHotelBusiness.class)
public class TbHotelBusinessVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 服务编号
     */
    @ExcelProperty(value = "服务编号")
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
     * 服务名称 中文
     */
    @ExcelProperty(value = "服务名称 中文")
    private String nameZh;

    /**
     * 服务名称 维文
     */
    @ExcelProperty(value = "服务名称 维文")
    private String nameUg;

    /**
     * 服务简介 中文
     */
    @ExcelProperty(value = "服务简介 中文")
    private String introductionZh;

    /**
     * 服务简介 维文
     */
    @ExcelProperty(value = "服务简介 维文")
    private String introductionUg;

    /**
     * 服务详情 中文
     */
    @ExcelProperty(value = "服务详情 中文")
    private String descriptionZh;

    /**
     * 服务详情 维文
     */
    @ExcelProperty(value = "服务详情 维文")
    private String descriptionUg;

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
     * 服务销量
     */
    @ExcelProperty(value = "服务销量")
    private Long salesCount;

    /**
     * 服务点击量
     */
    @ExcelProperty(value = "服务点击量")
    private Long browseCount;

    /**
     * 排序字段
     */
    @ExcelProperty(value = "排序字段")
    private Long sort;


}
