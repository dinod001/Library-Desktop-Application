package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.AuthorDTO;
import com.CodesageLK.entity.Custom.Author;
import com.CodesageLK.repo.Custom.Impl.AuthorImpl;
import com.CodesageLK.service.Custom.AuthorService;
import com.CodesageLK.utill.exception.Custom.AuthorException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorImpl repo;
    public AuthorServiceImpl(AuthorImpl authers) {
        this.repo = authers;
    }

    @Override
    public boolean add(AuthorDTO authorDTO) throws AuthorException {
        try {
            return repo.add(convertDtoToEntity(authorDTO));
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException){
                System.out.println(((SQLException)e).getErrorCode());
                if (((SQLException)e).getErrorCode() == 1062){
                    throw new AuthorException("Duplicate Author ID found",e);
                }
                if (((SQLException)e).getErrorCode() == 1406){
                    String[] errors=((SQLException)e).getMessage().split("'");
                    throw new AuthorException("input data is too log in "+errors[1],e);
                }
            }
            throw new AuthorException("Error occurred - Contact Developer",e);
        }
    }

    @Override
    public boolean update(AuthorDTO authorDTO) throws AuthorException {
        try {
            return repo.update(convertDtoToEntity(authorDTO));
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException)e).getErrorCode() == 1406){
                String[] errors=((SQLException)e).getMessage().split("'");
                throw new AuthorException("input data is too log in "+errors[1],e);
            }
            throw new AuthorException("Error Ocurred - Contact Developer",e);
        }
    }

    @Override
    public boolean delete(Integer t) throws AuthorException {
        try {
           return repo.delete(t);
        } catch (SQLException | ClassNotFoundException e) {
            throw new AuthorException("Error occurred - Contact Developer - may be not implemented yet",e);
        }
    }

    @Override
    public Optional<AuthorDTO> get(Integer integer) throws AuthorException {
        try {
            Optional<Author> author = repo.get(integer);
            if (author.isPresent()){
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.get().getId());
                authorDTO.setName(author.get().getName());
                authorDTO.setContact(author.get().getContact());
                return Optional.of(authorDTO);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
        return Optional.empty();
    }

    @Override
    public List<AuthorDTO> getAll() throws AuthorException {
        try {
            List<Author> all = repo.getAll();
            List<AuthorDTO> authorDTOList = new ArrayList<>();
            if (all.isEmpty()){
                throw new AuthorException("No Author Found");
            }
            for (Author author : all){
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorDTO.setContact(author.getContact());
                authorDTOList.add(authorDTO);
            }
            return authorDTOList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new AuthorException("Error occurred - Contact Developer");
        }
    }

    private Author convertDtoToEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setContact(dto.getContact());
        return author;
    }

    private AuthorDTO convertEntityToDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setContact(author.getContact());
        return authorDTO;
    }
}
