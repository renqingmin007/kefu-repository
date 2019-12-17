package com.bjunicom.scservice.service;

import com.bjunicom.scservice.dao.WorkOrderMapper;
import com.bjunicom.scservice.pojo.WorkOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WorkOrderService {
    @Autowired
    WorkOrderMapper workOrderMapper;
    //工单录入
    public void insertWorkOrder(WorkOrder wo)
    {
        workOrderMapper.insertWorkOrder(wo);
    }
    //删除工单
    public void deleteWorkOrder(int workOrderId)
    {
        workOrderMapper.deleteWorkOrder(workOrderId);
    }
    //修改工单信息
    public void modifyWorkOrder(int workOrderId,String workOrderPhone,String workOrderLessee,String workOrderProblem,String workOrderImage)
    {
        workOrderMapper.modifyWorkOrder(workOrderId, workOrderPhone, workOrderLessee, workOrderProblem, workOrderImage);
    }

    //派单
    public void sendWorkOrder(int workOrderId, String agentOa, Timestamp workOrderStart)
    {
        workOrderMapper.sendWorkOrder(workOrderId, agentOa, workOrderStart);
    }
    //转派工单
    public void deliverWorkOrder(int workOrderId, String agentOa, Timestamp workOrderStart)
    {
        workOrderMapper.deliverWorkOrder(workOrderId, agentOa, workOrderStart);
    }
    //关闭工单
    public void endWorkOrder(int workOrderId, Timestamp workOrderEnd)
    {
        workOrderMapper.endWorkOrder(workOrderId, workOrderEnd);
    }


    //查询所有工单信息
    public List<WorkOrder> selectWorkOrder()
    {
        return workOrderMapper.selectWorkOrder();
    }
    //根据ID查询工单信息
    public WorkOrder selectById(int workOrderId)
    {
        return workOrderMapper.selectById(workOrderId);
    }
    //根据名称查询工单
    public List<WorkOrder> selectByName(String workOrderName)
    {
        return workOrderMapper.selectByName(workOrderName);
    }
    //查询某一客服关联的所有工单
    public List<WorkOrder> selectAgentOA(String agentOa)
    {
        return workOrderMapper.selectAgentOA(agentOa);
    }
    //查询某一客服关联的所有工单
    public List<WorkOrder> searchOrder(String agentOa, String workOrderStatus)
    {
        return workOrderMapper.searchOrder(agentOa, workOrderStatus);
    }


}
