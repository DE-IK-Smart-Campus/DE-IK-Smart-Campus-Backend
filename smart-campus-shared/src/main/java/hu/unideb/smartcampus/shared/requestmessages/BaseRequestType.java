package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Base message type, where the message types should been listed, otherwise the processing service
 * would not recognize it.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
    property = RequestMessagesConstants.MESSAGE_TYPE)
@JsonSubTypes({
    @JsonSubTypes.Type(value = RetrieveSubjectsRequest.class,
        name = RequestMessagesConstants.RETRIEVE_SUBJECTS_REQUEST),
    @JsonSubTypes.Type(value = ExampleRequest.class,
        name = RequestMessagesConstants.EXAMPLE_REQUEST),
    @JsonSubTypes.Type(value = RetrieveInstructorConsultingHours.class,
        name = RequestMessagesConstants.RETRIEVE_CONSULTING_HOURS)})
public interface BaseRequestType {

}
