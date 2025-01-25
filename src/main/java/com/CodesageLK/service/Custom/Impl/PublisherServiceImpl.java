package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.PublisherDTO;
import com.CodesageLK.entity.Custom.Publisher;
import com.CodesageLK.utill.exception.Custom.PublisherException;
import com.CodesageLK.repo.Custom.Impl.PublisherImpl;
import com.CodesageLK.repo.Custom.PublisherRepo;
import com.CodesageLK.service.Custom.PublisherService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepo repo;
    public PublisherServiceImpl(PublisherImpl repo) {
        this.repo = repo;
    }
    @Override
    public boolean add(PublisherDTO publisherDTO) throws PublisherException {
        Publisher publisher=this.convertDtoToEntity(publisherDTO);
        try {
            return repo.add(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (e instanceof SQLException){
                System.out.println(((SQLException)e).getErrorCode());
                if (((SQLException)e).getErrorCode() == 1062){
                    throw new PublisherException("Duplicate Publisher ID found",e);
                }
                if (((SQLException)e).getErrorCode() == 1406){
                    String[] errors=((SQLException)e).getMessage().split("'");
                    throw new PublisherException("input data is too log in "+errors[1],e);
                }
            }
            throw new PublisherException("Error occurred - Contact Developer",e);
        }
    }

    @Override
    public boolean update(PublisherDTO publisherDTO) throws PublisherException {
        Publisher publisher=this.convertDtoToEntity(publisherDTO);
        try {
            return repo.update(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (((SQLException)e).getErrorCode() == 1406){
                String[] errors=((SQLException)e).getMessage().split("'");
                throw new PublisherException("input data is too log in "+errors[1],e);
            }
            throw new PublisherException("Error Ocurred - Contact Developer",e);
        }
    }

    @Override
    public boolean delete(Integer t) throws PublisherException {
        try {
            return repo.delete(t);
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Not implemented yet - Contact Developer");
        }
    }

    @Override
    public Optional<PublisherDTO> get(Integer integer) throws PublisherException {
        try {
            Optional<Publisher>publisher=repo.get(integer);
            if(publisher.isPresent()){
                PublisherDTO publisherDTO=this.convertEntityToDto(publisher.get());
                return Optional.of(publisherDTO);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Something went wrong - Contact Developer",e);
        }
        return Optional.empty();
    }

    @Override
    public List<PublisherDTO> getAll() throws PublisherException {
        try {
            List<PublisherDTO> publisherDTOS=new ArrayList<PublisherDTO>();
            List<Publisher> publishers=repo.getAll();
            if(publishers.isEmpty()){
                throw new PublisherException("No publishers found");
            }
            else{
                for(Publisher publisher:publishers){
                    PublisherDTO publisherDTO=this.convertEntityToDto(publisher);
                    publisherDTOS.add(publisherDTO);
                }
            }
            return publisherDTOS;
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Something went wrong - Contact Developer");
        }
    }

    private Publisher convertDtoToEntity(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setId(publisherDTO.getId());
        publisher.setContact(publisherDTO.getContact());
        publisher.setLocation(publisherDTO.getLocation());
        return publisher;
    }

    private PublisherDTO convertEntityToDto(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setContact(publisher.getContact());
        publisherDTO.setLocation(publisher.getLocation());
        return publisherDTO;
    }
}
