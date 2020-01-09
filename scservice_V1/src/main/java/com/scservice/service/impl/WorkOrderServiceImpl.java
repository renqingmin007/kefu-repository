package com.scservice.service.impl;

import com.scservice.mapper.WorkOrderMapper;
import com.scservice.pojo.WorkOrder;
import com.scservice.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/30
 * @Description: com.scservice.service.impl
 * @version: 1.0
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;

    //工单录入
    @Override
    public void insertWorkOrder(WorkOrder workOrder)
    {
        workOrderMapper.insertWorkOrder(workOrder);
    }

    //删除工单
    @Override
    public void deleteWorkOrder(Integer workOrderId)
    {
        workOrderMapper.deleteWorkOrder(workOrderId);
    }

    //修改工单信息
    @Override
    public void modifyWorkOrder(WorkOrder workOrder)
    {
          workOrderMapper.modifyWorkOrder(workOrder);
    }

    //轮询派单
    @Override
    public void sendWorkOrder(WorkOrder workOrder)
    {
        workOrderMapper.sendWorkOrder(workOrder);
    }

    //转派工单
    @Override
    public void deliverWorkOrder(WorkOrder workOrder)
    {
        workOrderMapper.deliverWorkOrder(workOrder);

    }

    //查询所有工单信息
    @Override
    public List<WorkOrder> searchAllOrder()
    {
        return workOrderMapper.searchAllOrder();
    }

    //根据工单号查询工单信息
    @Override
    public List<WorkOrder> searchOrderByUid(String uid)
    {
        return workOrderMapper.searchOrderByUid(uid);
    }

    //根据工单联系电话查询工单
    @Override
    public List<WorkOrder> searchOrderByPhone(String workOrderPhone)
    {
        return workOrderMapper.searchOrderByPhone(workOrderPhone);
    }
    //根据id查询工单
    @Override
    public WorkOrder searchOrderById(Integer workOrderId)
    {
        return workOrderMapper.searchOrderById(workOrderId);
    }
    //查询某一客服关联的所有工单
    @Override
    public List<WorkOrder> selectAgentId(Long agentId)
    {
        return workOrderMapper.selectAgentId(agentId);
    }


    //查询待办的工单列表（做统计）
    @Override
    public List<WorkOrder> selectTodoList(){
        return workOrderMapper.selectTodoList();
    }

    //查询已经处理的工单列表（做统计）
    @Override
    public List<WorkOrder> selectDoList(){
        return workOrderMapper.selectDoList();
    }
    //图片上传
    @Override
    public void imageStorage(Long workOrderId,String image)
    {
        workOrderMapper.imageStorage(workOrderId,image);
    }
}
