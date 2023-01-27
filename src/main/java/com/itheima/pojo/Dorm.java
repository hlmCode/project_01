package com.itheima.pojo;

public class Dorm {
    private int id;     //宿舍id
    private int lid;    //楼宇id
    private int uid;    //宿管id
    private String lname;   //楼宇编号
    private String lbrief;  //楼宇介绍
    private String lgender; //楼宇类型
    private String roomName;    //宿舍编号
    private String roomNumber;  //床位
    private String roomSpare;   //剩余床位
    private String uname;   //宿管名字
    private String uphone;  //宿管联系方式

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLbrief() {
        return lbrief;
    }

    public void setLbrief(String lbrief) {
        this.lbrief = lbrief;
    }

    public String getLgender() {
        return lgender;
    }

    public void setLgender(String lgender) {
        this.lgender = lgender;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomSpare() {
        return roomSpare;
    }

    public void setRoomSpare(String roomSpare) {
        this.roomSpare = roomSpare;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }
}
