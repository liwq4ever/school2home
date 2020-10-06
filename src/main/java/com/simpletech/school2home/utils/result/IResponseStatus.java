package com.simpletech.school2home.utils.result;

/**
 * @项目名称： cywebnew
 * @类名称： IResponseStatus
 * @类描述：响应状态信息
 * @创建时间： 2019年5月28日11:04:17
 * @version:
 */
public interface IResponseStatus {

    String getCode();

    String getMessage();

    public enum ResponseStatus implements IResponseStatus {

        EXCEPTION("0", "EXCEPTION"),//后台异常
        SUCCESS("1", "SUCCESS"), //请求成功
        PARAMERROR("2", "PARAMERROR"),//参数异常
        NULLUSER("3", "NULLUSER"),//用户不存在
        MANY_LOGINS("4", "MANY_LOGINS"),
        IPLIMITED("5", "IPLIMITED"),
        ILLEGALDATA("6", "ILLEGALDATA");
        private String code;
        private String message;

        private ResponseStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }
}