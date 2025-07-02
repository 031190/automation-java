package com.testapi.pojo;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class RestApiEntryDataObj {

    private String color;
    private Integer capacityGb;
    private String capacity;
    private String price;
    private String screenSize;
    private String capacity2;
    private String generation;
    private String generation2;
    public Map<String,Object> additionalProps = new HashMap<>();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("capacity GB")
    public Integer getCapacityGb() {
        return capacityGb;
    }

    @JsonProperty("capacity GB")
    public void setCapacityGb(Integer capacityGb) {
        this.capacityGb = capacityGb;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("Screen size")
    public String getScreenSize() {
        return screenSize;
    }

    @JsonProperty("Screen size")
    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    @JsonProperty("Capacity")
    public String getCapacity2() {
        return capacity2;
    }

    @JsonProperty("Capacity")
    public void setCapacity2(String capacity2) {
        this.capacity2 = capacity2;
    }

    @JsonAnySetter
    public void setAdditionalProps(String key, Object value) {
        this.additionalProps.put(key, value);
    }

    @JsonAnyGetter
    public Map<String,Object> getAdditionalProps(){
        return this.additionalProps;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @JsonProperty("Generation")
    public String getGeneration2() {
        return generation2;
    }

    @JsonProperty("Generation")
    public void setGeneration2(String generation2) {
        this.generation2 = generation2;
    }

    @Override
    public String toString() {
        return "{" +
                "colour='" + color + '\'' +
                ", capacity GB ='" + capacityGb + '\'' +
                '}';
    }
}
