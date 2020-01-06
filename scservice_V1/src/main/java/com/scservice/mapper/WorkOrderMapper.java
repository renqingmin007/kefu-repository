package com.scservice.mapper;

import com.scservice.pojo.Admin;
import com.scservice.pojo.WorkOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkOrderMapper {
    //工单录入及轮询派单
    void insertWorkOrder(WorkOrder workOrder);

    //删除工单
    void deleteWorkOrder(@Param("workOrderId") Long workOrderId);

    //修改工单信息
    WorkOrder modifyWorkOrder(WorkOrder workOrder);

    //轮询派单
    void sendWorkOrder(@Param("workOrder") WorkOrder workOrder, @Param("agentOa") String admin_oa);

    //转派工单
    void deliverWorkOrder(String workOrder_uid, Admin admin);

    //查询所有工单信息
    List<WorkOrder> searchAllOrder();

    //根据工单号查询工单信息
    List<WorkOrder> searchOrderByUid(@Param("workOrderUid") String uid);

    //根据工单联系电话查询工单
    List<WorkOrder> searchOrderByPhone(@Param("workOrderPhone") String workOrderPhone);

    //根据id查询工单
    WorkOrder searchOrderById(@Param("workOrderId") Long workOrderId);

    //查询某一客服关联的所有工单
    List<WorkOrder> selectAgentOA(@Param("agentOa") String agentOa);


    //查询待办工单列表
    List<WorkOrder> selectTodoList();

    //查询已处理工单列表
    List<WorkOrder> selectDoList();

    //图片路径存储
    void imageStorage(@Param("workOrderId") Long workOrderId, @Param("image") String image);

}
