package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.entity.Custom.SubCategory;
import com.CodesageLK.repo.Custom.SubCategoriesRepo;
import com.CodesageLK.utill.CrudUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SubCategoriesImpl implements SubCategoriesRepo {

    boolean flag = true;

    @Override
    public boolean saveList(List<SubCategory> list) throws SQLException, ClassNotFoundException {
        for (SubCategory subCategory : list) {
            if (flag) {
                add(subCategory);
            }
            else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(SubCategory subCategory) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO sub_categories (book_id, category_id) VALUES (?,?)";
        flag=CrudUtil.executeSql(sql, subCategory.getBookId(), subCategory.getCategoryId());
        return flag;
    }

    @Override
    public boolean update(SubCategory subCategory) throws SQLException, ClassNotFoundException {

        return false;
    }

    @Override
    public boolean delete(Integer t) throws SQLException, ClassNotFoundException {
        String sql="DELETE FROM sub_categories WHERE id=?";
        return CrudUtil.executeSql(sql, t);
    }

    @Override
    public Optional<SubCategory> get(Integer integer) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public List<SubCategory> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
