package com.learn.maksymgromov.learnui.Model;

import java.util.List;

public interface Repository<T> {

    void add(T item);
    void remove(T item);
    void update(T item, T newItem);

    List<T> getAllItems();
}
