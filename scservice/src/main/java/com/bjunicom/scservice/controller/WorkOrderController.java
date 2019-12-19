package com.bjunicom.scservice.controller;


import com.bjunicom.scservice.pojo.WorkOrder;
import com.bjunicom.scservice.service.WorkOrderService;
import com.bjunicom.scservice.service.AdminService;


import com.bjunicom.scservice.utils.ResultModel;
import com.bjunicom.scservice.utils.ResultTools;
import org.apache.poi.hssf.usermodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.bjunicom.scservice.utils.CDateEditor;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class WorkOrderController {

    @Autowired

    private WorkOrderService workOrder;
    private AdminService adminService;

    //工单录入接口
    @RequestMapping(value = "WorkOrderSubmit", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel WorkOrderSubmit(String name, String wechatid, String phone, String lessee, String problem, MultipartFile file) throws IOException {
        if (name == null || name == "" || wechatid == null || wechatid == "") {
            return ResultTools.result(404, "基础信息缺失", null);
        }
        if (lessee == null || lessee == "" || phone == null || phone == "" || problem == null || problem == "") {
            return ResultTools.result(404, "问题描述信息不全", null);
        }
        WorkOrder wo = new WorkOrder();
        wo.setWechat_ID(wechatid);

        //在录入时需要调用图片上传接口，返回服务器上图片存储地址
        //因此参数列表处image应该替换成FILE
        String image = Upload(file);

        wo.setWork_order_image(image);
        wo.setWork_order_lessee(lessee);
        wo.setWork_order_phone(phone);
        wo.setWork_order_problem(problem);
        wo.setWork_order_name(name);
        try {
            workOrder.insertWorkOrder(wo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("insertmsg", name);
            return ResultTools.result(0, "", map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    //删除工单接口
    @RequestMapping(value = "/DeleteOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel DeleteOrder(Integer workorder_id) {
        try {
            workOrder.deleteWorkOrder(workorder_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deletemsg", workorder_id);
            return ResultTools.result(0, "", map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    //修改工单接口
    @RequestMapping(value = "ModifyOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel ModifyOrder(Integer workorder_id, String workorder_lessee, String workorder_phone,
                                   String workorder_image, String workorder_problem) {
        try {
            WorkOrder wo = workOrder.selectById(workorder_id);
            wo.setWork_order_phone(workorder_phone);
            wo.setWork_order_image(workorder_image);
            wo.setWork_order_problem(workorder_problem);
            wo.setWork_order_lessee(workorder_lessee);
            workOrder.modifyWorkOrder(wo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("modifymsg", workorder_id.toString());
            return ResultTools.result(0, "", map);

        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    //派单接口
    @RequestMapping(value = "SendOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel SendOrder(String agent_oa, Integer workorder_id, Timestamp workorder_start) {
        try {
            WorkOrder wo = workOrder.selectById(workorder_id);
            wo.setAgent_oa(agent_oa);
            wo.setWork_order_start(workorder_start);
            workOrder.sendWorkOrder(wo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sendordermsg", workorder_id);
            return ResultTools.result(0, "", map);

        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    //转派接口
    @RequestMapping(value = "DeliverOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel DeliverOrder(String agent_oa, Integer workorder_id, Timestamp workorder_start) {
        try {
            WorkOrder wo = workOrder.selectById(workorder_id);
            wo.setAgent_oa(agent_oa);
            wo.setWork_order_start(workorder_start);
            workOrder.deliverWorkOrder(wo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("delivermsg", workorder_id);
            return ResultTools.result(0, "", map);

        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    //查询工单接口
    @RequestMapping(value = "SearchOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public List<WorkOrder> SearchOrder(String agentOa, String workOrderStatus) {
        try {
            List<WorkOrder> wo_all = workOrder.searchOrder(agentOa, workOrderStatus);
            return wo_all;

        } catch (Exception e) {
            return null;

        }
    }

    //查询所有工单接口
    @RequestMapping(value = "SearchAll", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public List<WorkOrder> SearchAll() {
        try {
            List<WorkOrder> wo_all = workOrder.selectWorkOrder();
            return wo_all;
        } catch (Exception e) {
            return null;
        }
    }

    //查询某个客服所属工单接口
    @RequestMapping(value = "SearchByAgent", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public List<WorkOrder> SearchByAgent(String agent_oa) {
        try {
            List<WorkOrder> wo_agent = workOrder.selectAgentOA(agent_oa);
            return wo_agent;
        } catch (Exception e) {
            return null;
        }
    }

    //根据id查询工单接口
    @RequestMapping(value = "SearchById", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public WorkOrder SearchById(Integer workOrder_id) {
        try {

            WorkOrder wo = workOrder.selectById(workOrder_id);
            return wo;
        } catch (Exception e) {
            return null;
        }
    }

    //根据名称查询工单接口
    @RequestMapping(value = "SearchByName", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public List<WorkOrder> SearchByName(String name) {
        try {
            List<WorkOrder> wo_list = workOrder.selectByName(name);
            return wo_list;
        } catch (Exception e) {
            return null;
        }
    }

    //处理工单接口
    @RequestMapping(value = "ResolveOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel ResolveOrder(Integer workorder_id, Timestamp workorder_end) {
        WorkOrder wo = workOrder.selectById(workorder_id);
        String agent = wo.getAgent_oa();
        try {
            adminService.addCount(agent);
            wo.setWork_order_end(workorder_end);
            workOrder.endWorkOrder(wo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resolvemsg", workorder_id);
            return ResultTools.result(0, "", map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    //导出Excel接口
    @RequestMapping(value = "ExportWorkOrder", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel ExportWorkOrder(@RequestBody List<Integer> id_list, HttpServletResponse response, HttpSession session) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String filename = date;

        List<WorkOrder> wo_list = new ArrayList<>();
        for (int i = 0; i < id_list.size(); i++) {
            WorkOrder temp = workOrder.selectById(id_list.get(i));
            if (temp != null) {
                wo_list.add(temp);
            }

        }
        String[] header = {"序号","客服人员OA","用户微信ID","问题名称","所属租户名称","手机号","问题详细描述","图片","工单状态","开始时间","结束时间","转发时间","备注"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("工单表");

        sheet.setDefaultColumnWidth(20);

        HSSFRow head = sheet.createRow(0);

        for(int j = 0;j<header.length;j++)
        {
            HSSFCell cell = head.createCell(j);

            HSSFRichTextString text = new HSSFRichTextString(header[j]);

            cell.setCellValue(text);
        }

        int max_row = 13;
        for(int i = 0;i<wo_list.size();i++)
        {
            HSSFRow myrow = sheet.createRow(i+1);
            WorkOrder temp = wo_list.get(i);
            ArrayList wo_temp = temp.turnList();
            for(int k=0;k<max_row;k++)
            {
                if(k==7)
                {
                    //插入图片，待完成
                }
                else {
                    HSSFCell maincell = myrow.createCell(k);
                    HSSFRichTextString TEXT = new HSSFRichTextString(String.valueOf(wo_temp.get(k)));

                    maincell.setCellValue(TEXT);
                }
            }

        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename="+filename+".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exportmsg", "success");
        return ResultTools.result(0, "", map);
    }

    //图片上传接口
    @RequestMapping(value = "ImageUpload", method = {RequestMethod.POST,RequestMethod.GET}, produces = "application/json")
    public ResultModel ImageUpload(MultipartFile file,Integer workorder_id) throws IOException {
        String image = Upload(file);

        //将图片地址存入数据库
        WorkOrder wo = workOrder.selectById(workorder_id);
        wo.setWork_order_image(image);
        workOrder.imageStorage(wo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("imageuploadmsg", "success");
        return ResultTools.result(0, "", map);
    }

    public String Upload(MultipartFile file) throws IOException {
        //设置日期格式  HH:mm:ss
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        //获取项目resource文件夹路径
        ClassPathResource classPathResource = new ClassPathResource("resources");
        String path = classPathResource.getPath() + "/image/";
        String sourcename = file.getOriginalFilename();
        //        String sourcename = "D://pic/7e8b14382f20427fb7af9472f375f4fb.jpg";
        String extendName = sourcename.substring(sourcename.lastIndexOf("."), sourcename.length());
        String fileName =  date + extendName;

        //存储图片
        File dir = new File(path, fileName);
        File filepath = new File(path);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        file.transferTo(dir);

        //返回图片地址
        return path+fileName;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Timestamp.class, new CDateEditor(dateFormat, false));
    }
}
