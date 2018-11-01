package com.company.Tasks;

import java.util.Comparator;

public class SortByDSC implements Comparator {
    public int compare(Object o1, Object o2) {
        Task s1 = (Task) o1;
        Task s2 = (Task) o2;
        if (s1.getPriority() < s2.getPriority())
            return 1;
        else if (s1.getPriority() == s2.getPriority()) {
            return 0;
        }
        return -1;
    }
}
