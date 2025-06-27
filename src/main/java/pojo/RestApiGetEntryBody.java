package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestApiGetEntryBody {
    @JsonIgnoreProperties("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private RestApiEntryDataObj dataObj;

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

    public RestApiEntryDataObj getDataObj() {
        return dataObj;
    }

    public void setDataObj(RestApiEntryDataObj dataObj) {
        this.dataObj = dataObj;
    }

    @Override
    public String toString() {
        return "RestApiGetEntryBody{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data=" + dataObj.toString() +
                '}';
    }
}
