package org.dromara.hotel.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 酒店服务对象 tb_hotel_business
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_hotel_business")
public class TbHotelBusiness extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 服务编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 酒店编号
     */
    private Long hotelId;

    /**
     * 服务名称 中文
     */
    private String nameZh;

    /**
     * 服务名称 维文
     */
    private String nameUg;

    /**
     * 服务简介 中文
     */
    private String introductionZh;

    /**
     * 服务简介 维文
     */
    private String introductionUg;

    /**
     * 服务详情 中文
     */
    private String descriptionZh;

    /**
     * 服务详情 维文
     */
    private String descriptionUg;

    /**
     * 封面图
     */
    private String pic;

    /**
     * 商品状态: 0 上架（开启） 1 下架（禁用）-1 回收
     */
    private Long status;

    /**
     * 市场价
     */
    private Long marketPrice;

    /**
     * 成本价
     */
    private Long costPrice;

    /**
     * 销售价
     */
    private Long price;

    /**
     * 服务销量
     */
    private Long salesCount;

    /**
     * 服务点击量
     */
    private Long browseCount;

    /**
     * 排序字段
     */
    private Long sort;


}
