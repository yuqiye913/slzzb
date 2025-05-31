package org.dromara.hotel.service;

import org.dromara.hotel.domain.vo.TbHotelRoomVo;
import org.dromara.hotel.domain.bo.TbHotelRoomBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 酒店房间Service接口
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
public interface ITbHotelRoomService {

    /**
     * 查询酒店房间
     *
     * @param id 主键
     * @return 酒店房间
     */
    TbHotelRoomVo queryById(Long id);

    /**
     * 分页查询酒店房间列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 酒店房间分页列表
     */
    TableDataInfo<TbHotelRoomVo> queryPageList(TbHotelRoomBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的酒店房间列表
     *
     * @param bo 查询条件
     * @return 酒店房间列表
     */
    List<TbHotelRoomVo> queryList(TbHotelRoomBo bo);

    /**
     * 新增酒店房间
     *
     * @param bo 酒店房间
     * @return 是否新增成功
     */
    Boolean insertByBo(TbHotelRoomBo bo);

    /**
     * 修改酒店房间
     *
     * @param bo 酒店房间
     * @return 是否修改成功
     */
    Boolean updateByBo(TbHotelRoomBo bo);

    /**
     * 校验并批量删除酒店房间信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
