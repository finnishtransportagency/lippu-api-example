/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package fi.ficora.lippu.controller;

import fi.ficora.lippu.domain.model.ApiError;
import java.time.OffsetDateTime;
import fi.ficora.lippu.domain.model.ProductQueryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-22T16:56:34.734+02:00")

@Api(value = "products", description = "the products API")
public interface ProductsApi {

    Logger log = LoggerFactory.getLogger(ProductsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Product portfolio for given datetime.", nickname = "products", notes = "Returns product portfolio of transport operator that is valid on given datetime.As product offerings can change over time, and some products may be seasonal, the date is always part of the search process. If the search parameter {date} is left unchecked, then the time of the query is assumed as a time.", response = ProductQueryResponse.class, tags={ "availability", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns product portfolio and supported passenger types for provided products.", response = ProductQueryResponse.class),
        @ApiResponse(code = 403, message = "Access denied or invalid authentication token.", response = ApiError.class) })
    @RequestMapping(value = "/products/{date}",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)
    default ResponseEntity<?> products(@ApiParam(value = "Unique message identification that is used for error situations. Every client implementation will create its own message ids." ,required=true) @RequestHeader(value="X-Message-Id", required=true) String xMessageId,@ApiParam(value = "Unique transaction identification that is used for error situations. Value is generated by original requestor and used in every message in the whole request chain related to the same ticket transaction." ,required=true) @RequestHeader(value="X-Transaction-Id", required=true) String xTransactionId,@ApiParam(value = "Text indentification of original requestor. Value is use for error situations." ,required=true) @RequestHeader(value="X-Initiator", required=true) String xInitiator,@ApiParam(value = "JWT authentication token for authorization requests." ,required=true) @RequestHeader(value="X-Authorization", required=true) String xAuthorization,@ApiParam(value = "Datetime value when returned product portfolio is valid. If date is not given, current datetime is used.",required=true) @PathVariable("date") String date,@ApiParam(value = "Longitude part of a coordinate to narrow products query from a certain location.") @Valid @RequestParam(value = "fromLat", required = false) Double fromLat,@ApiParam(value = "Longitude part of a coordinate to narrow products query from a certain location.") @Valid @RequestParam(value = "fromLon", required = false) Double fromLon,@ApiParam(value = "Latitude part of a coordinate to narrow products query to a certain location.") @Valid @RequestParam(value = "toLat", required = false) Double toLat,@ApiParam(value = "Longitude part of a coordinate to narrow products query to a certain location.") @Valid @RequestParam(value = "toLon", required = false) Double toLon,@ApiParam(value = "List of required accessibility options for the product.", allowableValues = "WHEELCHAIR, PUSHCHAIR, PASSENGER_CART, LOW-FLOOR, GUIDE_DOG, ONBOARD_ASSISTANCE, BOARDING_ASSISTANCE, UNACCOMPANIED_MINOR_ASSISTANCE, STEP_FREE_ACCESS, WALKINGSTICK, AUDIO_NAVIGATOR, VISUAL_NAVIGATOR, UMBRELLA, BUGGY, SUITABLE_FOR_WHEELCHAIRS, SUITABLE_FOR_HEAVILY_DISABLED, TACTILE_PLATFORM_EDGES, TACTILE_GUIDING_STRIPS") @Valid @RequestParam(value = "accessibility", required = false) List<String> accessibility,
                                       @RequestHeader(value = "Accept", required = false) String accept) throws Exception {

        return new ResponseEntity<ProductQueryResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}