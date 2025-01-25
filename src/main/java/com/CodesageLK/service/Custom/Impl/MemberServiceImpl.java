package com.CodesageLK.service.Custom.Impl;

import com.CodesageLK.dto.Impl.MemberDTO;
import com.CodesageLK.entity.Custom.Member;
import com.CodesageLK.utill.exception.Custom.MemberCustomException;
import com.CodesageLK.repo.Custom.Impl.MemberRepoImpl;
import com.CodesageLK.service.Custom.MemberService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    /*//property dependency injection
    private MemberRepoImpl memberRepo=new MemberRepoImpl();

    //constructor dependency injection
    public MemberServiceImpl(MemberRepoImpl memberRepo) {
        this.memberRepo=memberRepo;

        //set dependency injection
    public void setMemberRepo(MemberRepoImpl memberRepo) {
        this.memberRepo = memberRepo;
    }
    }*/

    private final MemberRepoImpl memberRepo;

    //constructor dependency injection
    public MemberServiceImpl(MemberRepoImpl memberRepo) {
        this.memberRepo = memberRepo;
    }

    //add member
    public boolean add(MemberDTO member) throws MemberCustomException {
        Member entity=this.ConvertDtoToEntity(member);
        try {
            boolean isSaved=memberRepo.add(entity);
            return isSaved;
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException){
                System.out.println(((SQLException)e).getErrorCode());
                if (((SQLException)e).getErrorCode() == 1062){
                    throw new MemberCustomException("Duplicate Member ID found",e);
                }
                if (((SQLException)e).getErrorCode() == 1406){
                    String[] errors=((SQLException)e).getMessage().split("'");
                    throw new MemberCustomException("input data is too log in "+errors[1],e);
                }
            }
            throw new MemberCustomException("Error occurred - Contact Developer",e);
        }
    }

    //update member
    public boolean update(MemberDTO member) throws MemberCustomException {
        Member entity=this.ConvertDtoToEntity(member);
        boolean isUpdated=false;
        try {
            isUpdated=memberRepo.update(entity);
            return isUpdated;
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException)e).getErrorCode() == 1406){
                String[] errors=((SQLException)e).getMessage().split("'");
                throw new MemberCustomException("input data is too log in "+errors[1],e);
            }
           throw new MemberCustomException("Error Ocurred - Contact Developer",e);
        }
    }

    //delete member
    public boolean delete(String memberId) throws MemberCustomException {
        boolean isDeleted=false;
        try {
            isDeleted=memberRepo.delete(memberId);
            return isDeleted;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MemberCustomException("Something went wrong - contact Developer",e);
        }
    }

    //search member
    public Optional<MemberDTO> get(String memberId) {
        try {
            Optional<Member> member=memberRepo.get(memberId);
            if(member.isPresent()) {
                MemberDTO memberDto= ConvertEntityToDto(member.get());
                return Optional.of(memberDto);
            }
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //get all
    public List<MemberDTO> getAll() throws MemberCustomException {
        try {
            List<MemberDTO> list=new ArrayList<>();
            List<Member> members=memberRepo.getAll();
            if (members.isEmpty()) {
                throw new MemberCustomException("No Members currently exist !");
            }
            for(Member member:members) {
                MemberDTO memberDto= ConvertEntityToDto(member);
                list.add(memberDto);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MemberCustomException("Something went wrong - contact Developer");
        }
    }

    private Member ConvertDtoToEntity(MemberDTO member) {
        Member memberEntity = new Member();
        memberEntity.setId(member.getId());
        memberEntity.setName(member.getName());
        memberEntity.setEmail(member.getEmail());
        memberEntity.setAddress(member.getAddress());
        memberEntity.setContact(member.getContact());
        return memberEntity;
    }

    private MemberDTO ConvertEntityToDto(Member memberEntity) {
        MemberDTO memberDto = new MemberDTO();
        memberDto.setId(memberEntity.getId());
        memberDto.setName(memberEntity.getName());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setAddress(memberEntity.getAddress());
        memberDto.setContact(memberEntity.getContact());
        return memberDto;
    }
}
