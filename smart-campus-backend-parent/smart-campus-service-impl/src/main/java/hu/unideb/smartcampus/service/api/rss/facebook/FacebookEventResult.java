package hu.unideb.smartcampus.service.api.rss.facebook;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FacebookEventResult{

  private List<FacebookEvent> data;
 
  private FacebookPaging paging;
  
}
