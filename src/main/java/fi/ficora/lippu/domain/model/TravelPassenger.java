package fi.ficora.lippu.domain.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fi.ficora.lippu.domain.model.AccessibilityBase;
import fi.ficora.lippu.domain.model.ExtraServiceBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TravelPassenger
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-22T16:56:34.734+02:00")

public class TravelPassenger   {
  @JsonProperty("category")
  private String category = null;

  @JsonProperty("extraServices")
  @Valid
  private List<ExtraServiceBase> extraServices = null;

  @JsonProperty("accessibility")
  @Valid
  private List<AccessibilityBase> accessibility = null;

  public TravelPassenger category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public TravelPassenger extraServices(List<ExtraServiceBase> extraServices) {
    this.extraServices = extraServices;
    return this;
  }

  public TravelPassenger addExtraServicesItem(ExtraServiceBase extraServicesItem) {
    if (this.extraServices == null) {
      this.extraServices = new ArrayList<>();
    }
    this.extraServices.add(extraServicesItem);
    return this;
  }

   /**
   * List of required extra services for the travel. Identifer for the extra service the title of extra service.
   * @return extraServices
  **/
  @ApiModelProperty(value = "List of required extra services for the travel. Identifer for the extra service the title of extra service.")

  @Valid

  public List<ExtraServiceBase> getExtraServices() {
    return extraServices;
  }

  public void setExtraServices(List<ExtraServiceBase> extraServices) {
    this.extraServices = extraServices;
  }

  public TravelPassenger accessibility(List<AccessibilityBase> accessibility) {
    this.accessibility = accessibility;
    return this;
  }

  public TravelPassenger addAccessibilityItem(AccessibilityBase accessibilityItem) {
    if (this.accessibility == null) {
      this.accessibility = new ArrayList<>();
    }
    this.accessibility.add(accessibilityItem);
    return this;
  }

   /**
   * List of required accessibility options for the travel.
   * @return accessibility
  **/
  @ApiModelProperty(value = "List of required accessibility options for the travel.")

  @Valid

  public List<AccessibilityBase> getAccessibility() {
    return accessibility;
  }

  public void setAccessibility(List<AccessibilityBase> accessibility) {
    this.accessibility = accessibility;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TravelPassenger travelPassenger = (TravelPassenger) o;
    return Objects.equals(this.category, travelPassenger.category) &&
        Objects.equals(this.extraServices, travelPassenger.extraServices) &&
        Objects.equals(this.accessibility, travelPassenger.accessibility);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, extraServices, accessibility);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TravelPassenger {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    extraServices: ").append(toIndentedString(extraServices)).append("\n");
    sb.append("    accessibility: ").append(toIndentedString(accessibility)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

