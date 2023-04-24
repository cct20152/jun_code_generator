package com.bjc.lcp.app.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bjc.lcp.system.entity.BaseEntity;

/**
 * @description 应用表
 * @author wujun
 * @date 2023-04-24
 */
@Data
@TableName("app_info")
public class AppInfoEntity  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 应用编号
    */
    @TableId(value = "app_no" )
    private String appNo;

    /**
    * 租户(机构)id
    */
    @TableField(value = "tenat_id" )
    private String tenaid;

    /**
    * 应用类型(0-全局公共资产应用;1-普通应用;2-机构公共资产应用)
    */
    @TableField(value = "app_type" )
    private Integer appType;

    /**
    * 中文名称
    */
    @TableField(value = "app_name" )
    private String appName;

    /**
    * 英文名称
    */
    @TableField(value = "app_eng_nam" )
    private String appEngNam;

    /**
    * 应用描述
    */
    @TableField(value = "app_desc" )
    private String appDesc;

    /**
    * 部门名称
    */
    @TableField(value = "dept" )
    private String dept;

    /**
    * 备注
    */
    @TableField(value = "remark" )
    private String remark;

    /**
    * 创建用户
    */
    @TableField(value = "create_user" )
    private String createUser;

    /**
    * 创建时间
    */
    @TableField(value = "create_time" )
    private Date createTime;

    /**
    * 最后更新用户
    */
    @TableField(value = "update_user" )
    private String updateUser;

    /**
    * 最后更新时间
    */
    @TableField(value = "update_time" )
    private Date updateTime;

    /**
    * 系统标识
    */
    @TableField(value = "sys_flg" )
    private String syflg;

    /**
    * 状态-0-正常，1-逻辑删除
    */
    @TableField(value = "deleted" )
    private String deleted;

    public AppInfoEntity() {}
}
