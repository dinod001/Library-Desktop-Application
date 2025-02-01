package com.CodesageLK.service.Custom;

import com.CodesageLK.dto.Impl.SignUpDto;
import com.CodesageLK.service.SuperService;

public interface SignUpService extends SuperService {
    public boolean save(SignUpDto SignUpDto);
    public boolean update(String userName,String password);
    public int search(String username,String password);
    public boolean usernameExists(String username);
}
