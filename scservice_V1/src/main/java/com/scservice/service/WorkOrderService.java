package com.scservice.service;

import com.scservice.mapper.WorkOrderMapper;
import com.scservice.pojo.Admin;
import com.scservice.pojo.WorkOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WorkOrderService {

    //工单录入
     void insertWorkOrder(WorkOrder workOrder);

    //删除工单
     void deleteWorkOrder(Long workOrderId);

    //修改工单信息
     WorkOrder modifyWorkOrder(WorkOrder workOrder);

    //轮询派单
     void sendWorkOrder(WorkOrder workOrder,String admin_oa);

    //转派工单
     void deliverWorkOrder(String workOrder_uid, Admin admin);

    //查询所有工单信息
    public List<WorkOrder> searchAllOrder();

    //根据工单号查询工单信息
    public List<WorkOrder> searchOrderByUid(String uid);

    //根据工单联系电话查询工单
    public List<WorkOrder> searchOrderByPhone(String workOrderPhone);

    //根据id查询工单
    public WorkOrder searchOrderById(Long workOrderId);

    //查询某一客服关联的所有工单
    public List<WorkOrder> selectAgentOA(String agentOa);

    //查询待办的工单列表（做统计）
    public List<WorkOrder> selectTodoList();

    //查询已经处理的工单列表（做统计）
    public List<WorkOrder> selectDoList();
    //图片上传
    public void imageStorage(Long workOrderId,String image);

}
