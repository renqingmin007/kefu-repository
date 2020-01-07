package com.bjunicom.scservice.pojo;


import java.sql.Timestamp;
import java.util.ArrayList;

public class WorkOrder {

    private long work_order_ID;
    private String agent_oa;
    private String wechat_ID;
    private String work_order_name;
    private String work_order_lessee;
    private String work_order_phone;
    private String work_order_problem;
    private String work_order_image;
    private String work_order_status;
    private Timestamp work_order_start;
    private Timestamp work_order_end;
    private Timestamp work_order_delivery;
    private String work_order_backup;

    public long getWork_order_ID() {
        return work_order_ID;
    }

    public void setWork_order_ID(long work_order_ID) {
        this.work_order_ID = work_order_ID;
    }

    public String getAgent_oa() {
        return agent_oa;
    }

    public void setAgent_oa(String agent_oa) {
        this.agent_oa = agent_oa;
    }

    public String getWechat_ID() {
        return wechat_ID;
    }

    public void setWechat_ID(String wechat_ID) {
        this.wechat_ID = wechat_ID;
    }

    public String getWork_order_name() {
        return work_order_name;
    }

    public void setWork_order_name(String work_order_name) {
        this.work_order_name = work_order_name;
    }

    public String getWork_order_lessee() {
        return work_order_lessee;
    }

    public void setWork_order_lessee(String work_order_lessee) {
        this.work_order_lessee = work_order_lessee;
    }

    public String getWork_order_phone() {
        return work_order_phone;
    }

    public void setWork_order_phone(String work_order_phone) {
        this.work_order_phone = work_order_phone;
    }

    public String getWork_order_problem() {
        return work_order_problem;
    }

    public void setWork_order_problem(String work_order_problem) {
        this.work_order_problem = work_order_problem;
    }

    public String getWork_order_image() {
        return work_order_image;
    }

    public void setWork_order_image(String work_order_image) {
        this.work_order_image = work_order_image;
    }

    public String getWork_order_status() {
        return work_order_status;
    }

    public void setWork_order_status(String work_order_status) {
        this.work_order_status = work_order_status;
    }

    public Timestamp getWork_order_start() {
        return work_order_start;
    }

    public void setWork_order_start(Timestamp work_order_start) {
        this.work_order_start = work_order_start;
    }

    public Timestamp getWork_order_end() {
        return work_order_end;
    }

    public void setWork_order_end(Timestamp work_order_end) {
        this.work_order_end = work_order_end;
    }

    public Timestamp getWork_order_delivery() {
        return work_order_delivery;
    }

    public void setWork_order_delivery(Timestamp work_order_delivery) {
        this.work_order_delivery = work_order_delivery;
    }

    public String getWork_order_backup() {
        return work_order_backup;
    }

    public void setWork_order_backup(String work_order_backup) {
        this.work_order_backup = work_order_backup;
    }

    public ArrayList turnList()
    {
        ArrayList wolist = new ArrayList();
        wolist.add(this.work_order_ID);
        wolist.add(this.agent_oa);
        wolist.add(this.wechat_ID);
        wolist.add(this.work_order_name);
        wolist.add(this.work_order_lessee);
        wolist.add(this.work_order_phone);
        wolist.add(this.work_order_problem);
        wolist.add(this.work_order_image);
        wolist.add(this.work_order_status);
        wolist.add(this.work_order_start);
        wolist.add(this.work_order_end);
        wolist.add(this.work_order_delivery);
        wolist.add(this.work_order_backup);
        return wolist;
    }
}
