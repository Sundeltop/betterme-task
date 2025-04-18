package org.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Error(@JsonProperty("code") Integer code,
                    @JsonProperty("type") String type,
                    @JsonProperty("message") String message) {

    public static Error orderNotFoundError() {
        return new Error(1, "error", "Order not found");
    }

    public static Error orderNotFoundUnknown() {
        return new Error(404, "unknown", "Order Not Found");
    }
}
