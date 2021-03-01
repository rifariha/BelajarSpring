package com.example.springmongo.springmongo.config;

import org.springframework.stereotype.Service;

@Service
public class Authentication {
    public boolean isSuperAdmin(Integer roleId) throws Exception {

        if(!roleId.equals(17))
        {
            throw new Exception("You are not allowed!");
        }
        return true;
    }

    public boolean isOperator(Integer roleId) throws Exception {

        if(!roleId.equals(18))
        {
            throw new Exception("You are not allowed!");
        }
        return true;
    }

    public boolean isReviewer(Integer roleId) throws Exception {

        if(!roleId.equals(19))
        {
            throw new Exception("You are not allowed!");
        }
        return true;
    }
}
