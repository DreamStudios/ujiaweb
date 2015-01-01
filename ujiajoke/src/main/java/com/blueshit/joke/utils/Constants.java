package com.blueshit.joke.utils;

/**
 * 项目常量工具类
 *
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 202014/11/8 13:33
 * @description
 */
public class Constants {
    //基本常量
    public static class Common {
        public static final String URLENC    = "UTF-8";
        //激活
        public static final String ACTIVE    = "active_mail";
        public static final String RESET_PWD = "reset_password_mail";

        public static final int PAGE_SIZE = 15;
    }

    public static class DatePartten {
        public static final String LINK_DATE = "yyyy-MM-dd";
        public static final String CN_DATE   = "yyyy年MM月dd日";

        public static final String LINK_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 通过经验值获取等级
     * @param experience
     * @return
     */
    public static String getLevel(int experience){
        String level;
        if(experience>=6500 && experience<23000){
            level = "一般员工";
        }else if(experience>=23000 && experience<49500){
            level = "优秀员工";
        }else if(experience>=49500 && experience<86000){
            level = "领班";
        }else if(experience>=86000 && experience<132500){
            level = "主管";
        }else if(experience>=132500 && experience<257420){
            level = "副总经理";
        }else if(experience>=257420 && experience<451210){
            level = "总经理";
        }else if(experience>=451210 && experience<681225){
            level = "副总裁";
        }else if(experience>=681225 && experience<1734250){
            level = "总裁";
        }else if(experience>=1734250 && experience<8888888){
            level = "执行懂事";
        }else if(experience>=8888888){
            level = "董事长";
        }else {
            level = "实习生";
        }
        return level;
    }
}
