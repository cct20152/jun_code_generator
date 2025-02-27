package com.bjc.lcp.system.common.utils;


import com.bjc.lcp.common.base.BaseResponseCode;
import com.bjc.lcp.common.base.ResponseCodeInterface;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回值DataResult
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Data
public class DataResult {

    /**
     * 请求响应code，0为成功 其他为失败
     */
    @ApiModelProperty(value = "请求响应code，0为成功 其他为失败", name = "code")
    private int code;

    /**
     * 响应异常码详细信息
     */
    @ApiModelProperty(value = "响应异常码详细信息", name = "msg")
    private String msg;

    @ApiModelProperty(value = "需要返回的数据", name = "data")
    private Object data;

    public DataResult(int code, Object data) {
        this.code = code;
        this.data = data;
        this.msg = null;
    }

    public DataResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }


    public DataResult() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(Object data) {
        this.data = data;
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
    }

    public DataResult(ResponseCodeInterface responseCodeInterface) {
        this.data = null;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }

    public DataResult(ResponseCodeInterface responseCodeInterface, Object data) {
        this.data = data;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }

    /**
     * 操作成功 data为null
     */
    public static DataResult success() {
        return new DataResult();
    }

    /**
     * 操作成功 data 不为null
     */
    public static DataResult success(Object data) {
        return new DataResult(data);
    }

    /**
     * 操作失败 data 不为null
     */
    public static DataResult fail(String msg) {
        return new DataResult(BaseResponseCode.OPERATION_ERRO.getCode(), msg);
    }

    /**
     * 操作成功 data 为null，传Msg消息
     */
    public static DataResult successMsg(String msg) {
        return new DataResult(BaseResponseCode.SUCCESS.getCode(), msg);
    }
    /*public static DataResult success(String msg) {
        return new DataResult(BaseResponseCode.SUCCESS.getCode(), msg);
    }*/


    /**
     * 自定义返回  data为null
     */
    public static DataResult getResult(int code, String msg) {
        return new DataResult(code, msg);
    }

    /**
     * 自定义返回 入参一般是异常code枚举 data为空
     */
    public static DataResult getResult(BaseResponseCode responseCode) {
        return new DataResult(responseCode);
    }


    /**
     * 系统未定义的异常返回结果
     *
     * @param baseResponseCode
     * @return
     */
    public static DataResult otherException(BaseResponseCode baseResponseCode) {

        DataResult dataResult = new DataResult();
        dataResult.setCode(baseResponseCode.getCode());
        dataResult.setMsg(baseResponseCode.getMsg());
        dataResult.setData(null);
        return dataResult;
    }

}
