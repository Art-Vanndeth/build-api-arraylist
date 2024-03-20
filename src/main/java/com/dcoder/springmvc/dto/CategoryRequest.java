package com.dcoder.springmvc.dto;

import lombok.Builder;

@Builder
public record CategoryRequest(String title , String description) {
}
