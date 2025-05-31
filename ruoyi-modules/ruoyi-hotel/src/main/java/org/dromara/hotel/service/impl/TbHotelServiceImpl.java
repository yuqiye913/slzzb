package org.dromara.hotel.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.constant.SystemConstants;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StreamUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.utils.TreeBuildUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.hotel.domain.bo.TbHotelBo;
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.domain.TbHotel;
import org.dromara.hotel.mapper.TbHotelMapper;
import org.dromara.hotel.service.ITbHotelService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 酒店Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@RequiredArgsConstructor
@Service
public class TbHotelServiceImpl implements ITbHotelService {

    private final TbHotelMapper baseMapper;

    /**
     * 查询酒店
     *
     * @param id 主键
     * @return 酒店
     */
    @Override
    public TbHotelVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店分页列表
     */
    @Override
    public TableDataInfo<TbHotelVo> queryPageList(TbHotelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbHotel> lqw = buildQueryWrapper(bo);
        Page<TbHotelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店列表
     *
     * @param bo 查询条件
     * @return 酒店列表
     */
    @Override
    public List<TbHotelVo> queryList(TbHotelBo bo) {
        LambdaQueryWrapper<TbHotel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbHotel> buildQueryWrapper(TbHotelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbHotel> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbHotel::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getNameZh()), TbHotel::getNameZh, bo.getNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getNameUg()), TbHotel::getNameUg, bo.getNameUg());
        lqw.eq(StringUtils.isNotBlank(bo.getDescriptionZh()), TbHotel::getDescriptionZh, bo.getDescriptionZh());
        lqw.eq(StringUtils.isNotBlank(bo.getDescriptionUg()), TbHotel::getDescriptionUg, bo.getDescriptionUg());
        lqw.eq(StringUtils.isNotBlank(bo.getCityZh()), TbHotel::getCityZh, bo.getCityZh());
        lqw.eq(StringUtils.isNotBlank(bo.getCityUg()), TbHotel::getCityUg, bo.getCityUg());
        lqw.eq(StringUtils.isNotBlank(bo.getAddressZh()), TbHotel::getAddressZh, bo.getAddressZh());
        lqw.eq(StringUtils.isNotBlank(bo.getAddressUg()), TbHotel::getAddressUg, bo.getAddressUg());
        lqw.eq(bo.getLongitude() != null, TbHotel::getLongitude, bo.getLongitude());
        lqw.eq(bo.getLatitude() != null, TbHotel::getLatitude, bo.getLatitude());
        lqw.eq(StringUtils.isNotBlank(bo.getFzrNameZh()), TbHotel::getFzrNameZh, bo.getFzrNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getFzrNameUg()), TbHotel::getFzrNameUg, bo.getFzrNameUg());
        lqw.eq(StringUtils.isNotBlank(bo.getFzrPhone()), TbHotel::getFzrPhone, bo.getFzrPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getKfPhone()), TbHotel::getKfPhone, bo.getKfPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getPicture()), TbHotel::getPicture, bo.getPicture());
        return lqw;
    }

    @Override
    public List<Tree<Long>> selectHotelTreeList(TbHotelBo hotel) {
        LambdaQueryWrapper<TbHotel> lqw = buildQueryWrapper(hotel);
        List<TbHotelVo> hotels = baseMapper.selectVoList(lqw);
        return buildHotelTreeSelect(hotels);
    }

    @Override
    public List<Tree<Long>> buildHotelTreeSelect(List<TbHotelVo> hotels) {
        if (CollUtil.isEmpty(hotels)) {
            return CollUtil.newArrayList();
        }
        // 获取当前列表中每一个节点的parentId，然后在列表中查找是否有id与其parentId对应，若无对应，则表明此时节点列表中，该节点在当前列表中属于顶级节点
        List<Tree<Long>> treeList = CollUtil.newArrayList();
        for (TbHotelVo d : hotels) {
            Long parentId = 0L;
            TbHotelVo TbHotelVo = StreamUtils.findFirst(hotels, it -> it.getId().longValue() == parentId);
            if (ObjectUtil.isNull(TbHotelVo)) {
                List<Tree<Long>> trees = TreeBuildUtils.build(hotels, parentId, (dept, tree) ->
                    tree.setId(dept.getId())
                        .setParentId(parentId)
                        .setName(dept.getNameZh())
//                        .setWeight(dept.getOrderNum())
                        .putExtra("disabled", SystemConstants.DISABLE.equals(dept.getStatus())));
                Tree<Long> tree = StreamUtils.findFirst(trees, it -> it.getId().longValue() == d.getId());
                treeList.add(tree);
            }
        }
        return treeList;
    }

    /**
     * 新增酒店
     *
     * @param bo 酒店
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbHotelBo bo) {
        TbHotel add = MapstructUtils.convert(bo, TbHotel.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店
     *
     * @param bo 酒店
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbHotelBo bo) {
        TbHotel update = MapstructUtils.convert(bo, TbHotel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbHotel entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店信息
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
