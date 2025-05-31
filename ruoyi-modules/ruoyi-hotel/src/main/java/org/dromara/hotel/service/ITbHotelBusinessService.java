package org.dromara.hotel.service;

import org.dromara.hotel.domain.vo.TbHotelBusinessVo;
import org.dromara.hotel.domain.bo.TbHotelBusinessBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 酒店服务Service接口
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
public interface ITbHotelBusinessService {

    /**
     * 查询酒店服务
     *
     * @param id 主键
     * @return 酒店服务
     */
    TbHotelBusinessVo queryById(Long id);

    /**
     * 分页查询酒店服务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店服务分页列表
     */
    TableDataInfo<TbHotelBusinessVo> queryPageList(TbHotelBusinessBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的酒店服务列表
     *
     * @param bo 查询条件
     * @return 酒店服务列表
     */
    List<TbHotelBusinessVo> queryList(TbHotelBusinessBo bo);

    /**
     * 新增酒店服务
     *
     * @param bo 酒店服务
     * @return 是否新增成功
     */
    Boolean insertByBo(TbHotelBusinessBo bo);

    /**
     * 修改酒店服务
     *
     * @param bo 酒店服务
     * @return 是否修改成功
     */
    Boolean updateByBo(TbHotelBusinessBo bo);

    /**
     * 校验并批量删除酒店服务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
