package de.codefor.le.locations.locator;

import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nominatim {

    @JsonProperty("place_id")
    private String placeId;
    private String[] boundingbox;
    private String lat;
    private String lon;
    private String type;

    private String licence;

    @JsonProperty("osm_type")
    private String osmType;

    @JsonProperty("osm_id")
    private Long osmId;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("class")
    private String clazz;

    private Double importance;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String[] getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(String[] boundingbox) {
        this.boundingbox = boundingbox;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nominatim nominatim)) return false;
        return Objects.equals(getPlaceId(), nominatim.getPlaceId()) && Arrays.equals(getBoundingbox(), nominatim.getBoundingbox()) && Objects.equals(getLat(), nominatim.getLat()) && Objects.equals(getLon(), nominatim.getLon()) && Objects.equals(getType(), nominatim.getType()) && Objects.equals(getLicence(), nominatim.getLicence()) && Objects.equals(getOsmType(), nominatim.getOsmType()) && Objects.equals(getOsmId(), nominatim.getOsmId()) && Objects.equals(getDisplayName(), nominatim.getDisplayName()) && Objects.equals(getClazz(), nominatim.getClazz()) && Objects.equals(getImportance(), nominatim.getImportance());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getPlaceId(), getLat(), getLon(), getType(), getLicence(), getOsmType(), getOsmId(), getDisplayName(), getClazz(), getImportance());
        result = 31 * result + Arrays.hashCode(getBoundingbox());
        return result;
    }

    @Override
    public String toString() {
        return "Nominatim{" +
                "placeId='" + placeId + '\'' +
                ", boundingbox=" + Arrays.toString(boundingbox) +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", type='" + type + '\'' +
                ", licence='" + licence + '\'' +
                ", osmType='" + osmType + '\'' +
                ", osmId=" + osmId +
                ", displayName='" + displayName + '\'' +
                ", clazz='" + clazz + '\'' +
                ", importance=" + importance +
                '}';
    }
}
