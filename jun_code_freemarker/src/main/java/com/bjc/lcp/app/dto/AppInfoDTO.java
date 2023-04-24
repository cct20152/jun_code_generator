package com.bjc.lcp.app.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable; 
import com.bjc.lcp.system.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @description 应用表
 * @author wujun
 * @date 2023-04-24
 */
@Data
@ApiModel("应用表")
public class AppInfoDTO  extends BaseEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 应用编号
    */
    @ApiModelProperty("应用编号") 
    private String appNo;

    /**
    * 租户(机构)id
    */
    @ApiModelProperty("租户(机构)id") 
    private String tenaid;

    /**
    * 应用类型(0-全局公共资产应用;1-普通应用;2-机构公共资产应用)
    */
    @ApiModelProperty("应用类型(0-全局公共资产应用;1-普通应用;2-机构公共资产应用)") 
    private Integer appType;

    /**
    * 中文名称
    */
    @ApiModelProperty("中文名称") 
    private String appName;

    /**
    * 英文名称
    */
    @ApiModelProperty("英文名称") 
    private String appEngNam;

    /**
    * 应用描述
    */
    @ApiModelProperty("应用描述") 
    private String appDesc;

    /**
    * 部门名称
    */
    @ApiModelProperty("部门名称") 
    private String dept;

    /**
    * 备注
    */
    @ApiModelProperty("备注") 
    private String remark;

    /**
    * 创建用户
    */
    @ApiModelProperty("创建用户") 
    private String createUser;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间") 
    private Date createTime;

    /**
    * 最后更新用户
    */
    @ApiModelProperty("最后更新用户") 
    private String updateUser;

    /**
    * 最后更新时间
    */
    @ApiModelProperty("最后更新时间") 
    private Date updateTime;

    /**
    * 系统标识
    */
    @ApiModelProperty("系统标识") 
    private String syflg;

    /**
    * 状态-0-正常，1-逻辑删除
    */
    @ApiModelProperty("状态-0-正常，1-逻辑删除") 
    private String deleted;

    public AppInfoDTO() {}
}
