package com.sanqing.tool;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends org.apache.struts.action.ActionServlet {
    protected void process(HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse) throws IOException,ServletException {
        setCharset(httpservletrequest);//���ñ���
        super.process(httpservletrequest, httpservletresponse);//���ø��ദ�?��
    }

    private void setCharset(HttpServletRequest httpservletrequest) {
        String s = getServletConfig().getInitParameter("charset");//��ó�ʼ������
        if (s == null || s.length() == 0) {//������Ϊ�գ�����Ϊnull
            s="utf-8";						//���ò���ֵΪutf-8
        }
        try {
            httpservletrequest.setCharacterEncoding(s);//������������ʽ
        } catch (UnsupportedEncodingException unsupportedencodingexception) {
            log("set character encoding error", unsupportedencodingexception);
        }
    }
}
