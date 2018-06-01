package com.tyj.game.bean;

public class Label {
    private int id;
    private String image;
    private String content;
    private Account account;

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    private String init;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    private String correct;
    private String updatetime;

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", cdate='" + cdate + '\'' +
                ", status=" + status +
                ", uid=" + uid +
                ", updatetime='" + updatetime + '\'' +
                ", correct='" + correct + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private String cdate;
    private int status;
    private int uid;
}
