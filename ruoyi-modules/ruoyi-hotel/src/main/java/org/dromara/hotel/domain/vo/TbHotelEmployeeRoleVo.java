package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbHotelEmployeeRole;
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
 * 酒店员工角色视图对象 tb_hotel_employee_role
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbHotelEmployeeRole.class)
public class TbHotelEmployeeRoleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ExcelProperty(value = "角色ID")
    private Long id;

    /**
     * 租户编号
     */
    @ExcelProperty(value = "租户编号")
    private String tenantId;

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
     * 角色名称
     */
    @ExcelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @ExcelProperty(value = "角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long sort;

    /**
     * 角色状态（0正常 1停用）
     */
    @ExcelProperty(value = "角色状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
