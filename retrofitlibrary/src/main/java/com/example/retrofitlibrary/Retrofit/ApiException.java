package com.example.retrofitlibrary.Retrofit;


import com.example.retrofitlibrary.entity.resultbean.ResultBean;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ApiException extends RuntimeException {
    private int mErrorCode;
    private String mErrorMessage;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
        mErrorMessage=errorMessage;
    }

    public int getmErrorCode() {
        return mErrorCode;
    }

    public void setmErrorCode(int mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getmErrorMessage() {
        return mErrorMessage;
    }

    public void setmErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    /**
     * 判断是否抛出异常
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isExpried() {
        return mErrorCode == ResultBean.EXCEPTION_CODE;
    }
}
