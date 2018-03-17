package example.mmigmur.data.entities.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable{

    @SerializedName("data")
    T data;
    @SerializedName("status")
    String status;
    @SerializedName("success")
    boolean success;

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public String getStatus(){
        return status;
    }

    public boolean isSuccess() {
        return success;
    }

}
