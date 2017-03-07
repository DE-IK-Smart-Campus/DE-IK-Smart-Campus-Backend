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
    property = "messageType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RetrieveSubjectsRequest.class,
        name = RequestMessagesConstants.RETRIEVE_SUBJECTS_REQUEST),
    @JsonSubTypes.Type(value = ExampleRequest.class, name = "ExampleProcessMessage"),
    @JsonSubTypes.Type(value = RetrieveInstructorConsultingHours.class,
        name = RequestMessagesConstants.RETRIEVE_CONSULTING_HOURS)})
public interface BaseRequestType {

}
