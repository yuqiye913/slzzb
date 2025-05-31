package org.dromara.hotel.domain.vo;

import org.dromara.hotel.domain.TbHotelRoom;
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
 * 酒店房间视图对象 tb_hotel_room
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TbHotelRoom.class)
public class TbHotelRoomVo implements Serializable {

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
     * 房间名称 中文
     */
    @ExcelProperty(value = "房间名称 中文")
    private String nameZh;

    /**
     * 房间名称 维文
     */
    @ExcelProperty(value = "房间名称 维文")
    private String nameUg;

    /**
     * WiFi编码
     */
    @ExcelProperty(value = "WiFi编码")
    private String wifiSsid;

    /**
     * WiFi密码
     */
    @ExcelProperty(value = "WiFi密码")
    private String wifiPassword;


}
