package com.sp.apis.pdf_generator_api.config.dto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard error response format")
public record ErrorResponse(
    @Schema(example = "ERROR_400", description = "Machine-readable error code")
    String code,
    
    @Schema(example = "Invalid request parameter", description = "Human-readable message")
    String message
) {
    // Builder for convenience
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }
}