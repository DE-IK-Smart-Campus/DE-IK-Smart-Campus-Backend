package hu.unideb.smartcampus.service.api.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookTokenCheck {

  private FacebookTokenCheckData data;
  
}
