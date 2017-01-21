package com.cavalry.androidlib.sample.constants.user;

import com.cavalry.androidlib.sample.bean.UserBean;

/**
 * 用户的基本信息.
 * <p>1. 以{@link #mUserBean}是否为null为依据判断是否登录</p>
 *
 * @author Cavalry Lin
 * @since 1.0.0
 */

public class UserInfo {
    private volatile static UserInfo mUserInfo;

    private UserBean mUserBean;

    private UserState mUserState;


    private UserInfo(){
        setSignState(isSigned());
    }

    public static UserInfo getInstance(){
        if(mUserInfo==null){
            synchronized (UserInfo.class){
                if (mUserInfo==null){
                    mUserInfo = new UserInfo();
                }
            }
        }
        return mUserInfo;
    }

    public boolean isSigned(){
        if(mUserBean == null){
            mUserBean = getUserBeanFromLocal();
        }

        return mUserBean != null;
    }

    /**
     *
     * @param signed    是否登录
     * @param userBean  若signed=true, userBean不能为空. 若signed=false, u
     */
    public void setSigned(boolean signed,UserBean userBean){
        if(signed && userBean == null)
            throw new RuntimeException("signed=true时, userBean不能为空");

        if(signed){
            mUserBean = userBean;
            saveUserBean2Local(userBean);
        }else{
            mUserBean = null;
            clearUserBeanFromLocal();
        }

        setSignState(signed);
    }

    private void clearUserBeanFromLocal() {
        System.out.println("将UserBean从本地存储中删除");
    }


    private UserBean getUserBeanFromLocal(){
        //实际应该从SP中读取
        return new UserBean();
    }

    private void saveUserBean2Local(UserBean userBean) {
        System.out.println("将UserBean保存在本地");
    }

    private void setSignState(boolean signed){
        mUserState =  signed ? new SignedState() : new UnsignState();
    }
}
