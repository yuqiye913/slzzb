package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbHotelEmployee;
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
 * 酒店员工视图对象 tb_hotel_employee
 *
 * @author Lan Zhan
 * @date 2025-04-18
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbHotelEmployee.class)
public class TbHotelEmployeeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
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
     * 员工姓名 中文
     */
    @ExcelProperty(value = "员工姓名 中文")
    private String userNameZh;

    /**
     * 员工姓名 维文
     */
    @ExcelProperty(value = "员工姓名 维文")
    private String userNameUg;

    /**
     * openId
     */
    @ExcelProperty(value = "openId")
    private String openId;


}
