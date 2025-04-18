package org.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Error(@JsonProperty("code") Integer code,
                    @JsonProperty("type") String type,
                    @JsonProperty("message") String message) {

    public static Error orderNotFound() {
        return new Error(1, "error", "Order not found");
    }
}
