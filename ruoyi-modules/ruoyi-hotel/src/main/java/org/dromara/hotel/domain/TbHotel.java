package org.dromara.hotel.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 酒店对象 tb_hotel
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_hotel")
public class TbHotel extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 酒店名称 中文
     */
    private String nameZh;

    /**
     * 酒店名称 维文
     */
    private String nameUg;

    /**
     * 酒店简介 中文
     */
    private String descriptionZh;

    /**
     * 酒店简介 维文
     */
    private String descriptionUg;

    /**
     * 所属城市 中文
     */
    private String cityZh;

    /**
     * 所属城市 维文
     */
    private String cityUg;

    /**
     * 酒店地址 中文
     */
    private String addressZh;

    /**
     * 酒店地址 维文
     */
    private String addressUg;

    /**
     * 经度
     */
    private Long longitude;

    /**
     * 纬度
     */
    private Long latitude;

    /**
     * 负责人名称 中文
     */
    private String fzrNameZh;

    /**
     * 负责人名称 维文
     */
    private String fzrNameUg;

    /**
     * 负责人联系电话
     */
    private String fzrPhone;

    /**
     * 客服联系电话
     */
    private String kfPhone;

    /**
     * 图片信息
     */
    private String picture;


}
