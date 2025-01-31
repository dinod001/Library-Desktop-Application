package com.CodesageLK.repo.Custom.Impl;

import com.CodesageLK.dto.Impl.BorrowBookDTO;
import com.CodesageLK.utill.CrudUtil;
import com.CodesageLK.utill.exception.SuperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookImpl {
    public ArrayList<BorrowBookDTO> searchByBookID(int bookID) throws SuperException {
        String sql = "SELECT * FROM book_records WHERE book_id = "+bookID;
        ArrayList<BorrowBookDTO> search = search(sql);
        return search;
    }
    public ArrayList<BorrowBookDTO> searchByMemberID(String memberID) throws SuperException {
        String sql = "SELECT * FROM book_records WHERE member_id = '" + memberID + "'";
        ArrayList<BorrowBookDTO> search = search(sql);
        return search;
    }

    public ArrayList<BorrowBookDTO> searchByMemberContactNo(String memberContactNo) throws SuperException {
        String sql="SELECT id FROM member where contact=?;";
        try {
            ResultSet rs=CrudUtil.executeSql(sql,memberContactNo);
            if(rs.next()){
                // This is NOT recommended
                String sql2 = "SELECT * FROM book_records WHERE member_id='" + rs.getString("id") + "'";
                ArrayList<BorrowBookDTO> search = search(sql2);
                return search;
            }else{
                throw new SuperException("Member not found");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new SuperException("Something went wrong-contact developer");
        }
    }

    public ArrayList<BorrowBookDTO> search(String sql) throws SuperException {
        BorrowBookDTO borrowBookDTO = new BorrowBookDTO();
        ArrayList<BorrowBookDTO> borrowBookDTOs = new ArrayList<BorrowBookDTO>();
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
                borrowBookDTO.setId(rs.getInt("id"));
                borrowBookDTO.setBook_ID(rs.getInt("book_id"));
                borrowBookDTO.setBook_Name(bookName);
                borrowBookDTO.setMember_Name(memberName);
                borrowBookDTO.setMember_Id(rs.getString("member_id"));
                borrowBookDTO.setBorrowed_Date(rs.getDate(2).toLocalDate());
                borrowBookDTO.setReturn_Date(rs.getDate(4).toLocalDate());
                borrowBookDTOs.add(borrowBookDTO);
            }
            if (borrowBookDTOs.isEmpty()){
                return null;
            }
            return borrowBookDTOs;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SuperException("Something went wrong - contact developer");
        }
    }
}
