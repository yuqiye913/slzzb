package org.dromara.hotel.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.hotel.domain.vo.TbHotelRoomVo;
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.mapper.TbHotelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.dromara.hotel.domain.bo.TbHotelEmployeeBo;
import org.dromara.hotel.domain.vo.TbHotelEmployeeVo;
import org.dromara.hotel.domain.TbHotelEmployee;
import org.dromara.hotel.mapper.TbHotelEmployeeMapper;
import org.dromara.hotel.service.ITbHotelEmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 酒店员工Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-18
 */
@RequiredArgsConstructor
@Service
public class TbHotelEmployeeServiceImpl implements ITbHotelEmployeeService {


    private final TbHotelEmployeeMapper baseMapper;

    private final TbHotelMapper hotelMapper;

    /**
     * 查询酒店员工
     *
     * @param id 主键
     * @return 酒店员工
     */
    @Override
    public TbHotelEmployeeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店员工列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店员工分页列表
     */
    @Override
    public TableDataInfo<TbHotelEmployeeVo> queryPageList(TbHotelEmployeeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbHotelEmployee> lqw = buildQueryWrapper(bo);
        Page<TbHotelEmployeeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        List<TbHotelEmployeeVo> list = result.getRecords().stream().map(it -> {
            TbHotelEmployeeVo res = new TbHotelEmployeeVo();
            BeanUtils.copyProperties(it, res);

            TbHotelVo hotelVo = hotelMapper.selectVoById(res.getHotelId());
            res.setHotelName(hotelVo.getNameZh());
            return res;
        }).toList();

        result.setRecords(list);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店员工列表
     *
     * @param bo 查询条件
     * @return 酒店员工列表
     */
    @Override
    public List<TbHotelEmployeeVo> queryList(TbHotelEmployeeBo bo) {
        LambdaQueryWrapper<TbHotelEmployee> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbHotelEmployee> buildQueryWrapper(TbHotelEmployeeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbHotelEmployee> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbHotelEmployee::getId);
        lqw.eq(bo.getHotelId() != null, TbHotelEmployee::getHotelId, bo.getHotelId());
        lqw.eq(StringUtils.isNotBlank(bo.getUserNameZh()), TbHotelEmployee::getUserNameZh, bo.getUserNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getUserNameUg()), TbHotelEmployee::getUserNameUg, bo.getUserNameUg());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenId()), TbHotelEmployee::getOpenId, bo.getOpenId());
        return lqw;
    }

    /**
     * 新增酒店员工
     *
     * @param bo 酒店员工
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbHotelEmployeeBo bo) {
        TbHotelEmployee add = MapstructUtils.convert(bo, TbHotelEmployee.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店员工
     *
     * @param bo 酒店员工
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbHotelEmployeeBo bo) {
        TbHotelEmployee update = MapstructUtils.convert(bo, TbHotelEmployee.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbHotelEmployee entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店员工信息
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
