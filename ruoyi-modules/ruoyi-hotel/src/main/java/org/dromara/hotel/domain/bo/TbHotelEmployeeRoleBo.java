package org.dromara.hotel.domain.bo;

import org.dromara.hotel.domain.TbHotelEmployeeRole;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 酒店员工角色业务对象 tb_hotel_employee_role
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TbHotelEmployeeRole.class, reverseConvertGenerate = false)
public class TbHotelEmployeeRoleBo extends BaseEntity {

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 酒店编号
     */
    @NotNull(message = "酒店编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long hotelId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roleName;

    /**
     * 角色权限字符串
     */
    @NotBlank(message = "角色权限字符串不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roleKey;

    /**
     * 显示顺序
     */
    private Long sort;

    /**
     * 角色状态（0正常 1停用）
     */
    @NotBlank(message = "角色状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;


}
