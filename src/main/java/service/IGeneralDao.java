package service;

import java.util.List;

public interface IGeneralDao<T> {
    void insert(T t);
    T selectUserByID(String id);
    List<T> selectUserByName(String inputSearch);
    List<T> selectAllUser();

    boolean delete(String id);
    boolean update(T t);
    boolean  checkLogin(String username,String password);

}
