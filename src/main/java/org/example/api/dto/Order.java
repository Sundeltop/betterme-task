package org.example.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.OffsetDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.time.OffsetDateTime.now;

@Builder
@JsonInclude(NON_NULL)
public record Order(@JsonProperty("id") Long id,
                    @JsonProperty("petId") Long petId,
                    @JsonProperty("quantity") Integer quantity,
                    @JsonProperty("shipDate") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") OffsetDateTime shipDate,
                    @JsonProperty("status") String status,
                    @JsonProperty("complete") Boolean isCompleted) {

    public static Order orderWithId(Long orderId) {
        return new Order(orderId, 0L, 0, now(), "placed", true);
    }
}
