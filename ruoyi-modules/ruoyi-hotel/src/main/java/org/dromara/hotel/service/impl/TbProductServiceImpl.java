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
import org.dromara.hotel.domain.vo.TbProductCategoryVo;
import org.dromara.hotel.mapper.TbHotelMapper;
import org.dromara.hotel.mapper.TbProductCategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.dromara.hotel.domain.bo.TbProductBo;
import org.dromara.hotel.domain.vo.TbProductVo;
import org.dromara.hotel.domain.TbProduct;
import org.dromara.hotel.mapper.TbProductMapper;
import org.dromara.hotel.service.ITbProductService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 酒店商品Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@RequiredArgsConstructor
@Service
public class TbProductServiceImpl implements ITbProductService {

    private final TbProductMapper baseMapper;

    private final TbHotelMapper hotelMapper;

    private final TbProductCategoryMapper categoryMapper;

    /**
     * 查询酒店商品
     *
     * @param id 主键
     * @return 酒店商品
     */
    @Override
    public TbProductVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店商品列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店商品分页列表
     */
    @Override
    public TableDataInfo<TbProductVo> queryPageList(TbProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbProduct> lqw = buildQueryWrapper(bo);
        Page<TbProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        List<TbProductVo> list = result.getRecords().stream().map(it -> {
            TbProductVo res = new TbProductVo();
            BeanUtils.copyProperties(it, res);

            TbHotelVo hotelVo = hotelMapper.selectVoById(res.getHotelId());
            res.setHotelName(hotelVo.getNameZh());

            TbProductCategoryVo categoryVo = categoryMapper.selectVoById(res.getCategoryId());
            res.setCategoryName(categoryVo.getNameZh());
            return res;
        }).toList();

        result.setRecords(list);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店商品列表
     *
     * @param bo 查询条件
     * @return 酒店商品列表
     */
    @Override
    public List<TbProductVo> queryList(TbProductBo bo) {
        LambdaQueryWrapper<TbProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbProduct> buildQueryWrapper(TbProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbProduct> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbProduct::getId);
        lqw.eq(bo.getHotelId() != null, TbProduct::getHotelId, bo.getHotelId());
        lqw.eq(StringUtils.isNotBlank(bo.getNameZh()), TbProduct::getNameZh, bo.getNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getNameUg()), TbProduct::getNameUg, bo.getNameUg());
        lqw.eq(StringUtils.isNotBlank(bo.getIntroductionZh()), TbProduct::getIntroductionZh, bo.getIntroductionZh());
        lqw.eq(StringUtils.isNotBlank(bo.getIntroductionUg()), TbProduct::getIntroductionUg, bo.getIntroductionUg());
        lqw.eq(StringUtils.isNotBlank(bo.getDescriptionZh()), TbProduct::getDescriptionZh, bo.getDescriptionZh());
        lqw.eq(StringUtils.isNotBlank(bo.getDescriptionUg()), TbProduct::getDescriptionUg, bo.getDescriptionUg());
        lqw.eq(bo.getCategoryId() != null, TbProduct::getCategoryId, bo.getCategoryId());
        lqw.eq(StringUtils.isNotBlank(bo.getPic()), TbProduct::getPic, bo.getPic());
        lqw.eq(bo.getStatus() != null, TbProduct::getStatus, bo.getStatus());
        lqw.eq(bo.getMarketPrice() != null, TbProduct::getMarketPrice, bo.getMarketPrice());
        lqw.eq(bo.getCostPrice() != null, TbProduct::getCostPrice, bo.getCostPrice());
        lqw.eq(bo.getPrice() != null, TbProduct::getPrice, bo.getPrice());
        lqw.eq(bo.getStock() != null, TbProduct::getStock, bo.getStock());
        lqw.eq(bo.getSalesCount() != null, TbProduct::getSalesCount, bo.getSalesCount());
        lqw.eq(bo.getBrowseCount() != null, TbProduct::getBrowseCount, bo.getBrowseCount());
        lqw.eq(bo.getSort() != null, TbProduct::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增酒店商品
     *
     * @param bo 酒店商品
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbProductBo bo) {
        TbProduct add = MapstructUtils.convert(bo, TbProduct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店商品
     *
     * @param bo 酒店商品
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbProductBo bo) {
        TbProduct update = MapstructUtils.convert(bo, TbProduct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbProduct entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店商品信息
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
