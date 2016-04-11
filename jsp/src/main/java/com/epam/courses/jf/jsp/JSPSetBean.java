package com.epam.courses.jf.jsp;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class JSPSetBean {
    private Iterator<String> it;
    private Set<String> set;

    public JSPSetBean(Set<String> set){
        this.set = set;
    }

    public int getSize(){
        return set.size();
    }

    /**
     * @deprecated use {@link #elements()}
     */
    @Deprecated
    public String getElement() {
        if (it == null || !it.hasNext())
            it = set.iterator();

        return it.next();
    }

    public Stream<String> elements() {
        return set.stream();
    }
}
