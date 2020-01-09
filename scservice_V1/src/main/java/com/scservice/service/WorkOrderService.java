package com.scservice.service;

import com.scservice.pojo.WorkOrder;
import java.util.List;

public interface WorkOrderService {

    //工单录入
     void insertWorkOrder(WorkOrder workOrder);

    //删除工单
     void deleteWorkOrder(Integer workOrderId);

    //修改工单信息
     void modifyWorkOrder(WorkOrder workOrder);

    //轮询派单
     void sendWorkOrder(WorkOrder workOrder);

    //转派工单
     void deliverWorkOrder(WorkOrder workOrder);

    //查询所有工单信息
     List<WorkOrder> searchAllOrder();

    //根据工单号查询工单信息
     List<WorkOrder> searchOrderByUid(String uid);

    //根据工单联系电话查询工单
     List<WorkOrder> searchOrderByPhone(String workOrderPhone);

    //根据id查询工单
     WorkOrder searchOrderById(Integer workOrderId);

    //查询某一客服关联的所有工单
     List<WorkOrder> selectAgentId(Long agentId);

    //查询待办的工单列表（做统计）
     List<WorkOrder> selectTodoList();

    //查询已经处理的工单列表（做统计）
     List<WorkOrder> selectDoList();
    //图片上传
     void imageStorage(Long workOrderId,String image);

}
