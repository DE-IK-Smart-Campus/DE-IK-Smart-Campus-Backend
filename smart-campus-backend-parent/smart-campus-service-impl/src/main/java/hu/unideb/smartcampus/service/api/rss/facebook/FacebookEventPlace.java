package hu.unideb.smartcampus.service.api.rss.facebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacebookEventPlace {

  private String name;
  
  private String id;
  
  private FaceBookEventLocation location;
  
}
