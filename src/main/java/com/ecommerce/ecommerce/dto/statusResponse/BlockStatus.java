package com.ecommerce.ecommerce.dto.statusResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlockStatus {

    private String message;
    private boolean status;

}
