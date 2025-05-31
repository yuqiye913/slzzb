package org.dromara.hotel.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 酒店员工对象 tb_hotel_employee
 *
 * @author Lan Zhan
 * @date 2025-04-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_hotel_employee")
public class TbHotelEmployee extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 酒店编号
     */
    private Long hotelId;

    /**
     * 员工姓名 中文
     */
    private String userNameZh;

    /**
     * 员工姓名 维文
     */
    private String userNameUg;

    /**
     * openId
     */
    private String openId;


}
