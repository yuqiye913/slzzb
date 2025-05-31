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
import org.dromara.hotel.domain.vo.TbHotelEmployeeVo;
import org.dromara.hotel.domain.bo.TbHotelEmployeeBo;
import org.dromara.hotel.service.ITbHotelEmployeeService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店员工
 *
 * @author Lan Zhan
 * @date 2025-04-18
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/employee")
public class TbHotelEmployeeController extends BaseController {

    private final ITbHotelEmployeeService tbHotelEmployeeService;

    /**
     * 查询酒店员工列表
     */
    @SaCheckPermission("hotel:employee:list")
    @GetMapping("/list")
    public TableDataInfo<TbHotelEmployeeVo> list(TbHotelEmployeeBo bo, PageQuery pageQuery) {
        return tbHotelEmployeeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店员工列表
     */
    @SaCheckPermission("hotel:employee:export")
    @Log(title = "酒店员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbHotelEmployeeBo bo, HttpServletResponse response) {
        List<TbHotelEmployeeVo> list = tbHotelEmployeeService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店员工", TbHotelEmployeeVo.class, response);
    }

    /**
     * 获取酒店员工详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:employee:query")
    @GetMapping("/{id}")
    public R<TbHotelEmployeeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbHotelEmployeeService.queryById(id));
    }

    /**
     * 新增酒店员工
     */
    @SaCheckPermission("hotel:employee:add")
    @Log(title = "酒店员工", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbHotelEmployeeBo bo) {
        return toAjax(tbHotelEmployeeService.insertByBo(bo));
    }

    /**
     * 修改酒店员工
     */
    @SaCheckPermission("hotel:employee:edit")
    @Log(title = "酒店员工", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbHotelEmployeeBo bo) {
        return toAjax(tbHotelEmployeeService.updateByBo(bo));
    }

    /**
     * 删除酒店员工
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:employee:remove")
    @Log(title = "酒店员工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbHotelEmployeeService.deleteWithValidByIds(List.of(ids), true));
    }
}
