package iot.ah.demo.core.mysql;

/**
 * @description:
 * @author: yjk
 * @create: 2020-02-19 15:06
 **/
public class BaseDownResponse<T> {
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 本地消息
     */
    private String localizedMsg;
    /**
     * 返回对象主体信息
     */
    private T data;

    public BaseDownResponse() {
    }

    public BaseDownResponse(String code, String message, String localizedMsg, T data) {
        this.code = code;
        this.message = message;
        this.localizedMsg = localizedMsg;
        this.data = data;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocalizedMsg() {
        return localizedMsg;
    }

    public void setLocalizedMsg(String localizedMsg) {
        this.localizedMsg = localizedMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseDownResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", localizedMsg='" + localizedMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
