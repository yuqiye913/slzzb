package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbHotel;
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
 * 酒店视图对象 tb_hotel
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbHotel.class)
public class TbHotelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 酒店名称 中文
     */
    @ExcelProperty(value = "酒店名称 中文")
    private String nameZh;

    /**
     * 酒店名称 维文
     */
    @ExcelProperty(value = "酒店名称 维文")
    private String nameUg;

    /**
     * 酒店简介 中文
     */
    @ExcelProperty(value = "酒店简介 中文")
    private String descriptionZh;

    /**
     * 酒店简介 维文
     */
    @ExcelProperty(value = "酒店简介 维文")
    private String descriptionUg;

    /**
     * 所属城市 中文
     */
    @ExcelProperty(value = "所属城市 中文")
    private String cityZh;

    /**
     * 所属城市 维文
     */
    @ExcelProperty(value = "所属城市 维文")
    private String cityUg;

    /**
     * 酒店地址 中文
     */
    @ExcelProperty(value = "酒店地址 中文")
    private String addressZh;

    /**
     * 酒店地址 维文
     */
    @ExcelProperty(value = "酒店地址 维文")
    private String addressUg;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private Long longitude;

    /**
     * 纬度
     */
    @ExcelProperty(value = "纬度")
    private Long latitude;

    /**
     * 负责人名称 中文
     */
    @ExcelProperty(value = "负责人名称 中文")
    private String fzrNameZh;

    /**
     * 负责人名称 维文
     */
    @ExcelProperty(value = "负责人名称 维文")
    private String fzrNameUg;

    /**
     * 负责人联系电话
     */
    @ExcelProperty(value = "负责人联系电话")
    private String fzrPhone;

    /**
     * 客服联系电话
     */
    @ExcelProperty(value = "客服联系电话")
    private String kfPhone;

    /**
     * 图片信息
     */
    @ExcelProperty(value = "图片信息")
    private String picture;

    /**
     * 酒店状态（0正常 1停用）
     */
    @ExcelProperty(value = "酒店状态")
    private String status;


}
