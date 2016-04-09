package com.epam.courses.jf.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class SpecialJSPTag extends TagSupport {

    private JSPSetBean set;

    public void setSet(JSPSetBean set) {
        this.set = set;
    }

    @Override
    public int doStartTag() throws JspException {
        int size = set.getSize();

        try{
            JspWriter out = pageContext.getOut();

            out.write("Size = <b>(" + size + ")</b><table style=\"border: 1px solid #000\">");

            for(int i=0; i < size; i++)
                out.write("<tr><td>" + set.getElement() + "</td></tr>");

            out.write("</table>");

        }catch(IOException e){
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
