package com.epam.courses.jf.jsp;

import java.util.Iterator;
import java.util.Set;

public class JSPSetBean {
    private Iterator<String> it;
    private Set<String> set;

    public JSPSetBean(Set<String> set){
        this.set = set;
    }

    public int getSize(){
        return set.size();
    }

    public String getElement() {
        if (it == null || !it.hasNext())
            it = set.iterator();

        return it.next();
    }


}
