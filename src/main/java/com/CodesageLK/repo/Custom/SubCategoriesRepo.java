package com.CodesageLK.repo.Custom;

import com.CodesageLK.entity.Custom.SubCategory;
import com.CodesageLK.repo.CrudRepository;
import com.CodesageLK.service.CrudService;

import java.sql.SQLException;
import java.util.List;

public interface SubCategoriesRepo extends CrudRepository<SubCategory,Integer> {
    public boolean saveList(List<SubCategory> list) throws SQLException, ClassNotFoundException;
    public List<Integer> seacrh(int bookId) throws SQLException, ClassNotFoundException;
}
