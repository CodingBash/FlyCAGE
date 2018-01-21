
package edu.ilstu.biology.flytranscriptionwebapp.transferobject;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "executionTime",
    "wasSuccessful",
    "error",
    "statusCode"
})
public class GeneIDInformationCountTO {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("executionTime")
    private String executionTime;
    @JsonProperty("wasSuccessful")
    private Boolean wasSuccessful;
    @JsonProperty("error")
    private Object error;
    @JsonProperty("statusCode")
    private Integer statusCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("executionTime")
    public String getExecutionTime() {
        return executionTime;
    }

    @JsonProperty("executionTime")
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    @JsonProperty("wasSuccessful")
    public Boolean getWasSuccessful() {
        return wasSuccessful;
    }

    @JsonProperty("wasSuccessful")
    public void setWasSuccessful(Boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    @JsonProperty("error")
    public Object getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Object error) {
        this.error = error;
    }

    @JsonProperty("statusCode")
    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
