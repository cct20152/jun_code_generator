package com.fc.v2.common.base;


import com.fc.v2.common.domain.ResuTree;
import com.fc.v2.common.domain.ResultTable;


/**
 * web层通用数据处理
 *
 * @author fuce
 * @ClassName: BaseController
 * @date 2018年8月18日
 */
public class BaseController {





    /**
     * 返回 Tree 数据
     *
     * @param data
     * @return
     */
    protected static ResuTree dataTree(Object data) {
        ResuTree resuTree = new ResuTree();
        resuTree.setData(data);
        return resuTree;
    }

    /**
     * 返回数据表格数据
     *
     * @param data  表格分页数据
     * @param count
     * @return
     */
    protected static ResultTable pageTable(Object data, long count) {
        return ResultTable.pageTable(count, data);
    }


}
