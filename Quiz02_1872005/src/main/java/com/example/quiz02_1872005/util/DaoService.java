package com.example.quiz02_1872005.util;

import java.util.List;
/**Michael Sebastian Gunadi-1872005*/
public interface DaoService<T> {

    int addData(T object);
    int deleteData(T object);
    int updateData(T object);
    List<T> fetchall();
}
