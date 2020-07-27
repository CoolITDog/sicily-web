package zy.com.cn.sicily.web.model;

import javax.persistence.Table;

/**
 * @title: WeChatConfig
 * @description: 微信配置相关实体
 * @author: zhangyan
 * @date: 2020-07-24 16:56
 * @version: 1.0
 **/
@Table(name = "sicily_we_chat_config")
public class WeChatConfig extends BaseModel{

    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private Integer merchantId;

    /**
     * 配置名称
     */
    private String configName;

    /**
     * 配置
     */
    private String configContent;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent;
    }

    @Override
    public String toString() {
        return "WeChatConfig{" +
                "merchantId=" + merchantId +
                ", configName='" + configName + '\'' +
                ", configContent='" + configContent + '\'' +
                "} " + super.toString();
    }
}
