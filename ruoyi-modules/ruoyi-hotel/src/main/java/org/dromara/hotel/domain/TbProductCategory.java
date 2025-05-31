package org.dromara.hotel.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 酒店商品分类对象 tb_product_category
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_product_category")
public class TbProductCategory extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id")
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
