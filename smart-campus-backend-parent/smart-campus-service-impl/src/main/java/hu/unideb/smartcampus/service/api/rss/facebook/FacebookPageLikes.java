package hu.unideb.smartcampus.service.api.rss.facebook;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacebookPageLikes {

  private List<FacebookPage> data;

  private FacebookPaging paging;

}
