package pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class DataObj {

    @JsonProperty("id")
    int number;

    @JsonProperty("employee_name")
    String name;

    @JsonProperty("employee_salary")
    int salary;

    @JsonProperty("employee_age")
    int age;

    @JsonProperty("profile_image")
    String image;
}
