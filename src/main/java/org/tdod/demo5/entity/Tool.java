package org.tdod.demo5.entity;

public class Tool {

    private String toolCode;
    private ToolTypeEnum toolType;
    private String brand;

    public Tool() {

    }

    public Tool(String toolCode, ToolTypeEnum toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public ToolTypeEnum getToolType() {
        return toolType;
    }

    public void setToolType(ToolTypeEnum toolType) {
        this.toolType = toolType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
