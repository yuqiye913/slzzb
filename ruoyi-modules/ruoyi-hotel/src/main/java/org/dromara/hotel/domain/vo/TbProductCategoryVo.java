package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbProductCategory;
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
 * 酒店商品分类视图对象 tb_product_category
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbProductCategory.class)
public class TbProductCategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
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
     * 分类名称 中文
     */
    @ExcelProperty(value = "分类名称 中文")
    private String nameZh;

    /**
     * 分类名称 维文
     */
    @ExcelProperty(value = "分类名称 维文")
    private String nameUg;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 分类状态（0正常 1停用）
     */
    @ExcelProperty(value = "分类状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
