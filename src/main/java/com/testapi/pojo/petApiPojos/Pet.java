package com.testapi.pojo.petApiPojos;

import java.util.List;
import java.util.Objects;

public class Pet {
    private long id;
    private String name;
    private CategoryAndTag category;
    private List<String> photoUrls;
    private List<CategoryAndTag> tags;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(name, pet.name) && Objects.equals(category, pet.category) && Objects.equals(photoUrls, pet.photoUrls) && Objects.equals(tags, pet.tags) && Objects.equals(status, pet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, photoUrls, tags, status);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}
