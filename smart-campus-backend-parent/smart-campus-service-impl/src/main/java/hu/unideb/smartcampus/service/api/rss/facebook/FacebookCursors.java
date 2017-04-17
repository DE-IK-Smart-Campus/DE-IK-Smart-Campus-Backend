package hu.unideb.smartcampus.service.api.rss.facebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacebookCursors {

  private String before;

  private String after;

}
