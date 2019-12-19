package com.bjunicom.scservice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.bjunicom.scservice.pojo.WorkOrder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
@Mapper
@Component("WorkOrderMapper")
public interface WorkOrderMapper {
    //工单录入
    void insertWorkOrder(WorkOrder wo);

    //删除工单
    void deleteWorkOrder(@Param("work_order_ID") Integer work_order_ID);

    //修改工单信息
    void modifyWorkOrder(WorkOrder wo);

    //派单
    void sendWorkOrder(WorkOrder wo);

    //转派工单
    void deliverWorkOrder(WorkOrder wo);

    //关闭工单
    void endWorkOrder(WorkOrder wo);


    //查询所有工单信息
    List<WorkOrder> selectWorkOrder();

    //根据ID查询工单信息
    WorkOrder selectById(@Param("work_order_ID") Integer work_order_ID);

    //根据名称查询工单
    List<WorkOrder> selectByName(@Param("work_order_name") String work_order_name);

    //查询某一客服关联的所有工单
    List<WorkOrder> selectAgentOA(@Param("agent_oa") String agent_oa);

    //查询某一客服关联的所有工单
    List<WorkOrder> searchOrder(@Param("agent_oa") String agent_oa, @Param("work_order_status") String work_order_status);

    //图片路径存储
    void imageStorage(WorkOrder wo);
}
