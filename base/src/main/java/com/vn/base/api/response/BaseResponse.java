package com.vn.base.api.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @Expose
    @SerializedName("status")
    public int status;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("description")
    public String message;

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    /**
     * check api response
     * @return true if api response success other false
     */
    public boolean isSuccess() {
        return status == 1;
    }
}
