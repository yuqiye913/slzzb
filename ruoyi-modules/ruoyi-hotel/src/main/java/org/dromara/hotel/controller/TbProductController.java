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
import org.dromara.hotel.domain.vo.TbProductVo;
import org.dromara.hotel.domain.bo.TbProductBo;
import org.dromara.hotel.service.ITbProductService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店商品
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/product")
public class TbProductController extends BaseController {

    private final ITbProductService tbProductService;

    /**
     * 查询酒店商品列表
     */
    @SaCheckPermission("hotel:product:list")
    @GetMapping("/list")
    public TableDataInfo<TbProductVo> list(TbProductBo bo, PageQuery pageQuery) {
        return tbProductService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店商品列表
     */
    @SaCheckPermission("hotel:product:export")
    @Log(title = "酒店商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbProductBo bo, HttpServletResponse response) {
        List<TbProductVo> list = tbProductService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店商品", TbProductVo.class, response);
    }

    /**
     * 获取酒店商品详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:product:query")
    @GetMapping("/{id}")
    public R<TbProductVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbProductService.queryById(id));
    }

    /**
     * 新增酒店商品
     */
    @SaCheckPermission("hotel:product:add")
    @Log(title = "酒店商品", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbProductBo bo) {
        return toAjax(tbProductService.insertByBo(bo));
    }

    /**
     * 修改酒店商品
     */
    @SaCheckPermission("hotel:product:edit")
    @Log(title = "酒店商品", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbProductBo bo) {
        return toAjax(tbProductService.updateByBo(bo));
    }

    /**
     * 删除酒店商品
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:product:remove")
    @Log(title = "酒店商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbProductService.deleteWithValidByIds(List.of(ids), true));
    }
}
