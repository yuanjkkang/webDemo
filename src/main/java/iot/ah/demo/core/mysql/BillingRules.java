package iot.ah.demo.core.mysql;

/**
 * @description: 计费规则-下行
 * 接口路径：/CommunityParkingService/BillingRules
 * @author: yjk
 * @create: 2020-02-19 15:03
 **/
public class BillingRules{
    private String parkId;
    /**
     * 计费规则id
     */
    private String feeRuleId;
    /**
     * 计费规则名称
     */
    private String feeRuleName;
    /**
     * 计费规则函数脚本
     */
    private String feeRule;
    /**
     * 计费规则描述
     */
    private String description;
    /**
     * 计费规则状态，生效LIVE 失效UNLIVE
     */
    private String status;

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getFeeRuleId() {
        return feeRuleId;
    }

    public void setFeeRuleId(String feeRuleId) {
        this.feeRuleId = feeRuleId;
    }

    public String getFeeRuleName() {
        return feeRuleName;
    }

    public void setFeeRuleName(String feeRuleName) {
        this.feeRuleName = feeRuleName;
    }

    public String getFeeRule() {
        return feeRule;
    }

    public void setFeeRule(String feeRule) {
        this.feeRule = feeRule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BillingRules{" +
                "parkId='" + parkId + '\'' +
                ", feeRuleId='" + feeRuleId + '\'' +
                ", feeRuleName='" + feeRuleName + '\'' +
                ", feeRule='" + feeRule + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
