package com.tyj.game.dao;

import com.tyj.game.bean.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {

    Account login(Account account);

    int register(Account account);

    Account select(Account account);

    Account findByName(String name);

    Account findByUid(int id);

    List<Account> users();
}
