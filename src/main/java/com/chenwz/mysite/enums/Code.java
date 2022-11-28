package com.chenwz.mysite.enums;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Loger
 */
@Getter
public enum Code {

    /**
     * 未定义
     */
    UNDEFINED(-1, "未定义"),

    /**
     * 业务状态码
     */
    SUCCESS(0, "success"),

    UN_AUTHORIZATION(401, "鉴权异常"),

    PERMISSION_DENIED(403, "权限不通过"),

    ZLB_SSO_ERROR(407, "浙里办单点登录失败"),

    PARAM_ERROR(1001, "参数异常"),

    HTTP_METHOD_NOT_SUPPORTED(1002, "请求方法错误"),

    HTTP_CONTENT_TYPE_NOT_SUPPORTED(1003, "不支持的Content-Type"),

    USER_DATA_FILE_NOT_EXIST(1004, "用户信息文件不存在"),

    USER_NOT_EXIST(1005, "该用户不存在"),

    PASSWORD_IS_WRONG(1006, "密码错误"),

    PLEASE_LOGIN(1007, "请登录"),

    NEW_PASSWORD_NOT_THE_SAME(1008, "两次输入的密码不一致"),

    HTTP_REQUEST_EXCEPTION(1010, "HTTP请求异常"),

    USER_LOGIN_NAME_NUMBER_ERROR(1012, "该用户名用户在本系统中存在多个, 系统异常, 请联系运维人员"),


    /**
     * 100000-109999   区块链相关
     */
    HYPERCHAIN_RECEIPT_CODE_ERROR(100010, "区块链返回码异常"),

    INVOKE_HYPERCHAIN_FAIL(100020, "调用区块链失败"),

    DEPLOY_ERROR(100030, "部署智能合约失败"),


    TX_ERROR(100040, "数据上链失败"),

    TX_NOT_EXIST(100041, "交易不存在"),

    GET_BLOCK_FAIL(100042, "查询区块信息失败"),

    GET_TX_FAIL(100043, "查询交易信息失败"),

    GET_NODE_FAIL(100044, "查询节点信息失败"),

    /**
     * 110000-119999   文件相关
     */
    FILE_SIZE_LIMIT(110010, "文件大小超过限制"),

    MINIO_UPLOAD_ERROR(110020, "minio上传文件失败"),

    FILE_ID_EXISTS(110030, "系统自动生成文件ID已存在, 系统异常"),

    DISK_SAVE_ERROR(110040, "磁盘存储文件失败"),

    DISK_DOWNLOAD_ERROR(110050, "磁盘下载文件失败"),

    FILE_NOT_EXIST(110060, "文件不存在"),

    FILE_TYPE_ERROR(110061, "上传文件格式不正确"),

    GET_FILE_INPUT_STREAM_ERROR(110070, "获取文件内容失败, 请重试"),

    OSS_DOWNLOAD_ERROR(110080, "从OSS下载文件失败"),

    /**
     * 数据拉取相关错误码
     */
    DATASOURCE_NOT_EXIST(20001, "数据源不存在/未设置"),


    /**
     * 权限相关错误码
     */
     AUTH_ENUM_NOT_EXIST(30001,"权限枚举不存在"),


     ENUM_NOT_EXIST(30002,"枚举不存在"),


    SYSTEM_ERROR(99999, "系统异常");


    private int code;
    private String msg;

    Code(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private static final Map<Integer, Code> CODE_MAP = new HashMap<>();

    static {
        for (Code code : Code.values()) {
            CODE_MAP.put(code.code, code);
        }
    }

    public static Code getEnumByCode(int code) {
        return CODE_MAP.get(code);
    }

    public static boolean isValid(int code) {
        return CODE_MAP.get(code) != null;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
