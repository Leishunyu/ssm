package com.ssm.utils;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

}