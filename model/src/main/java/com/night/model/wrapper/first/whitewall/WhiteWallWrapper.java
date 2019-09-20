package com.night.model.wrapper.first.whitewall;

import java.io.Serializable;

public class WhiteWallWrapper implements Serializable {
    private String userLevel;
    private String userPetName;
    private String userComment;


    public WhiteWallWrapper(String userLevel, String userPetName, String userComment) {
        this.userLevel = userLevel;
        this.userPetName = userPetName;
        this.userComment = userComment;
    }

    public String getUserPetName() {
        return userPetName;
    }

    public void setUserPetName(String userPetName) {
        this.userPetName = userPetName;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}
