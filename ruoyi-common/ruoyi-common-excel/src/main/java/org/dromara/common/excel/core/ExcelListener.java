package org.dromara.common.excel.core;

import cn.idev.excel.read.listener.ReadListener;

/**
 * Excel 导入监听
 *
 * @author Lan Zhan
 */
public interface ExcelListener<T> extends ReadListener<T> {

    ExcelResult<T> getExcelResult();

}
