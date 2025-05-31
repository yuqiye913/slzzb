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
import org.dromara.hotel.domain.vo.TbHotelBusinessVo;
import org.dromara.hotel.domain.bo.TbHotelBusinessBo;
import org.dromara.hotel.service.ITbHotelBusinessService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店服务
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/business")
public class TbHotelBusinessController extends BaseController {

    private final ITbHotelBusinessService tbHotelBusinessService;

    /**
     * 查询酒店服务列表
     */
    @SaCheckPermission("hotel:business:list")
    @GetMapping("/list")
    public TableDataInfo<TbHotelBusinessVo> list(TbHotelBusinessBo bo, PageQuery pageQuery) {
        return tbHotelBusinessService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店服务列表
     */
    @SaCheckPermission("hotel:business:export")
    @Log(title = "酒店服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbHotelBusinessBo bo, HttpServletResponse response) {
        List<TbHotelBusinessVo> list = tbHotelBusinessService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店服务", TbHotelBusinessVo.class, response);
    }

    /**
     * 获取酒店服务详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:business:query")
    @GetMapping("/{id}")
    public R<TbHotelBusinessVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbHotelBusinessService.queryById(id));
    }

    /**
     * 新增酒店服务
     */
    @SaCheckPermission("hotel:business:add")
    @Log(title = "酒店服务", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbHotelBusinessBo bo) {
        return toAjax(tbHotelBusinessService.insertByBo(bo));
    }

    /**
     * 修改酒店服务
     */
    @SaCheckPermission("hotel:business:edit")
    @Log(title = "酒店服务", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbHotelBusinessBo bo) {
        return toAjax(tbHotelBusinessService.updateByBo(bo));
    }

    /**
     * 删除酒店服务
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:business:remove")
    @Log(title = "酒店服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbHotelBusinessService.deleteWithValidByIds(List.of(ids), true));
    }
}
