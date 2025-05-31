package org.dromara.hotel.service;

import org.dromara.hotel.domain.vo.TbHotelEmployeeRoleVo;
import org.dromara.hotel.domain.bo.TbHotelEmployeeRoleBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 酒店员工角色Service接口
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
public interface ITbHotelEmployeeRoleService {

    /**
     * 查询酒店员工角色
     *
     * @param id 主键
     * @return 酒店员工角色
     */
    TbHotelEmployeeRoleVo queryById(Long id);

    /**
     * 分页查询酒店员工角色列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店员工角色分页列表
     */
    TableDataInfo<TbHotelEmployeeRoleVo> queryPageList(TbHotelEmployeeRoleBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的酒店员工角色列表
     *
     * @param bo 查询条件
     * @return 酒店员工角色列表
     */
    List<TbHotelEmployeeRoleVo> queryList(TbHotelEmployeeRoleBo bo);

    /**
     * 新增酒店员工角色
     *
     * @param bo 酒店员工角色
     * @return 是否新增成功
     */
    Boolean insertByBo(TbHotelEmployeeRoleBo bo);

    /**
     * 修改酒店员工角色
     *
     * @param bo 酒店员工角色
     * @return 是否修改成功
     */
    Boolean updateByBo(TbHotelEmployeeRoleBo bo);

    /**
     * 校验并批量删除酒店员工角色信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
