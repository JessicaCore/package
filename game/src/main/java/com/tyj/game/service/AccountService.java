package com.tyj.game.service;

import com.tyj.game.bean.Account;
import com.tyj.game.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;


    public Account login(Account account){
        Account a = accountDao.select(account);
        if(a==null){
            return null;
        }
        return a;

    }

    public int insert(Account account){

        if( accountDao.register(account)>0 ){
            return 1;
        }
        return 0;//失败

    }
}
