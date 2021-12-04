package com.trots.periodacals.util.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class JspCustomTag extends SimpleTagSupport{
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void doTag() throws IOException {
        String dollarText = text;
        JspWriter out = getJspContext().getOut();
        out.println(dollarText);
    }
}
