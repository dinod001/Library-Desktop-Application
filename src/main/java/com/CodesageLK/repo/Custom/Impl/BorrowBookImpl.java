package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.dto.Impl.BorrowBookDTO;
import com.CodesageLK.repo.Custom.BorrowBookRepo;
import com.CodesageLK.utill.CrudUtil;
import com.CodesageLK.utill.exception.SuperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookImpl implements BorrowBookRepo {
    @Override
    public boolean addBorrowBook(List<BorrowBookDTO> borrowBookDTOList) throws SuperException {
        for (BorrowBookDTO borrowBookDTO : borrowBookDTOList) {
            String sql = "insert into book_records(borrowed_date,isReturned,returnDate,returnedDate,book_id,member_id) values(?,?,?,?,?,?)";
            try {
                boolean result=CrudUtil.executeSql(sql,borrowBookDTO.getBorrowed_Date(),borrowBookDTO.isIs_returned(),borrowBookDTO.getReturn_Date(),borrowBookDTO.getReturned_Date(),borrowBookDTO.getBook_ID(),borrowBookDTO.getMember_Id());
                if (!result){
                    return false;
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new SuperException("Something went wrong - contact developer");
            }
        }
       return true;
    }

    @Override
    public List<BorrowBookDTO> getBorrowBookList() throws SuperException {
        String sql = "select * from book_records";
        List<BorrowBookDTO> borrowBookDTOList = new ArrayList<>();
        try {
            ResultSet rs=CrudUtil.executeSql(sql);
            while (rs.next()) {
                String bookName=null;
                String memberName=null;
                ResultSet rs1= CrudUtil.executeSql("select name from book where id=?",rs.getString("book_id"));
                if (rs1.next()){
                    bookName=rs1.getString("name");
                }
                ResultSet rs2=CrudUtil.executeSql("select name from member where id=?",rs.getString("member_id"));
                if (rs2.next()){
                    memberName=rs2.getString("name");
                }
                BorrowBookDTO borrowBookDTO = new BorrowBookDTO();
                borrowBookDTO.setBook_ID(rs.getInt("book_id"));
                borrowBookDTO.setBook_Name(bookName);
                borrowBookDTO.setMember_Name(memberName);
                borrowBookDTO.setBorrowed_Date(rs.getDate(2).toLocalDate());
                borrowBookDTO.setReturn_Date(rs.getDate(4).toLocalDate());
                borrowBookDTOList.add(borrowBookDTO);
            }
            return borrowBookDTOList;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SuperException("Something went wrong - contact developer");
        }
    }
}
