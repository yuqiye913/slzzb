package org.dromara.hotel.domain.bo;

import org.dromara.hotel.domain.TbProductCategory;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 酒店商品分类业务对象 tb_product_category
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TbProductCategory.class, reverseConvertGenerate = false)
public class TbProductCategoryBo extends BaseEntity {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 酒店编号
     */
    private Long hotelId;

    /**
     * 分类名称 中文
     */
    private String nameZh;

    /**
     * 分类名称 维文
     */
    private String nameUg;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 分类状态（0正常 1停用）
     */
    private String status;


}
