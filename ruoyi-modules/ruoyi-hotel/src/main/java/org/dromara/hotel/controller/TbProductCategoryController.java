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
import org.dromara.hotel.domain.vo.TbProductCategoryVo;
import org.dromara.hotel.domain.bo.TbProductCategoryBo;
import org.dromara.hotel.service.ITbProductCategoryService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店商品分类
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/productCategory")
public class TbProductCategoryController extends BaseController {

    private final ITbProductCategoryService tbProductCategoryService;

    /**
     * 查询酒店商品分类列表
     */
    @SaCheckPermission("hotel:productCategory:list")
    @GetMapping("/list")
    public TableDataInfo<TbProductCategoryVo> list(TbProductCategoryBo bo, PageQuery pageQuery) {
        return tbProductCategoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店商品分类列表
     */
    @SaCheckPermission("hotel:productCategory:export")
    @Log(title = "酒店商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbProductCategoryBo bo, HttpServletResponse response) {
        List<TbProductCategoryVo> list = tbProductCategoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店商品分类", TbProductCategoryVo.class, response);
    }

    /**
     * 获取酒店商品分类详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:productCategory:query")
    @GetMapping("/{id}")
    public R<TbProductCategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbProductCategoryService.queryById(id));
    }

    /**
     * 新增酒店商品分类
     */
    @SaCheckPermission("hotel:productCategory:add")
    @Log(title = "酒店商品分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbProductCategoryBo bo) {
        return toAjax(tbProductCategoryService.insertByBo(bo));
    }

    /**
     * 修改酒店商品分类
     */
    @SaCheckPermission("hotel:productCategory:edit")
    @Log(title = "酒店商品分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbProductCategoryBo bo) {
        return toAjax(tbProductCategoryService.updateByBo(bo));
    }

    /**
     * 删除酒店商品分类
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:productCategory:remove")
    @Log(title = "酒店商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbProductCategoryService.deleteWithValidByIds(List.of(ids), true));
    }
}
