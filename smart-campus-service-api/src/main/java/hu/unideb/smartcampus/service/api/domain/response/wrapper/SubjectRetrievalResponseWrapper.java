package hu.unideb.smartcampus.service.api.domain.response.wrapper;

import java.util.List;

import hu.unideb.smartcampus.service.api.domain.response.wrapper.inner.SubjectWrapper;
import lombok.Builder;
import lombok.Data;

/**
 * Wrapper for result of Retrieve Subjects.
 *
 */
@Data
public class SubjectRetrievalResponseWrapper extends BaseWrapper {

  /**
   * Wrapped subjects in set.
   */
  private final List<SubjectWrapper> subjects;

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
