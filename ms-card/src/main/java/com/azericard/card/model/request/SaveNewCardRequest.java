package com.azericard.card.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveNewCardRequest {

  @NotBlank
  private String name;
  @NotBlank
  @Size(min = 16, max = 16)
  private String number;
  @Min(24)
  private int expireYear;
  @Min(1)
  @Max(12)
  private int expireMonth;
  @NotBlank
  @Size(min = 3, max = 3)
  private String cvv;
  @NotNull
  private BigDecimal balance;
}
