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
import org.dromara.hotel.domain.vo.TbHotelEmployeeRoleVo;
import org.dromara.hotel.domain.bo.TbHotelEmployeeRoleBo;
import org.dromara.hotel.service.ITbHotelEmployeeRoleService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店员工角色
 *
 * @author Lan Zhan
 * @date 2025-04-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/employeeRole")
public class TbHotelEmployeeRoleController extends BaseController {

    private final ITbHotelEmployeeRoleService tbHotelEmployeeRoleService;

    /**
     * 查询酒店员工角色列表
     */
    @SaCheckPermission("hotel:employeeRole:list")
    @GetMapping("/list")
    public TableDataInfo<TbHotelEmployeeRoleVo> list(TbHotelEmployeeRoleBo bo, PageQuery pageQuery) {
        return tbHotelEmployeeRoleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店员工角色列表
     */
    @SaCheckPermission("hotel:employeeRole:export")
    @Log(title = "酒店员工角色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbHotelEmployeeRoleBo bo, HttpServletResponse response) {
        List<TbHotelEmployeeRoleVo> list = tbHotelEmployeeRoleService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店员工角色", TbHotelEmployeeRoleVo.class, response);
    }

    /**
     * 获取酒店员工角色详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:employeeRole:query")
    @GetMapping("/{id}")
    public R<TbHotelEmployeeRoleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbHotelEmployeeRoleService.queryById(id));
    }

    /**
     * 新增酒店员工角色
     */
    @SaCheckPermission("hotel:employeeRole:add")
    @Log(title = "酒店员工角色", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbHotelEmployeeRoleBo bo) {
        return toAjax(tbHotelEmployeeRoleService.insertByBo(bo));
    }

    /**
     * 修改酒店员工角色
     */
    @SaCheckPermission("hotel:employeeRole:edit")
    @Log(title = "酒店员工角色", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbHotelEmployeeRoleBo bo) {
        return toAjax(tbHotelEmployeeRoleService.updateByBo(bo));
    }

    /**
     * 删除酒店员工角色
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:employeeRole:remove")
    @Log(title = "酒店员工角色", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbHotelEmployeeRoleService.deleteWithValidByIds(List.of(ids), true));
    }
}
