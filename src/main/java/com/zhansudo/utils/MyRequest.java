package com.zhansudo.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class MyRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    private boolean hasEncode;

    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    // 对需要增强的方法进行覆盖
    @Override
    public Map<String, String[]> getParameterMap() {
        // 获取请求方式
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post")) {
            // post请求
            try {
                // 处理post乱码
                request.setCharacterEncoding("UTF-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (method.equalsIgnoreCase("get")) {
            // get请求
            Map<String, String[]> parameterMap = request.getParameterMap();
            // 确保get手动编码逻辑只运行一次
            if (!hasEncode) {
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            try {
                                values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode = true;
            }
            return parameterMap;
        }
        return super.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        // 取回参数的第一个值
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }
}
