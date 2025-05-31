package org.dromara.hotel.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.hotel.domain.vo.TbHotelBusinessVo;
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.mapper.TbHotelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.dromara.hotel.domain.bo.TbProductCategoryBo;
import org.dromara.hotel.domain.vo.TbProductCategoryVo;
import org.dromara.hotel.domain.TbProductCategory;
import org.dromara.hotel.mapper.TbProductCategoryMapper;
import org.dromara.hotel.service.ITbProductCategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 酒店商品分类Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@RequiredArgsConstructor
@Service
public class TbProductCategoryServiceImpl implements ITbProductCategoryService {

    private final TbProductCategoryMapper baseMapper;

    private final TbHotelMapper hotelMapper;

    /**
     * 查询酒店商品分类
     *
     * @param id 主键
     * @return 酒店商品分类
     */
    @Override
    public TbProductCategoryVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店商品分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店商品分类分页列表
     */
    @Override
    public TableDataInfo<TbProductCategoryVo> queryPageList(TbProductCategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbProductCategory> lqw = buildQueryWrapper(bo);
        Page<TbProductCategoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        List<TbProductCategoryVo> list = result.getRecords().stream().map(it -> {
            TbProductCategoryVo res = new TbProductCategoryVo();
            BeanUtils.copyProperties(it, res);

            TbHotelVo hotelVo = hotelMapper.selectVoById(res.getHotelId());
            res.setHotelName(hotelVo.getNameZh());
            return res;
        }).toList();

        result.setRecords(list);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店商品分类列表
     *
     * @param bo 查询条件
     * @return 酒店商品分类列表
     */
    @Override
    public List<TbProductCategoryVo> queryList(TbProductCategoryBo bo) {
        LambdaQueryWrapper<TbProductCategory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbProductCategory> buildQueryWrapper(TbProductCategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbProductCategory> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbProductCategory::getId);
        lqw.eq(bo.getHotelId() != null, TbProductCategory::getHotelId, bo.getHotelId());
        lqw.eq(StringUtils.isNotBlank(bo.getNameZh()), TbProductCategory::getNameZh, bo.getNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getNameUg()), TbProductCategory::getNameUg, bo.getNameUg());
        lqw.eq(bo.getSort() != null, TbProductCategory::getSort, bo.getSort());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TbProductCategory::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增酒店商品分类
     *
     * @param bo 酒店商品分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbProductCategoryBo bo) {
        TbProductCategory add = MapstructUtils.convert(bo, TbProductCategory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店商品分类
     *
     * @param bo 酒店商品分类
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbProductCategoryBo bo) {
        TbProductCategory update = MapstructUtils.convert(bo, TbProductCategory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbProductCategory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店商品分类信息
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
