package org.dromara.hotel.service;

import org.dromara.hotel.domain.vo.TbProductVo;
import org.dromara.hotel.domain.bo.TbProductBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 酒店商品Service接口
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
public interface ITbProductService {

    /**
     * 查询酒店商品
     *
     * @param id 主键
     * @return 酒店商品
     */
    TbProductVo queryById(Long id);

    /**
     * 分页查询酒店商品列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店商品分页列表
     */
    TableDataInfo<TbProductVo> queryPageList(TbProductBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的酒店商品列表
     *
     * @param bo 查询条件
     * @return 酒店商品列表
     */
    List<TbProductVo> queryList(TbProductBo bo);

    /**
     * 新增酒店商品
     *
     * @param bo 酒店商品
     * @return 是否新增成功
     */
    Boolean insertByBo(TbProductBo bo);

    /**
     * 修改酒店商品
     *
     * @param bo 酒店商品
     * @return 是否修改成功
     */
    Boolean updateByBo(TbProductBo bo);

    /**
     * 校验并批量删除酒店商品信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
