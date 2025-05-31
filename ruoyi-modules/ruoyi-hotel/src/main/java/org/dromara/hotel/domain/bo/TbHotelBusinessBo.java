package org.dromara.hotel.domain.bo;

import org.dromara.hotel.domain.TbHotelBusiness;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 酒店服务业务对象 tb_hotel_business
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TbHotelBusiness.class, reverseConvertGenerate = false)
public class TbHotelBusinessBo extends BaseEntity {

    /**
     * 服务编号
     */
    @NotNull(message = "服务编号不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 酒店编号
     */
    @NotNull(message = "酒店编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long hotelId;

    /**
     * 服务名称 中文
     */
    @NotBlank(message = "服务名称 中文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nameZh;

    /**
     * 服务名称 维文
     */
    @NotBlank(message = "服务名称 维文不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "封面图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pic;

    /**
     * 商品状态: 0 上架（开启） 1 下架（禁用）-1 回收
     */
    @NotNull(message = "商品状态: 0 上架（开启） 1 下架（禁用）-1 回收不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 市场价
     */
    @NotNull(message = "市场价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long marketPrice;

    /**
     * 成本价
     */
    @NotNull(message = "成本价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long costPrice;

    /**
     * 销售价
     */
    @NotNull(message = "销售价不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "排序字段不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;


}
