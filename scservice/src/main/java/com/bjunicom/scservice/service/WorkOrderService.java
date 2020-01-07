package com.bjunicom.scservice.service;

import com.bjunicom.scservice.dao.WorkOrderMapper;
import com.bjunicom.scservice.pojo.WorkOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class WorkOrderService {
    @Autowired
    WorkOrderMapper workOrderMapper;

    //工单录入
    public void insertWorkOrder(WorkOrder wo) {
        workOrderMapper.insertWorkOrder(wo);
    }

    //删除工单
    public void deleteWorkOrder(int work_order_ID) {
        workOrderMapper.deleteWorkOrder(work_order_ID);
    }

    //修改工单信息
    public void modifyWorkOrder(WorkOrder wo){
        workOrderMapper.modifyWorkOrder(wo);
    }

    //派单
    public void sendWorkOrder(WorkOrder wo) {
        workOrderMapper.sendWorkOrder(wo);
    }

    //转派工单
    public void deliverWorkOrder(WorkOrder wo) {
        workOrderMapper.deliverWorkOrder(wo);
    }

    //关闭工单
    public void endWorkOrder(WorkOrder wo) {
        workOrderMapper.endWorkOrder(wo);
    }


    //查询所有工单信息
    public List<WorkOrder> selectWorkOrder() {
        return workOrderMapper.selectWorkOrder();
    }

    //根据ID查询工单信息
    public WorkOrder selectById(int work_order_ID) {
        return workOrderMapper.selectById(work_order_ID);
    }

    //根据名称查询工单
    public List<WorkOrder> selectByName(String work_order_name) {
        return workOrderMapper.selectByName(work_order_name);
    }

    //查询某一客服关联的所有工单
    public List<WorkOrder> selectAgentOA(String agent_oa) {
        return workOrderMapper.selectAgentOA(agent_oa);
    }

    //查询某一客服关联的所有工单
    public List<WorkOrder> searchOrder(String agent_oa, String work_order_status) {
        return workOrderMapper.searchOrder(agent_oa, work_order_status);
    }

    //图片上传
    public void imageStorage(WorkOrder wo) {
        workOrderMapper.imageStorage(wo);
    }

}
