package org.dromara.hotel.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.mapper.TbHotelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.dromara.hotel.domain.bo.TbHotelEmployeeRoleBo;
import org.dromara.hotel.domain.vo.TbHotelEmployeeRoleVo;
import org.dromara.hotel.domain.TbHotelEmployeeRole;
import org.dromara.hotel.mapper.TbHotelEmployeeRoleMapper;
import org.dromara.hotel.service.ITbHotelEmployeeRoleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 酒店员工角色Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@RequiredArgsConstructor
@Service
public class TbHotelEmployeeRoleServiceImpl implements ITbHotelEmployeeRoleService {

    private final TbHotelEmployeeRoleMapper baseMapper;

    private final TbHotelMapper hotelMapper;

    /**
     * 查询酒店员工角色
     *
     * @param id 主键
     * @return 酒店员工角色
     */
    @Override
    public TbHotelEmployeeRoleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店员工角色列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店员工角色分页列表
     */
    @Override
    public TableDataInfo<TbHotelEmployeeRoleVo> queryPageList(TbHotelEmployeeRoleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbHotelEmployeeRole> lqw = buildQueryWrapper(bo);
        Page<TbHotelEmployeeRoleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        List<TbHotelEmployeeRoleVo> list = result.getRecords().stream().map(it -> {
            TbHotelEmployeeRoleVo res = new TbHotelEmployeeRoleVo();
            BeanUtils.copyProperties(it, res);

            TbHotelVo hotelVo = hotelMapper.selectVoById(res.getHotelId());
            res.setHotelName(hotelVo.getNameZh());
            return res;
        }).toList();

        result.setRecords(list);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店员工角色列表
     *
     * @param bo 查询条件
     * @return 酒店员工角色列表
     */
    @Override
    public List<TbHotelEmployeeRoleVo> queryList(TbHotelEmployeeRoleBo bo) {
        LambdaQueryWrapper<TbHotelEmployeeRole> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbHotelEmployeeRole> buildQueryWrapper(TbHotelEmployeeRoleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbHotelEmployeeRole> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbHotelEmployeeRole::getId);
        lqw.eq(bo.getHotelId() != null, TbHotelEmployeeRole::getHotelId, bo.getHotelId());
        lqw.like(StringUtils.isNotBlank(bo.getRoleName()), TbHotelEmployeeRole::getRoleName, bo.getRoleName());
        lqw.eq(StringUtils.isNotBlank(bo.getRoleKey()), TbHotelEmployeeRole::getRoleKey, bo.getRoleKey());
        lqw.eq(bo.getSort() != null, TbHotelEmployeeRole::getSort, bo.getSort());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TbHotelEmployeeRole::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增酒店员工角色
     *
     * @param bo 酒店员工角色
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbHotelEmployeeRoleBo bo) {
        TbHotelEmployeeRole add = MapstructUtils.convert(bo, TbHotelEmployeeRole.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店员工角色
     *
     * @param bo 酒店员工角色
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbHotelEmployeeRoleBo bo) {
        TbHotelEmployeeRole update = MapstructUtils.convert(bo, TbHotelEmployeeRole.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbHotelEmployeeRole entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店员工角色信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
