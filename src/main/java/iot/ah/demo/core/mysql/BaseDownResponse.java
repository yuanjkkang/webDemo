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
    private int code;
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
    private String id;

    public BaseDownResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseDownResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", localizedMsg='" + localizedMsg + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
