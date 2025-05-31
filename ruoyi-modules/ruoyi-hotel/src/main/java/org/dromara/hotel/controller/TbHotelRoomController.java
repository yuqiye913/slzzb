package org.dromara.hotel.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.hotel.domain.vo.TbHotelRoomVo;
import org.dromara.hotel.domain.bo.TbHotelRoomBo;
import org.dromara.hotel.service.ITbHotelRoomService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店房间
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/room")
public class TbHotelRoomController extends BaseController {

    private final ITbHotelRoomService tbHotelRoomService;

    /**
     * 查询酒店房间列表
     */
    @SaCheckPermission("hotel:room:list")
    @GetMapping("/list")
    public TableDataInfo<TbHotelRoomVo> list(TbHotelRoomBo bo, PageQuery pageQuery) {
        return tbHotelRoomService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店房间列表
     */
    @SaCheckPermission("hotel:room:export")
    @Log(title = "酒店房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbHotelRoomBo bo, HttpServletResponse response) {
        List<TbHotelRoomVo> list = tbHotelRoomService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店房间", TbHotelRoomVo.class, response);
    }

    /**
     * 获取酒店房间详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:room:query")
    @GetMapping("/{id}")
    public R<TbHotelRoomVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbHotelRoomService.queryById(id));
    }

    /**
     * 新增酒店房间
     */
    @SaCheckPermission("hotel:room:add")
    @Log(title = "酒店房间", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbHotelRoomBo bo) {
        return toAjax(tbHotelRoomService.insertByBo(bo));
    }

    /**
     * 修改酒店房间
     */
    @SaCheckPermission("hotel:room:edit")
    @Log(title = "酒店房间", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbHotelRoomBo bo) {
        return toAjax(tbHotelRoomService.updateByBo(bo));
    }

    /**
     * 删除酒店房间
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:room:remove")
    @Log(title = "酒店房间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbHotelRoomService.deleteWithValidByIds(List.of(ids), true));
    }
}
