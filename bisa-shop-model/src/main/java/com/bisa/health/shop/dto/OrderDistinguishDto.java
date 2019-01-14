package com.bisa.health.shop.dto;

/**
 * 封装 快递鸟 单号识别API 返回的物流信息
 * @author Administrator
 */

public class OrderDistinguishDto {

    private String EBusinessID;// 电商用户ID
    private String LogisticCode;// 物流单号
    private boolean Success;// 成功与否

    private String ShipperCode;// 快递公司编码
    private String ShipperName;// 快递公司名称

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String eBusinessID) {
        EBusinessID = eBusinessID;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public String getShipperName() {
        return ShipperName;
    }

    public void setShipperName(String shipperName) {
        ShipperName = shipperName;
    }

    @Override
    public String toString() {
        return "OrderDistinguishDto [EBusinessID=" + EBusinessID + ", LogisticCode=" + LogisticCode + ", Success=" + Success
                + ", ShipperCode=" + ShipperCode + ", ShipperName=" + ShipperName + "]";
    }

}
