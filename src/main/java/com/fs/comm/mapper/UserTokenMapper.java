package com.fs.comm.mapper;

import com.fs.comm.model.UserToken;

public interface UserTokenMapper {
	
    int insert(UserToken record);
    
    UserToken getTokenByUserID(int userId);
}