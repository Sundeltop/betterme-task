package org.example.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record Order(@JsonProperty("id") long id,
                    @JsonProperty("petId") long petId,
                    @JsonProperty("quantity") int quantity,
                    @JsonProperty("shipDate") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") OffsetDateTime shipDate,
                    @JsonProperty("status") String status,
                    @JsonProperty("complete") boolean isCompleted) {
}
