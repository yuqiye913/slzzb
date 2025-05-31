package org.dromara.hotel.domain.bo;

import org.dromara.hotel.domain.TbHotelEmployee;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 酒店员工业务对象 tb_hotel_employee
 *
 * @author Lan Zhan
 * @date 2025-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TbHotelEmployee.class, reverseConvertGenerate = false)
public class TbHotelEmployeeBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 酒店编号
     */
    @NotNull(message = "酒店编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long hotelId;

    /**
     * 员工姓名 中文
     */
    @NotBlank(message = "员工姓名 中文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userNameZh;

    /**
     * 员工姓名 维文
     */
    @NotBlank(message = "员工姓名 维文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userNameUg;

    /**
     * openId
     */
    @NotBlank(message = "openId不能为空", groups = { AddGroup.class, EditGroup.class })
    private String openId;


}
