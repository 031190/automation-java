package com.testapi.pojo.petApiPojos;

public class CategoryAndTag {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryAndTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
