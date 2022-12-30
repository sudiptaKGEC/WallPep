package com.sudiptahaldarkgec.wallpep;

import java.util.ArrayList;

public class SearchModel {
    private ArrayList<ImgModel> photos;

    public SearchModel(ArrayList<ImgModel> photos) {
        this.photos = photos;
    }

    public ArrayList<ImgModel> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<ImgModel> photos) {
        this.photos = photos;
    }
}
