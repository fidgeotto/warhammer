package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author David
 */
public class Faction extends ResourceSupport {

    private final String factionId;
    private final String name;

    @JsonCreator
    public Faction(@JsonProperty("factionId") String factionId, @JsonProperty("name") String name) {
        this.factionId = factionId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFactionId() {
        return factionId;
    }
}
