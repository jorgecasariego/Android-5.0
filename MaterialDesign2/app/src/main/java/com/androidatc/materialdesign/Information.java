package com.androidatc.materialdesign;

/**
 * Created by jorgecasariego on 6/7/15.
 */
public class Information {
    int iconId;
    String title;

    public Information(int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
