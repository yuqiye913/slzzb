package org.dromara.hotel.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.hotel.domain.vo.TbHotelEmployeeRoleVo;
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.mapper.TbHotelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.dromara.hotel.domain.bo.TbHotelBusinessBo;
import org.dromara.hotel.domain.vo.TbHotelBusinessVo;
import org.dromara.hotel.domain.TbHotelBusiness;
import org.dromara.hotel.mapper.TbHotelBusinessMapper;
import org.dromara.hotel.service.ITbHotelBusinessService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 酒店服务Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@RequiredArgsConstructor
@Service
public class TbHotelBusinessServiceImpl implements ITbHotelBusinessService {

    private final TbHotelBusinessMapper baseMapper;

    private final TbHotelMapper hotelMapper;

    /**
     * 查询酒店服务
     *
     * @param id 主键
     * @return 酒店服务
     */
    @Override
    public TbHotelBusinessVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店服务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店服务分页列表
     */
    @Override
    public TableDataInfo<TbHotelBusinessVo> queryPageList(TbHotelBusinessBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbHotelBusiness> lqw = buildQueryWrapper(bo);
        Page<TbHotelBusinessVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        List<TbHotelBusinessVo> list = result.getRecords().stream().map(it -> {
            TbHotelBusinessVo res = new TbHotelBusinessVo();
            BeanUtils.copyProperties(it, res);

            TbHotelVo hotelVo = hotelMapper.selectVoById(res.getHotelId());
            res.setHotelName(hotelVo.getNameZh());
            return res;
        }).toList();

        result.setRecords(list);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店服务列表
     *
     * @param bo 查询条件
     * @return 酒店服务列表
     */
    @Override
    public List<TbHotelBusinessVo> queryList(TbHotelBusinessBo bo) {
        LambdaQueryWrapper<TbHotelBusiness> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbHotelBusiness> buildQueryWrapper(TbHotelBusinessBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbHotelBusiness> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbHotelBusiness::getId);
        lqw.eq(bo.getHotelId() != null, TbHotelBusiness::getHotelId, bo.getHotelId());
        lqw.eq(StringUtils.isNotBlank(bo.getNameZh()), TbHotelBusiness::getNameZh, bo.getNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getNameUg()), TbHotelBusiness::getNameUg, bo.getNameUg());
        lqw.eq(StringUtils.isNotBlank(bo.getIntroductionZh()), TbHotelBusiness::getIntroductionZh, bo.getIntroductionZh());
        lqw.eq(StringUtils.isNotBlank(bo.getIntroductionUg()), TbHotelBusiness::getIntroductionUg, bo.getIntroductionUg());
        lqw.eq(StringUtils.isNotBlank(bo.getDescriptionZh()), TbHotelBusiness::getDescriptionZh, bo.getDescriptionZh());
        lqw.eq(StringUtils.isNotBlank(bo.getDescriptionUg()), TbHotelBusiness::getDescriptionUg, bo.getDescriptionUg());
        lqw.eq(StringUtils.isNotBlank(bo.getPic()), TbHotelBusiness::getPic, bo.getPic());
        lqw.eq(bo.getStatus() != null, TbHotelBusiness::getStatus, bo.getStatus());
        lqw.eq(bo.getMarketPrice() != null, TbHotelBusiness::getMarketPrice, bo.getMarketPrice());
        lqw.eq(bo.getCostPrice() != null, TbHotelBusiness::getCostPrice, bo.getCostPrice());
        lqw.eq(bo.getPrice() != null, TbHotelBusiness::getPrice, bo.getPrice());
        lqw.eq(bo.getSalesCount() != null, TbHotelBusiness::getSalesCount, bo.getSalesCount());
        lqw.eq(bo.getBrowseCount() != null, TbHotelBusiness::getBrowseCount, bo.getBrowseCount());
        lqw.eq(bo.getSort() != null, TbHotelBusiness::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增酒店服务
     *
     * @param bo 酒店服务
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbHotelBusinessBo bo) {
        TbHotelBusiness add = MapstructUtils.convert(bo, TbHotelBusiness.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店服务
     *
     * @param bo 酒店服务
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbHotelBusinessBo bo) {
        TbHotelBusiness update = MapstructUtils.convert(bo, TbHotelBusiness.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbHotelBusiness entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店服务信息
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
