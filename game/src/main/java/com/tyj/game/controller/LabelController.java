package com.tyj.game.controller;

import com.tyj.game.bean.Account;
import com.tyj.game.bean.Label;
import com.tyj.game.bean.Score;
import com.tyj.game.dao.AccountDao;
import com.tyj.game.dao.LabelDao;
import com.tyj.game.dao.ScoreDao;
import com.tyj.game.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

@CrossOrigin("*")
@RestController
@RequestMapping("label")
public class LabelController {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ScoreDao scoreDao;

    @RequestMapping(value ="/add",method = RequestMethod.POST)
    @Transactional
    public JsonResult add(@RequestBody Label label, HttpServletRequest req){
        String str= req.getParameter("uid");
        int uid = Integer.parseInt(str);
        Account a =accountDao.findByUid(uid);
        if (a!=null&&a.getName().equals("admin")){
            int add = labelDao.add(label);
            if(add==0){
                return new JsonResult(false,"添加失败");
            }
            return new JsonResult(true,"添加成功");
        }
        return new JsonResult(false,"您无权限添加");
    }

    @RequestMapping(value = "select/{id}",method = RequestMethod.GET)
    public JsonResult find(@PathVariable("id") int label){
        return new JsonResult(true,label);
    }

    /**随机取题
     *
     * @param req
     * @return
     */
    //用户随机获取图片
    @RequestMapping("getRandom")
    public JsonResult getRandom(HttpServletRequest req){
        int count = labelDao.findTotal();
        if (count==0){
            return new JsonResult(false,"当前无图片，请管理员添加");
        }
        List<Label> list  = labelDao.findALl();
        count = list.size();
        double index = Math.random();
        int y = (int)Math.floor(index*count);
        Label l = list.get(y);
        String str= req.getParameter("uid");
        int uid = Integer.parseInt(str);
        Account account =accountDao.findByUid(uid);
        if(account!=null){
            l.setUid(account.getUid());
            l.setStatus(1);
            labelDao.update(l);
            return new JsonResult(true,l);
        }
        return new JsonResult(false,"用户异常");
    }
//用户提交，随机暴击得分
    @RequestMapping("save")
    @Transactional
    public JsonResult trueOrFalse(@RequestBody Label label){
        Label l = labelDao.find(label);
        l.setStatus(2);
        l.setContent(label.getContent());
        labelDao.update(l);
        int uid = l.getUid();
        Score score = new Score();
        score.setUid(uid);
        score.setYid(l.getId());
         int y = (int)Math.floor(Math.random()*100+1);
         int x = (int)Math.floor(Math.random()*100+1);
         if(y>95){
             if(x>=50){
                   score.setJifen(3);
             }else {
                 score.setJifen(2);
             }
         } else {
              score.setJifen(1);
         }
         scoreDao.addScore(score);
        if(l!=null){
            return new JsonResult(true,score);
        }
        return new JsonResult(false,score);
    }
    //管理员随机获取已完成图片
    @RequestMapping("admingetRandom")
    public JsonResult admingetRandom(HttpServletRequest req){
        String str = req.getParameter("uid");
        int uid = Integer.parseInt(str);
        int count = labelDao.adminfindTotal(uid);
        System.out.println(count);
        if (count==0){
            return new JsonResult(false,"该用户答的题都已经审核完毕");
        }
        List<Label> list  = labelDao.adminfindALl(uid);
       // String name=labelDao.user();
        count = list.size();
        double index = Math.random();
        int y = (int)Math.floor(index*count);
        Label l = list.get(y);//取得随机数


        Account account =accountDao.findByUid(uid);

        if(account!=null){
            l.setUid(account.getUid());
            l.setStatus(2);
            labelDao.adminupdate(l);
            return new JsonResult(true,l);
        }
        return new JsonResult(false,"用户异常");
    }
//管理员审核通过
    @RequestMapping("adminsave")
    @Transactional
    public JsonResult adminsava(@RequestBody Label label){
        Label l = labelDao.find(label);
        l.setStatus(3);
        l.setContent(label.getContent());
        labelDao.adminupdate(l);
        if(l!=null){
            return new JsonResult(true,"true");
        }
        return new JsonResult(false,"false");
    }
    //error打回操作
    @RequestMapping("error")
    @Transactional
    public JsonResult error(@RequestBody Label label){
        Label l = labelDao.find(label);
        l.setStatus(0);
        l.setContent(label.getContent());
        labelDao.adminupdate(l);
        if(l!=null){
            return new JsonResult(true,"true");
        }
        return new JsonResult(false,"false");
    }
    //用户完成数量
    @RequestMapping("count")
    public JsonResult count(HttpServletRequest req){
        String str= req.getParameter("uid");
        int uid = Integer.parseInt(str);
        Account account =accountDao.findByUid(uid);
        int count = labelDao.count(account.getUid());
        return new JsonResult(true,count);
    }
    //管理员审核数量
    @RequestMapping("admincount")
    public JsonResult admincount(HttpServletRequest req){
        String str= req.getParameter("uid");
        int uid = Integer.parseInt(str);
        Account account =accountDao.findByUid(uid);
        int count = labelDao.admincount(account.getUid());
        return new JsonResult(true,count);
    }




//正确答案
@RequestMapping("getcorrect")
    public JsonResult getCorrect(HttpServletRequest req){
        String str= req.getParameter("uid");
        int uid = Integer.parseInt(str);
        int correct = labelDao.getCorrect(uid);
        int count = labelDao.count(uid);
        double result = 100*correct/count;
        return new JsonResult(true,result);
    }
//   @RequestMapping("getscore")
//    public JsonResult getScore(){
//            Random random1=new Random();
//            int random=(int) (Math.random()*100+1);
//            int score=1;
//            if (random<5){
//                if(random1.nextBoolean()==true){
//                    score=2;
//                }else{
//                    score=3;
//                }
//            }
//            return new JsonResult(true,score);
//
//    }
//正确率埋题
           @RequestMapping("zhengquelv")
      public JsonResult zhengquelv(HttpServletRequest req){
        try {
            String str= req.getParameter("uid");
            int uid = Integer.parseInt(str);
            List<Label> list=labelDao.zhengquelv(uid);
            List<Label> labels = new ArrayList<>();
            int a = 0;
            for (Label label:list){
                if(label.getCorrect()!=null){
                    labels.add(label);
                }
            }
            for (Label l :labels){
                if(l.getContent().equals(l.getCorrect())){
                    a++;
                }
            }
            double result = a*100/labels.size();
            return new JsonResult(true,result);
        }catch (Exception e){
            return new JsonResult(false,0);
        }
      }

//一个人的所有题
@RequestMapping("ti")
      public JsonResult ti(HttpServletRequest req){
        String str= req.getParameter("uid");
        int uid = Integer.parseInt(str);
        List<Label> list=labelDao.zhengquelv(uid);
        return new JsonResult(true,list);
      }
//一个人的具体题
@RequestMapping("jutiti")
      public JsonResult jutiti(HttpServletRequest req){
        String str = req.getParameter("id");
        int id = Integer.parseInt(str);
          Label label = labelDao.findById(id);
          Account a  =accountDao.findByUid(label.getUid());
          label.setAccount(a);
          return new JsonResult(true,label);
      }



     //管理员审核正确率
     @RequestMapping("accuracy")
    public  JsonResult accuracy(HttpServletRequest req){
        try {
            String str= req.getParameter("uid");
            int uid = Integer.parseInt(str);
            int count0=labelDao.count0(uid);
            int count3=labelDao.admincount(uid);
            double percent=(count3*100)/(count0+count3);
            return new JsonResult(true,percent);
        }catch (Exception e){
            return new JsonResult(false,0);
        }
    }
//    @RequestMapping("countTotal")
//    public JsonResult countTotal(HttpServletRequest req){
//        int countTotal=labelDao.countTotal();
//        return new JsonResult(true,countTotal);
//    }
//    @RequestMapping("count123")
//    public JsonResult count123(HttpServletRequest req){
//        int count123=labelDao.count123();
//        return  new JsonResult(true,count123);
//    }
    @RequestMapping("wancheng")
    public JsonResult wancheng(HttpServletRequest req){
        int count123=labelDao.count123();
        int countTotal=labelDao.countTotal();
        int wancheng=count123*100/countTotal;
        return new JsonResult(true,wancheng);
    }






}
