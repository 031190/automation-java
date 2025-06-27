package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class RestApiEntryDataObj {

    @JsonProperty("color")
    private String color;

    @JsonProperty("capacity GB")
    private Integer capacityGb;

    @JsonProperty("Capacity")
    private String capacity;

    @JsonProperty("price")
    private String price;

    @JsonProperty("Screen size")
    private String screenSize;

//    @JsonProperty("color")
//    public String getColour() {
//        return color;
//    }
//
//    @JsonProperty("capacity GB")
//    public Integer getCapacityGb() {
//        return capacityGb;
//    }
//
//    @JsonProperty("Capacity")
//    public String getCapacity() {
//        return capacity;
//    }
//
//    @JsonProperty("price")
//    public String getPrice() {
//        return price;
//    }
//
//    @JsonProperty("Screen size")
//    public String getScreenSize() {
//        return screenSize;
//    }
//
//    @JsonProperty("color")
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    @JsonProperty("capacity GB")
//    public void setCapacityGb(Integer capacityGb) {
//        this.capacityGb = capacityGb;
//    }
//
//    @JsonProperty("Capacity")
//    public void setCapacity(String capacity) {
//        this.capacity = capacity;
//    }
//
//    @JsonProperty("price")
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    @JsonProperty("Screen size")
//    public void setScreenSize(String screenSize) {
//        this.screenSize = screenSize;
//    }

    @Override
    public String toString() {
        return "{" +
                "colour='" + color + '\'' +
                ", capacity GB ='" + capacityGb + '\'' +
                '}';
    }
}
