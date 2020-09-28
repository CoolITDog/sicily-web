package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: WechatMessageConfig
 * @description: 微信模板消息
 * @author: zhangyan
 * @date: 2020-09-04 10:40
 * @version: 1.0
 **/
@Table(name = "sicily_wechat_message_config")
public class WechatMessageConfig extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 消息类型
     */
    private Integer msgType;
    /**
     * 消息名称
     */
    private String msgName;
    /**
     * 状态：Y：启用
     */
    private String status;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 模板内容
     */
    private String templateText;
    /**
     * 模板
     */
    private String template;
    /**
     * appId
     */
    private String appId;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "WechatMessageConfig{" +
                "msgType=" + msgType +
                ", msgName='" + msgName + '\'' +
                ", status='" + status + '\'' +
                ", templateId='" + templateId + '\'' +
                ", templateText='" + templateText + '\'' +
                ", template='" + template + '\'' +
                ", appId='" + appId + '\'' +
                "} " + super.toString();
    }
}
