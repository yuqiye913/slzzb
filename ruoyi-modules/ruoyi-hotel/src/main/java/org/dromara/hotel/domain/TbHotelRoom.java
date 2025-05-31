package org.dromara.hotel.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 酒店房间对象 tb_hotel_room
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_hotel_room")
public class TbHotelRoom extends TenantEntity {

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
     * 房间名称 中文
     */
    private String nameZh;

    /**
     * 房间名称 维文
     */
    private String nameUg;

    /**
     * WiFi编码
     */
    private String wifiSsid;

    /**
     * WiFi密码
     */
    private String wifiPassword;


}
