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
import org.dromara.hotel.domain.bo.TbHotelRoomBo;
import org.dromara.hotel.domain.vo.TbHotelRoomVo;
import org.dromara.hotel.domain.TbHotelRoom;
import org.dromara.hotel.mapper.TbHotelRoomMapper;
import org.dromara.hotel.service.ITbHotelRoomService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 酒店房间Service业务层处理
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@RequiredArgsConstructor
@Service
public class TbHotelRoomServiceImpl implements ITbHotelRoomService {

    private final TbHotelRoomMapper baseMapper;

    private final TbHotelMapper hotelMapper;


    /**
     * 查询酒店房间
     *
     * @param id 主键
     * @return 酒店房间
     */
    @Override
    public TbHotelRoomVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询酒店房间列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店房间分页列表
     */
    @Override
    public TableDataInfo<TbHotelRoomVo> queryPageList(TbHotelRoomBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TbHotelRoom> lqw = buildQueryWrapper(bo);
        Page<TbHotelRoomVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        List<TbHotelRoomVo> list = result.getRecords().stream().map(it -> {
            TbHotelRoomVo res = new TbHotelRoomVo();
            BeanUtils.copyProperties(it, res);

            TbHotelVo hotelVo = hotelMapper.selectVoById(res.getHotelId());
            res.setHotelName(hotelVo.getNameZh());
            return res;
        }).toList();

        result.setRecords(list);

        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的酒店房间列表
     *
     * @param bo 查询条件
     * @return 酒店房间列表
     */
    @Override
    public List<TbHotelRoomVo> queryList(TbHotelRoomBo bo) {
        LambdaQueryWrapper<TbHotelRoom> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TbHotelRoom> buildQueryWrapper(TbHotelRoomBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TbHotelRoom> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TbHotelRoom::getId);
        lqw.eq(bo.getHotelId() != null, TbHotelRoom::getHotelId, bo.getHotelId());

        lqw.eq(StringUtils.isNotBlank(bo.getNameZh()), TbHotelRoom::getNameZh, bo.getNameZh());
        lqw.eq(StringUtils.isNotBlank(bo.getNameUg()), TbHotelRoom::getNameUg, bo.getNameUg());
        lqw.eq(StringUtils.isNotBlank(bo.getWifiSsid()), TbHotelRoom::getWifiSsid, bo.getWifiSsid());
        lqw.eq(StringUtils.isNotBlank(bo.getWifiPassword()), TbHotelRoom::getWifiPassword, bo.getWifiPassword());
        return lqw;
    }

    /**
     * 新增酒店房间
     *
     * @param bo 酒店房间
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TbHotelRoomBo bo) {
        TbHotelRoom add = MapstructUtils.convert(bo, TbHotelRoom.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改酒店房间
     *
     * @param bo 酒店房间
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TbHotelRoomBo bo) {
        TbHotelRoom update = MapstructUtils.convert(bo, TbHotelRoom.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TbHotelRoom entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除酒店房间信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
