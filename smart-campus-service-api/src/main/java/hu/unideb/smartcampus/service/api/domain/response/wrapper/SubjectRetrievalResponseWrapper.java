package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Wrapper for result of Retrieve Subjects.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectRetrievalResponseWrapper extends BaseWrapper {

  /**
   * Wrapped subjects in set.
   */
  private List<SubjectWrapper> subjects;

  /**
   * Constructs a wrapper for response.
   */
  @Builder
  public SubjectRetrievalResponseWrapper(final String messageType,
      final List<SubjectWrapper> subjects) {
    super(messageType);
    this.subjects = subjects;
  }
}
