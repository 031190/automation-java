package com.testapi.pojo.petApiPojos;

import java.util.List;

public class Pet {
    private int id;
    private CategoryAndTag category;
    private String name;
    private List<String> photoUrls;
    private List<CategoryAndTag> tags;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryAndTag getCategory() {
        return category;
    }

    public void setCategory(CategoryAndTag category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<CategoryAndTag> getTags() {
        return tags;
    }

    public void setTags(List<CategoryAndTag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}
