package org.dromara.hotel.domain.bo;

import org.dromara.hotel.domain.TbHotelRoom;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 酒店房间业务对象 tb_hotel_room
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TbHotelRoom.class, reverseConvertGenerate = false)
public class TbHotelRoomBo extends BaseEntity {

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
     * 房间名称 中文
     */
    @NotBlank(message = "房间名称 中文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nameZh;

    /**
     * 房间名称 维文
     */
    @NotBlank(message = "房间名称 维文不能为空", groups = { AddGroup.class, EditGroup.class })
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
