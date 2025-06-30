package com.testapi.pojo;

public class RestApiGetEntryBody {
    private String id;
    private String name;
    private RestApiEntryDataObj data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestApiEntryDataObj getData() {
        return data;
    }

    public void setData(RestApiEntryDataObj dataObj) {
        this.data = dataObj;
    }

    @Override
    public String toString() {
        return "RestApiGetEntryBody{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
