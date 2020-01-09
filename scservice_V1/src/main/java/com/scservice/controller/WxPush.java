package com.bjunicom.scservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WxPush {
    //传入appid和appscreat，返回推送入口accesstoken
    public String getaccesstoken(String appid,String appscreat)
    {
        String APPID = appid;
        String APPSCREAT=appscreat;
        String result = "";
        BufferedReader in = null;
        try {
            String connector ="&";
            String APPID = app_id; //用户微信uid
            String APPSCREAT = app_screat;//公众号生成

            //推送接口地址
            String urlNameString ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSCREAT;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("推送失败！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        JSONObject jsonObject = JSONObject.parseObject(result);
        result = jsonObject.getString("access_token");
        //返回
        return result;
    }
    //传入微信openid 和推送内容content，返回成功或失败
    public void sendmessage(String accesstoken,String openid,String content)
    {
        String access_token =accesstoken;
        String urls="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("touser", openid);
        jsonParam.put("info", content);access_token
        StringBuffer sb=new StringBuffer();
    		try {
                URL url = new URL(urls);
                 //建立http连接
                 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                 //设置允许输出
                 conn.setDoOutput(true);
                 //设置允许输入
                 conn.setDoInput(true);
                 //设置不用缓存
                 conn.setUseCaches(false);
                 //设置传递方式
                 conn.setRequestMethod("POST");
                 //设置维持长连接
                 conn.setRequestProperty("Connection", "Keep-Alive");
                 //设置文件字符集:
                 conn.setRequestProperty("Charset", "UTF-8");
                 //转换为字节数组
                 byte[] data = (jsonParam.toString()).getBytes();
                 //设置文件长度
                 conn.setRequestProperty("Content-Length", String.valueOf(data.length));
                 //设置文件类型:
                 conn.setRequestProperty("contentType", "application/json");
                 //开始连接请求
                 conn.connect();
                 OutputStream out = new DataOutputStream(conn.getOutputStream()) ;
                 //写入请求的字符串
                 out.write((jsonParam.toString()).getBytes());
                 out.flush();
                 out.close();
                 System.out.println(conn.getResponseCode());
                 //请求返回的状态
                 if (HttpURLConnection.HTTP_OK == conn.getResponseCode(){
                 System.out.println("连接成功");
                 //请求返回的数据
                 InputStream in1 = conn.getInputStream();
                 try {
                 String readLine=new String();
                 BufferedReader responseReader=new BufferedReader(new InputStreamReader(in1,"UTF-8"));
                 while((readLine=responseReader.readLine())!=null){
                 sb.append(readLine).append("\n");
                 }
                 responseReader.close();
                 System.out.println(sb.toString());
                 } catch (Exception e1) {
                 e1.printStackTrace();
                 }
                 } else {
                     System.out.println("error++");
                 }
    		} catch (Exception e) {
            }
    		return sb.toString();
    }
}
