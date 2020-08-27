package com.eric;

import java.util.List;

/**
 * @Author: Eric
 * @Date: 2020/8/27 8:50 下午
 * @title: 桥接模式
 * @Description: 通过组合的方式将不同纬度的抽象拼接到一起
 * 如下例子，告警规则是通过告警等级+告警方式组合而成的，比如waring级别的告警+微信通知，error级别的告警+邮件通知，
 * 通过桥接模式可以实现两者实现依赖于各自的抽象，并且自由组合，将来如果有warning级别的告警需要配合邮件通知，不需要改变任何代码
 */
interface MsgSender{
    void send(String message);
}

class TelephoneMsgSender implements MsgSender{

    private List<String> telephones;
    public TelephoneMsgSender(List<String> telephones){
        this.telephones = telephones;
    }
    @Override
    public void send(String message) {
        for (String tel : telephones){
            //send
        }
    }
}

abstract class Notification{
    protected MsgSender msgSender;

    public Notification(MsgSender msgSender){
        this.msgSender = msgSender;
    }

    public abstract void notify(String msg);
}

class SeverNotification extends Notification{

    public SeverNotification(MsgSender msgSender)
    {
        super(msgSender);
    }
    @Override
    public void notify(String msg) {

    }
}