package com.trots.periodacals.util.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;


public class JspCustomTag extends SimpleTagSupport{
    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }
    StringWriter sw = new StringWriter();
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println( message );

    }
}
