package com.epam.courses.jf.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static java.util.stream.Collectors.joining;

public class SpecialJSPTag extends TagSupport {

    private JSPSetBean set;

    public void setSet(JSPSetBean set) {
        this.set = set;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut()
                    .write("Size = <b>(" + set.getSize() + ")</b>"
                            + "<table style=\"border: 1px solid #000\"><tr><td>"
                            + set.elements().collect(joining("</td></tr><tr><td>"))
                            + "</td></tr></table>");
        } catch(IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
