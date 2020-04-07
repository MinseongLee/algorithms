package samsungSW.d4.justRule.returnToRoom;

public class Student {
    //필드는 최대한 안보이게 하는 것이 좋다.
    private int nowRoom;
    private int myRoom;
    private int includePath;
    public Student(){}
    public Student(int nowRoom,int myRoom,int includePath){
        this.nowRoom = nowRoom;
        this.myRoom = myRoom;
        this.includePath = includePath;
    }
    public void addPath(){
        includePath++;
    }
    public int getNowRoom(){
        return this.nowRoom;
    }
    public int getMyRoom(){
        return this.myRoom;
    }
    public int getIncludePath(){
        return this.includePath;
    }
}
