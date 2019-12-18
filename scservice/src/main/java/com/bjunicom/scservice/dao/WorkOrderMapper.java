package com.bjunicom.scservice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.bjunicom.scservice.pojo.WorkOrder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
@Component("WorkOrderMapper")
public interface WorkOrderMapper {
    //工单录入
    void insertWorkOrder(WorkOrder wo);
    //删除工单
    void deleteWorkOrder(@Param( "workOrderId" )Integer workOrderId);
    //修改工单信息
    void modifyWorkOrder(@Param( "workOrderId" )Integer workOrderId,@Param( "workOrderPhone" )String workOrderPhone,@Param( "workOrderLessee" )String workOrderLessee,@Param( "workOrderProblem" )String workOrderProblem,@Param( "workOrderImage" )String workOrderImage);

    //派单
    void sendWorkOrder(@Param( "workOrderId" )Integer workOrderId, @Param( "agentOa" )String agentOa, @Param( "workOrderStart" ) Timestamp workOrderStart);
    //转派工单
    void deliverWorkOrder(@Param( "workOrderId" )Integer workOrderId, @Param( "agentOa" )String agentOa, @Param( "workOrderStart" ) Timestamp workOrderStart);
    //关闭工单
    void endWorkOrder(@Param( "workOrderId" )Integer workOrderId, @Param( "workOrderEnd" ) Timestamp workOrderEnd);


    //查询所有工单信息
    List<WorkOrder> selectWorkOrder();
    //根据ID查询工单信息
    WorkOrder selectById(@Param("workOrderId")Integer workOrderId);
    //根据名称查询工单
    List<WorkOrder> selectByName(@Param( "workOrderName" )String workOrderName);
    //查询某一客服关联的所有工单
    List<WorkOrder> selectAgentOA(@Param( "agentOa" )String agentOa);
    //查询某一客服关联的所有工单
    List<WorkOrder> searchOrder(@Param( "agentOa" )String agentOa, @Param("workOrderStatus")String workOrderStatus);

    //图片路径存储
    void imageStorage(@Param("workOrderId")Integer workOrderId,@Param("image")String image);
}
