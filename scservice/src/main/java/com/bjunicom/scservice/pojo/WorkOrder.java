package com.bjunicom.scservice.pojo;


public class WorkOrder {

  private long workOrderId;
  private String agentOa;
  private String wechatId;
  private String workOrderName;
  private String workOrderLessee;
  private String workOrderPhone;
  private String workOrderProblem;
  private String workOrderImage;
  private String workOrderStatus;
  private java.sql.Timestamp workOrderStart;
  private java.sql.Timestamp workOrderEnd;
  private java.sql.Timestamp workOrderDelivery;
  private String workOrderBackup;


  public long getWorkOrderId() {
    return workOrderId;
  }

  public void setWorkOrderId(long workOrderId) {
    this.workOrderId = workOrderId;
  }


  public String getAgentOa() {
    return agentOa;
  }

  public void setAgentOa(String agentOa) {
    this.agentOa = agentOa;
  }


  public String getWechatId() {
    return wechatId;
  }

  public void setWechatId(String wechatId) {
    this.wechatId = wechatId;
  }


  public String getWorkOrderName() {
    return workOrderName;
  }

  public void setWorkOrderName(String workOrderName) {
    this.workOrderName = workOrderName;
  }


  public String getWorkOrderLessee() {
    return workOrderLessee;
  }

  public void setWorkOrderLessee(String workOrderLessee) {
    this.workOrderLessee = workOrderLessee;
  }


  public String getWorkOrderPhone() {
    return workOrderPhone;
  }

  public void setWorkOrderPhone(String workOrderPhone) {
    this.workOrderPhone = workOrderPhone;
  }


  public String getWorkOrderProblem() {
    return workOrderProblem;
  }

  public void setWorkOrderProblem(String workOrderProblem) {
    this.workOrderProblem = workOrderProblem;
  }


  public String getWorkOrderImage() {
    return workOrderImage;
  }

  public void setWorkOrderImage(String workOrderImage) {
    this.workOrderImage = workOrderImage;
  }


  public String getWorkOrderStatus() {
    return workOrderStatus;
  }

  public void setWorkOrderStatus(String workOrderStatus) {
    this.workOrderStatus = workOrderStatus;
  }


  public java.sql.Timestamp getWorkOrderStart() {
    return workOrderStart;
  }

  public void setWorkOrderStart(java.sql.Timestamp workOrderStart) {
    this.workOrderStart = workOrderStart;
  }


  public java.sql.Timestamp getWorkOrderEnd() {
    return workOrderEnd;
  }

  public void setWorkOrderEnd(java.sql.Timestamp workOrderEnd) {
    this.workOrderEnd = workOrderEnd;
  }


  public java.sql.Timestamp getWorkOrderDelivery() {
    return workOrderDelivery;
  }

  public void setWorkOrderDelivery(java.sql.Timestamp workOrderDelivery) {
    this.workOrderDelivery = workOrderDelivery;
  }


  public String getWorkOrderBackup() {
    return workOrderBackup;
  }

  public void setWorkOrderBackup(String workOrderBackup) {
    this.workOrderBackup = workOrderBackup;
  }

}
