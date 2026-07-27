package com.godl0ki.groupsortedlist;

public interface GroupView<E> {
    void put(E obj);
     E get(int index);
    int size();
    String getName();
}
