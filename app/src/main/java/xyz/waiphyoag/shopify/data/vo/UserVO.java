package xyz.waiphyoag.shopify.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WaiPhyoAg on 9/2/19.
 */

public class UserVO {
    @SerializedName("userId")
    private String userId;
    @SerializedName("userName")
    private String userName;
    @SerializedName("productId")
    private String productId;
    @SerializedName("email")
    private String email;

    public UserVO(String userId, String userName, String productId, String email) {
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
