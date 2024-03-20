package com.dcoder.springmvc.dto;

import lombok.Builder;

// for user input
@Builder
public record ProductRequest(  String title,
                               String description,
                               float price,
                               String imageUrl, int categoryId) {
}
