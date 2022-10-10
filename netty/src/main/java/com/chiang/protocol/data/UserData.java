package com.chiang.protocol.data;

import java.util.HashMap;

public class UserData {
    private static final HashMap<String,User> USER_MAP = new HashMap<>();

    static {
        User jiangHao = new User("JiangHao", "12345");
        User zhangSan = new User("ZhangSan", "12345");

        USER_MAP.put(jiangHao.getUserName(),jiangHao);
        USER_MAP.put(jiangHao.getUserName(),zhangSan);
    }

    /**
     * 验证密码是否正确
     *
     * @param userName 帐号
     * @param password 密码
     * @return 如果帐号密码不正确，返回false
     */
    public static boolean check(String userName, String password){
        User user = USER_MAP.get(userName);
        if(user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

}
