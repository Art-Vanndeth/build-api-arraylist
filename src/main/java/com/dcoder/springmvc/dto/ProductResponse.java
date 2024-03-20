package com.dcoder.springmvc.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

//return value back to user
@Builder
public record ProductResponse (int id , String title , String description, float price, String imageUrl,@JsonProperty("category") CategoryResponse categoryResponse) {
}
