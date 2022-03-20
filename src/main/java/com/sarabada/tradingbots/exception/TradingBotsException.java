package com.sarabada.tradingbots.exception;

import com.sarabada.tradingbots.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradingBotsException extends RuntimeException {
    private List<ErrorDTO> errors;
    private HttpStatus status;
}