package org.dromara.hotel.controller;

import java.util.List;

import cn.hutool.core.lang.tree.Tree;
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
import org.dromara.hotel.domain.vo.TbHotelVo;
import org.dromara.hotel.domain.bo.TbHotelBo;
import org.dromara.hotel.service.ITbHotelService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 酒店
 *
 * @author Lan Zhan
 * @date 2025-04-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel/hotel")
public class TbHotelController extends BaseController {

    private final ITbHotelService tbHotelService;

    /**
     * 查询酒店列表
     */
    @SaCheckPermission("hotel:hotel:list")
    @GetMapping("/list")
    public TableDataInfo<TbHotelVo> list(TbHotelBo bo, PageQuery pageQuery) {
        return tbHotelService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出酒店列表
     */
    @SaCheckPermission("hotel:hotel:export")
    @Log(title = "酒店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TbHotelBo bo, HttpServletResponse response) {
        List<TbHotelVo> list = tbHotelService.queryList(bo);
        ExcelUtil.exportExcel(list, "酒店", TbHotelVo.class, response);
    }

    /**
     * 获取酒店树列表
     */
    @SaCheckPermission("hotel:hotel:list")
    @GetMapping("/tree")
    public R<List<Tree<Long>>> deptTree(TbHotelBo hotelBo) {
        return R.ok(tbHotelService.selectHotelTreeList(hotelBo));
    }


    /**
     * 获取酒店详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("hotel:hotel:query")
    @GetMapping("/{id}")
    public R<TbHotelVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tbHotelService.queryById(id));
    }

    /**
     * 新增酒店
     */
    @SaCheckPermission("hotel:hotel:add")
    @Log(title = "酒店", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TbHotelBo bo) {
        return toAjax(tbHotelService.insertByBo(bo));
    }

    /**
     * 修改酒店
     */
    @SaCheckPermission("hotel:hotel:edit")
    @Log(title = "酒店", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TbHotelBo bo) {
        return toAjax(tbHotelService.updateByBo(bo));
    }

    /**
     * 删除酒店
     *
     * @param ids 主键串
     */
    @SaCheckPermission("hotel:hotel:remove")
    @Log(title = "酒店", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tbHotelService.deleteWithValidByIds(List.of(ids), true));
    }
}
