package netty;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public interface Command {
    Byte LOGIN_REQUEST=1; //登录
    Byte LOGIN_RESPONSE=2; //登录
    Byte MESSAGE_REQUEST =3;
    Byte MESSAGE_RESPONSE=4;

    Byte CREATEGROUP_REQUEST=5;
    Byte CREATEGROUP_RESPONSE=6;

    Byte JOINGROUP_REQUEST=7;
    Byte JOINGROUP_RESPONSE=8;

    Byte QUITGROUP_REQUEST = 9;
    Byte QUITGROUP_RESPONSE = 10;

    Byte LISTGROUPMEMBERS_REQUEST = 11;
    Byte LISTGROUPMEMBERS_RESPONSE = 12;

    Byte GROUPMESSAGE_REQUEST = 13;
    Byte GROUPMESSAGE_RESPONSE = 14;
}
