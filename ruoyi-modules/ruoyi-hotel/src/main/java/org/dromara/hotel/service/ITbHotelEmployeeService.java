package org.dromara.hotel.service;

import org.dromara.hotel.domain.vo.TbHotelEmployeeVo;
import org.dromara.hotel.domain.bo.TbHotelEmployeeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 酒店员工Service接口
 *
 * @author Lan Zhan
 * @date 2025-04-18
 */
public interface ITbHotelEmployeeService {

    /**
     * 查询酒店员工
     *
     * @param id 主键
     * @return 酒店员工
     */
    TbHotelEmployeeVo queryById(Long id);

    /**
     * 分页查询酒店员工列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店员工分页列表
     */
    TableDataInfo<TbHotelEmployeeVo> queryPageList(TbHotelEmployeeBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的酒店员工列表
     *
     * @param bo 查询条件
     * @return 酒店员工列表
     */
    List<TbHotelEmployeeVo> queryList(TbHotelEmployeeBo bo);

    /**
     * 新增酒店员工
     *
     * @param bo 酒店员工
     * @return 是否新增成功
     */
    Boolean insertByBo(TbHotelEmployeeBo bo);

    /**
     * 修改酒店员工
     *
     * @param bo 酒店员工
     * @return 是否修改成功
     */
    Boolean updateByBo(TbHotelEmployeeBo bo);

    /**
     * 校验并批量删除酒店员工信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
