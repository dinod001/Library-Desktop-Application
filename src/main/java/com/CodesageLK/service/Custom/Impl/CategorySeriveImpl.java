package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.CategoryDTO;
import com.CodesageLK.entity.Custom.Category;
import com.CodesageLK.repo.Custom.CategoryRepo;
import com.CodesageLK.repo.Custom.Impl.CategoryImpl;
import com.CodesageLK.service.Custom.CategoryService;
import com.CodesageLK.utill.exception.Custom.CategoryException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategorySeriveImpl implements CategoryService {

    private CategoryRepo repo;
    public CategorySeriveImpl(CategoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public boolean add(CategoryDTO categoryDTO) throws CategoryException {
        try {
            return repo.add(this.convertDtoToEntity(categoryDTO));
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException){
                System.out.println(((SQLException)e).getErrorCode());
                if (((SQLException)e).getErrorCode() == 1062){
                    throw new CategoryException("Duplicate Category ID found",e);
                }
                if (((SQLException)e).getErrorCode() == 1406){
                    String[] errors=((SQLException)e).getMessage().split("'");
                    throw new CategoryException("input data is too log in "+errors[1],e);
                }
            }
            throw new CategoryException("Error occurred - Contact Developer",e);
        }
    }

    @Override
    public boolean update(CategoryDTO categoryDTO) throws CategoryException {
        try {
            return repo.update(this.convertDtoToEntity(categoryDTO));
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException)e).getErrorCode() == 1406){
                String[] errors=((SQLException)e).getMessage().split("'");
                throw new CategoryException("input data is too log in "+errors[1],e);
            }
            throw new CategoryException("Error Ocurred - Contact Developer",e);
        }
    }

    @Override
    public boolean delete(Integer t) throws CategoryException {
        try {
            return repo.delete(t);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CategoryException("Error occurred - Contact Developer",e);
        }
    }

    @Override
    public Optional<CategoryDTO> get(Integer integer) throws CategoryException {
        try {
            Optional<Category> category = repo.get(integer);
            if (category.isPresent()){
                CategoryDTO categoryDTO = new CategoryDTO();
                return Optional.of(this.convertEntityToDto(category.get()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CategoryDTO> getAll() throws CategoryException {
        try {
            List<Category> all = repo.getAll();
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            if (!all.isEmpty()){
                for (Category category : all){
                    categoryDTOList.add(this.convertEntityToDto(category));
                }
            }
            return categoryDTOList;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CategoryException("Error occurred - Contact Developer",e);
        }
    }

    private final Category convertDtoToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }
    private final CategoryDTO convertEntityToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
