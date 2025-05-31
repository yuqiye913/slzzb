package org.dromara.hotel.service;

import cn.hutool.core.lang.tree.Tree;
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.domain.bo.TbHotelBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 酒店Service接口
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
public interface ITbHotelService {

    /**
     * 查询酒店
     *
     * @param id 主键
     * @return 酒店
     */
    TbHotelVo queryById(Long id);

    /**
     * 分页查询酒店列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店分页列表
     */
    TableDataInfo<TbHotelVo> queryPageList(TbHotelBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的酒店列表
     *
     * @param bo 查询条件
     * @return 酒店列表
     */
    List<TbHotelVo> queryList(TbHotelBo bo);

    /**
     * 查询酒店树结构信息
     *
     * @param hotel 酒店信息
     * @return 酒店树信息集合
     */
    List<Tree<Long>> selectHotelTreeList(TbHotelBo hotel);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param hotels 酒店列表
     * @return 下拉树结构列表
     */
    List<Tree<Long>> buildHotelTreeSelect(List<TbHotelVo> hotels);

    /**
     * 新增酒店
     *
     * @param bo 酒店
     * @return 是否新增成功
     */
    Boolean insertByBo(TbHotelBo bo);

    /**
     * 修改酒店
     *
     * @param bo 酒店
     * @return 是否修改成功
     */
    Boolean updateByBo(TbHotelBo bo);

    /**
     * 校验并批量删除酒店信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
