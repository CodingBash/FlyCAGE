
package edu.ilstu.biology.flytranscriptionwebapp.transferobject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("Generated by http://www.jsonschema2pojo.org/ using the json result from http://iodocs.apps.intermine.org/flymine/docs#/ws-query-results/POST/query/results using query from queries.properties")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "primaryIdentifier",
    "objectId",
    "class",
    "rnaSeqResults"
})
public class GeneRNAInformationResultTO {

    @JsonProperty("primaryIdentifier")
    private String primaryIdentifier;
    @JsonProperty("objectId")
    private Integer objectId;
    @JsonProperty("class")
    private String _class;
    @JsonProperty("rnaSeqResults")
    private List<GeneRNAInformationRnaSeqResultTO> rnaSeqResults = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("primaryIdentifier")
    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    @JsonProperty("primaryIdentifier")
    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    @JsonProperty("objectId")
    public Integer getObjectId() {
        return objectId;
    }

    @JsonProperty("objectId")
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonProperty("rnaSeqResults")
    public List<GeneRNAInformationRnaSeqResultTO> getRnaSeqResults() {
        return rnaSeqResults;
    }

    @JsonProperty("rnaSeqResults")
    public void setRnaSeqResults(List<GeneRNAInformationRnaSeqResultTO> rnaSeqResults) {
        this.rnaSeqResults = rnaSeqResults;
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
