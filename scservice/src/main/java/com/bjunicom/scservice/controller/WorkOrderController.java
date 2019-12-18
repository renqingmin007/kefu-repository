package com.bjunicom.scservice.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.bjunicom.scservice.pojo.WorkOrder;
import com.bjunicom.scservice.service.WorkOrderService;
import com.bjunicom.scservice.utils.Exportutils;


import com.bjunicom.scservice.utils.ResultModel;
import com.bjunicom.scservice.utils.ResultTools;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class WorkOrderController {

    @Autowired

    private WorkOrderService workOrder;
    //工单录入接口
    @RequestMapping(value = "WorkOrderSubmit", method = RequestMethod.POST, produces = "application/json")
    public ResultModel WorkOrderSubmit(@RequestParam String name, @RequestParam String wechatid, @RequestParam String phone, @RequestParam String lessee, @RequestParam String problem, @RequestParam String image)
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
    @RequestMapping(value = "/DeleteOrder", method = RequestMethod.POST, produces = "application/json")
    public ResultModel DeleteOrder(@RequestParam Integer workorder_id)
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
    public ResultModel ModifyOrder(@RequestParam Integer workorder_id,@RequestParam String workorder_lessee,@RequestParam String workorder_phone,
                                   @RequestParam String workorder_image,@RequestParam String workorder_problem)
    {
        try {
            //List<WorkOrder> wo = workOrder.selectById(workorder_id);
            workOrder.modifyWorkOrder(workorder_id, workorder_phone, workorder_lessee, workorder_problem, workorder_image);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("modifymsg", workorder_id.toString());
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
            //adminService.addCount(agent);
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
    public ResultModel ExportWorkOrder(List<Integer> id_list, HttpServletResponse response, HttpSession session) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        String filepath = classPathResource.getPath()+"/excel/";
        File filep = new File(filepath);
        if(!filep.exists())
        {
            filep.mkdirs();
        }

        List<WorkOrder> wo_list = new ArrayList<>();
        for(int i = 0;i<id_list.size();i++)
        {
            WorkOrder temp = workOrder.selectById(i);
            if(temp!=null)
            {
                wo_list.add(temp);
            }

        }

        TemplateExportParams params = new TemplateExportParams();
        // 标题开始行
        params.setHeadingStartRow(0);
        // 标题行数
        params.setHeadingRows(1);
        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setSheetName("工单列表");

        // 获取报表内容
        // 因为表数据是根据存储过程来实现的，不同的报表有不同的配置，
        // 所以使用Map<String,Object>格式来接收
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", wo_list);
        // 获取模板文件路径
        // 这里有个很坑的地方，就是easypoi的API只能接收文件路径，无法读取文件
        // 设置模板路径
        params.setTemplateUrl(filepath);
        // 获取workbook
        Workbook workbook = ExcelExportUtil.exportExcel(params, data);
        // exportFileName代表导出的文件名称
        Exportutils.export(response, workbook, date+".xls");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exportmsg","success");
        return ResultTools.result(0, "",map);
    }

    //图片上传接口
    @RequestMapping(value = "ImageUpload", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ImageUpload(MultipartFile file, Integer workorder_id) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //获取项目resource文件夹路径
        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        String path = classPathResource.getPath()+"/image/";
        String sourcename = file.getOriginalFilename();
        String extendName = sourcename.substring(sourcename.lastIndexOf("."), sourcename.length());
        String fileName =  workorder_id + date+ extendName;

        //存储图片
        File dir = new File(path, fileName);
        File filepath = new File(path);
        if(!filepath.exists())
        {
            filepath.mkdirs();
        }
        file.transferTo(dir);

        //将图片地址存入数据库
        workOrder.imageStorage(workorder_id, path+fileName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("insertmsg",fileName+"已保存");

        return ResultTools.result(0, "",map);
    }

}
