package com.fc.v2.controller.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResuTree;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.custom.TsysTables;
import com.fc.v2.model.custom.TsysTablesVo;
import com.fc.v2.model.custom.autocode.AutoConfigModel;
import com.fc.v2.model.custom.autocode.AutoDictType;
import com.fc.v2.model.custom.autocode.BeanColumn;
import com.fc.v2.model.custom.autocode.TableInfo;
import com.fc.v2.service.ITDictService;
import com.fc.v2.util.AutoCode.AutoCodeUtil;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码自动生成
 *
 * @author fuce
 * @ClassName: AutoCodeController
 * @date 2019-08-13 00:34
 */

@Controller
@RequestMapping("/autoCodeController")
public class AutoCodeController extends BaseController {

    private String prefix = "admin/autoCode";
    @Autowired
    private ITGeneratorService generatorService;




    @Autowired
    private ITDictService dictService;


    @GetMapping("/global")
    public String global() {
        return prefix + "/global";
    }

    /**
     * 树结构查询所有表
     *
     * @return
     * @author fuce
     * @Date 2021年1月15日 下午2:21:19
     */
    @GetMapping("/selectTables")
    @ResponseBody
    public ResuTree selectTables() {
        List<TsysTables> list = generatorService.queryList(null);
        List<TsysTablesVo> TreeList = new ArrayList<TsysTablesVo>();
        for (int i = 0; i < list.size(); i++) {
            TsysTablesVo tablesVo = new TsysTablesVo(i + 1, -1, list.get(i).getTableName(), list.get(i).getEngine(),
                    list.get(i).getTableComment(), list.get(i).getTableModel(), list.get(i).getCreateTime(),
                    list.get(i).getTableName() + " > " + list.get(i).getTableComment());
            TreeList.add(tablesVo);
        }

        TsysTablesVo tables = new TsysTablesVo();
        tables.setTableModel("all");
        tables.setTableAndName("所有表");
        tables.setParentId(0);
        tables.setId(-1);
        TreeList.add(tables);
        return dataTree(TreeList);
    }

    /**
     * 根据表查询表字段详情
     *
     * @param tableName
     * @return
     * @author fuce
     * @Date 2019年8月15日 上午1:10:42
     */
    @GetMapping("/queryTableInfo")
    @ResponseBody
    public ResultTable queryTableInfo(String tableName) {
        List<BeanColumn> list = generatorService.queryColumns2(tableName);
        return pageTable(list, list.size());
    }

    /**
     * 生成文件
     *
     * @author fuce
     * @Date 2021年1月15日 下午2:21:55
     */
    @PostMapping("/createAuto")
    @ResponseBody
    public AjaxResult createAuto(@RequestBody AutoConfigModel autoConfigModel) {
        // 根据表名查询表字段集合
        List<BeanColumn> list = autoConfigModel.getBeanColumns();// generatorService.queryColumns2(autoConfigModel.getTableName());
        //根据表的设置查询出字典表需要的数据

        list.stream().forEach(item -> {
            if (item.getHtmlType() == 2) {
                AutoDictType autoDictType = new AutoDictType(dictService.getSysDictType(item.getDictTypeName()), dictService.getType(item.getDictTypeName()));
                item.setAutoDictType(autoDictType);
            }

        });


        // 初始化表信息
        TableInfo tableInfo = new TableInfo(autoConfigModel.getTableName(), list, autoConfigModel.getTableComment());

        AutoCodeUtil.autoCodeOneModel(tableInfo, autoConfigModel);
        return AjaxResult.success();
    }

    /**
     * 生成文件Zip
     *
     * @throws IOException
     * @author fuce
     * @Date 2021年1月15日 下午2:21:55
     */
    @GetMapping("/createAutoZip")
    @ResponseBody
    public void createAutoZip(AutoConfigModel autoConfigModel, HttpServletResponse response) throws IOException {
        byte[] b;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 根据表名查询表字段集合
        List<BeanColumn> list = generatorService.queryColumns2(autoConfigModel.getTableName());
        // 初始化表信息
        TableInfo tableInfo = new TableInfo(autoConfigModel.getTableName(), list, autoConfigModel.getTableComment());
        // 自动生成
        AutoCodeUtil.autoCodeOneModel(tableInfo, autoConfigModel, zip);
        IOUtils.closeQuietly(zip);
        b = outputStream.toByteArray();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"v2.zip\"");
        response.addHeader("Content-Length", "" + b.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(b, response.getOutputStream());
    }

    /**
     * 预览生成文件
     *
     * @author fuce
     * @Date 2021年1月15日 下午2:21:55
     */
    @GetMapping("/viewAuto")
    public String viewAuto(AutoConfigModel autoConfigModel, ModelMap model) {
        List<BeanColumn> list = generatorService.queryColumns2(autoConfigModel.getTableName());
        TableInfo tableInfo = new TableInfo(autoConfigModel.getTableName(), list, autoConfigModel.getTableComment());//获取表信息
        Map<String, String> map = AutoCodeUtil.viewAuto(tableInfo, autoConfigModel);
        model.put("viewmap", map);
        return prefix + "/view";
    }

}