package com.bjunicom.scservice.controller;

import com.bjunicom.scservice.pojo.Admin;
import com.bjunicom.scservice.pojo.WorkOrder;
import com.bjunicom.scservice.service.AdminService;
import com.bjunicom.scservice.service.WorkOrderService;
import com.bjunicom.scservice.utils.ResultModel;
import com.bjunicom.scservice.utils.ResultTools;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkOrderController {


    @Autowired
    private WorkOrderService workOrder;
    private AdminService adminService;
    //工单录入接口
    @RequestMapping(value = "WorkOrderSubmit", method = RequestMethod.POST, produces = "application/json")
    public ResultModel WorkOrderSubmit(String name,String wechatid,String phone,String lessee,String problem,String image)
    {
        if(name==null||name==""||wechatid==null||wechatid==""){
            return ResultTools.result(404,"基础信息缺失",null);
        }
        if(lessee==null||lessee==""||phone==null||phone==""||problem==null||problem==""){
            return ResultTools.result(404,"问题描述信息不全",null);
        }
        WorkOrder wo = new WorkOrder();
        wo.setWechatId(wechatid);
        wo.setWorkOrderImage(image);
        wo.setWorkOrderLessee(lessee);
        wo.setWorkOrderPhone(phone);
        wo.setWorkOrderProblem(problem);
        wo.setWorkOrderName(name);
        try
        {
            workOrder.insertWorkOrder(wo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("insertmsg",name);
            return ResultTools.result(0, "",map);
        }
        catch (Exception e)
        {
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //删除工单接口
    @RequestMapping(value = "DeleteOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel DeleteOrder(Integer workorder_id)
    {
        try
        {
            workOrder.deleteWorkOrder(workorder_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deletemsg",workorder_id);
            return ResultTools.result(0, "",map);
        }
        catch (Exception e)
        {
            return ResultTools.result(404, e.getMessage(),null);
        }
    }

    //修改工单接口
    @RequestMapping(value = "ModifyOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ModifyOrder(Integer workorder_id,String workorder_lessee,String workorder_phone,
                                   String workorder_image,String workorder_problem)
    {
        try {
            WorkOrder wo = workOrder.selectById(workorder_id);
            workOrder.modifyWorkOrder(workorder_id, workorder_phone, workorder_lessee, workorder_problem, workorder_image);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("modifymsg", workorder_id.toString()+wo.getWorkOrderName());
            return ResultTools.result(0, "",map);

        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

    //派单接口
    @RequestMapping(value = "SendOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel SendOrder(String agent_oa, Integer workorder_id, Timestamp workorder_start)
    {
        try {
//            WorkOrder wo = workOrder.selectById(workorder_id);
//            wo.setAgentOa(agent_oa);
//            wo.setWorkOrderStart(workorder_start);
            workOrder.sendWorkOrder(workorder_id, agent_oa, workorder_start);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sendordermsg", workorder_id);
            return ResultTools.result(0, "",map);

        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

    //转派接口
    @RequestMapping(value = "DeliverOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel DeliverOrder(String agent_oa, Integer workorder_id, Timestamp workorder_start)
    {
        try{
            workOrder.deliverWorkOrder(workorder_id, agent_oa, workorder_start);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("delivermsg", workorder_id);
            return ResultTools.result(0, "",map);

        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

    //查询工单接口
    @RequestMapping(value = "SearchOrder", method = RequestMethod.GET, produces = "application/json")
    public List<WorkOrder> SearchOrder(String agentOa, String workOrderStatus)
    {
        try {
            List<WorkOrder> wo_all = workOrder.searchOrder(agentOa, workOrderStatus);
            return wo_all;

        }catch (Exception e) {
            return null;

        }
    }

    //查询所有工单接口
    @RequestMapping(value = "SearchAll", method = RequestMethod.GET, produces = "application/json")
    public List<WorkOrder> SearchAll(){
        try {
            List<WorkOrder> wo_all = workOrder.selectWorkOrder();
            return wo_all;
        }catch (Exception e) {
            return null;
        }
    }

    //查询某个客服所属工单接口
    @RequestMapping(value = "SearchByAgent", method = RequestMethod.GET, produces = "application/json")
    public List<WorkOrder> SearchByAgent(String agent_oa){
        try {
            List<WorkOrder> wo_agent = workOrder.selectAgentOA(agent_oa);
            return wo_agent;
        }catch (Exception e) {
            return null;
        }
    }

    //根据id查询工单接口
    @RequestMapping(value = "SearchById", method = RequestMethod.GET, produces = "application/json")
    public WorkOrder SearchById(Integer workOrder_id){
        try {
            WorkOrder wo = workOrder.selectById(workOrder_id);
            return wo;
        }catch (Exception e) {
            return null;
        }
    }

    //根据名称查询工单接口
    @RequestMapping(value = "SearchByName", method = RequestMethod.GET, produces = "application/json")
    public List<WorkOrder> SearchByName(String name){
        try {
            List<WorkOrder> wo_list = workOrder.selectByName(name);
            return wo_list;
        }catch (Exception e) {
            return null;
        }
    }

    //处理工单接口
    @RequestMapping(value = "ResolveOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ResolveOrder(Integer workorder_id, Timestamp workorder_end)
    {
        WorkOrder wo = workOrder.selectById(workorder_id);
        String agent = wo.getAgentOa();
        try {
            adminService.addCount(agent);
            workOrder.endWorkOrder(workorder_id, workorder_end);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resolvemsg",workorder_id);
            return ResultTools.result(0, "",map);
        }catch (Exception e){
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

    //导出Excel接口
    @RequestMapping(value = "ExportWorkOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ExportWorkOrder(List<Integer> id_list)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("insertmsg","admin");
        return ResultTools.result(0, "",map);
    }

    //图片上传接口
    @RequestMapping(value = "ImageUpload", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ImageUpload(String workorder_image)
    {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("insertmsg",workorder_image);
        return ResultTools.result(0, "",map);
    }


}
