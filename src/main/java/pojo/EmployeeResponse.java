package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public class EmployeeResponse {
    @JsonProperty("status")
    String status;

    @JsonProperty("data")
    List<DataObj> dataObj;

    @JsonProperty("messages")
    int message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataObj> getDataObj() {
        return dataObj;
    }

    public void setDataObj(List<DataObj> dataObj) {
        this.dataObj = dataObj;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

}
