package hu.unideb.smartcampus.shared.requestmessages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base message type, where the message types should been listed, otherwise the processing service
 * would not recognize it.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
    property = "messageType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ConsultingDatesRequest.class,
        name = "ConsultingDatesProcessMessage"),
    @JsonSubTypes.Type(value = RetrieveSubjectsRequest.class,
        name = "AskSubjectsProcessMessage"),
    @JsonSubTypes.Type(value = ExampleRequest.class, name = "ExampleProcessMessage"),})
public interface BaseRequestType {

}
