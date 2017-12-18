package fi.ficora.lippu.domain.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import fi.ficora.lippu.domain.model.ExtraServiceBase;
import fi.ficora.lippu.domain.model.ProductFare;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Extra services for passenger, like transporting animal.
 */
@ApiModel(description = "Extra services for passenger, like transporting animal.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-22T16:56:34.734+02:00")

public class ExtraService extends ExtraServiceBase {

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("fare")
  private ProductFare fare = null;


  public ExtraService description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ExtraService fare(ProductFare fare) {
    this.fare = fare;
    return this;
  }

   /**
   * Get fare
   * @return fare
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ProductFare getFare() {
    return fare;
  }

  public void setFare(ProductFare fare) {
    this.fare = fare;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtraService extraService = (ExtraService) o;
    return Objects.equals(this.title, extraService.title) &&
        Objects.equals(this.description, extraService.description) &&
        Objects.equals(this.fare, extraService.fare);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, fare);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtraService {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fare: ").append(toIndentedString(fare)).append("\n");
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

