package com.epam.courses.jf.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class JSPTagWithBody extends BodyTagSupport {
    private int num;

    public void setNum(String num) {
        this.num = new Integer(num);
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(
                    "<table style=\"border: 3px solid #000; width: 100%\"><tr><td>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        if (num-- <= 1) return SKIP_BODY;
        try {
            pageContext.getOut().write("</td></tr><tr><td>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_AGAIN;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</td></tr></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
