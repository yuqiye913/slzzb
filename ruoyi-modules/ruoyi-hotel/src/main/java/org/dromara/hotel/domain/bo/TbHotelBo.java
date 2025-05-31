package org.dromara.hotel.domain.bo;

import org.dromara.hotel.domain.TbHotel;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 酒店业务对象 tb_hotel
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TbHotel.class, reverseConvertGenerate = false)
public class TbHotelBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 酒店名称 中文
     */
    @NotBlank(message = "酒店名称 中文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nameZh;

    /**
     * 酒店名称 维文
     */
    @NotBlank(message = "酒店名称 维文不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "负责人名称 中文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fzrNameZh;

    /**
     * 负责人名称 维文
     */
    @NotBlank(message = "负责人名称 维文不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fzrNameUg;

    /**
     * 负责人联系电话
     */
    @NotBlank(message = "负责人联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fzrPhone;

    /**
     * 客服联系电话
     */
    @NotBlank(message = "客服联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String kfPhone;

    /**
     * 图片信息
     */
    private String picture;


}
