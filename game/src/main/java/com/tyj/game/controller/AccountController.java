package com.tyj.game.controller;

import com.tyj.game.bean.Account;
import com.tyj.game.dao.AccountDao;
import com.tyj.game.dao.ScoreDao;
import com.tyj.game.service.AccountService;
import com.tyj.game.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ScoreDao scoreDao;

    /**
     * 登入
     * @param account
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult login(@RequestBody Account account, HttpServletRequest req) {
        Account a = accountService.login(account);
        if(a!=null){
            return new JsonResult(true,a.getUid());
        }
        return new JsonResult(false,"ss");
    }

    /**
     * 注册
     * @param account
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JsonResult register(@RequestBody Account account) {
        int result = accountDao.register(account);
        if(result>0){
            return new JsonResult(true,"注册成功");
        }
        return new JsonResult(false,"注册失败");
    }

    /**
     * 退出
     * @param account
     * @return
     */
    @RequestMapping(value = "/loginout/",method = RequestMethod.POST)
    public ResponseEntity<Void> loginout(@RequestBody Account account) {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    /**
     * 判断用户名是否存在
     * @return
     */
    @RequestMapping("isRepeat/{name}")
    public JsonResult getAccount(@PathVariable("name") String name){
        Account account = accountDao.findByName(name);
        if (account==null){
            return new JsonResult(true,"该用户名可以使用");
        }
        return new JsonResult(false,"此用户名已经注册");
    }

    /**
     * 用户列表
     */
    @RequestMapping("users")
    public JsonResult users(){
        List<Account> list = accountDao.users();
        for(Account account:list){
            account.setCount(scoreDao.imgcount(account.getUid())+"");
        }
        return new JsonResult(true,list);
    }

    /**
     * 得分记录
     * @param req
     * @return
     */

    @RequestMapping("myScore")
    public JsonResult totalJifen(HttpServletRequest req){
        String str = req.getParameter("uid");
        int uid = Integer.parseInt(str);
        int count = scoreDao.getTotalScore(uid);
        return new JsonResult(true,count);
    }






}
