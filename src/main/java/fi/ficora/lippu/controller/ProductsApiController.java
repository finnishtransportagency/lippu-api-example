package fi.ficora.lippu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.ficora.lippu.config.Constants;
import fi.ficora.lippu.domain.model.Accessibility;
import fi.ficora.lippu.domain.model.ApiError;
import fi.ficora.lippu.domain.model.ProductList;
import fi.ficora.lippu.domain.model.ProductQueryResponse;
import fi.ficora.lippu.service.IProductService;
import fi.ficora.lippu.util.ApiErrorUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date
        = "2017-09-11T13:35:10.864+03:00")

@Controller
public class ProductsApiController implements ProductsApi {
    private static final Logger log = LoggerFactory.getLogger(ProductsApiController
            .class);
    private final ObjectMapper objectMapper;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private IProductService productService;

    public ProductsApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ApiOperation(value = "Product portfolio for given datetime.", notes = "Returns product portfolio of transport operator that is valid on given datetime.As product offerings can change over time, and some products may be seasonal, the date is always part of the search process. If the search parameter {date} is left unchecked, then the time of the query is assumed as a time.", response = ProductQueryResponse.class, tags = {"availability",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns product portfolio and supported passenger types for provided products.", response = ProductQueryResponse.class),
            @ApiResponse(code = 403, message = "Invalid authentication token", response = ApiError.class) })
    @RequestMapping(value = "/products/{date}",
            produces = {"application/json", "application/xml"},
            method = RequestMethod.GET)
    public ResponseEntity<?> products(@ApiParam(value = "Unique message identification that is used for error situations. Every client implementation will create its own message ids." ,required=true) @RequestHeader(value="X-Message-Id", required=true) String xMessageId,@ApiParam(value = "Unique transaction identification that is used for error situations. Value is generated by original requestor and used in every message in the whole request chain related to the same ticket transaction." ,required=true) @RequestHeader(value="X-Transaction-Id", required=true) String xTransactionId,@ApiParam(value = "Text indentification of original requestor. Value is use for error situations." ,required=true) @RequestHeader(value="X-Initiator", required=true) String xInitiator,@ApiParam(value = "JWT authentication token for authorization requests." ,required=true) @RequestHeader(value="X-Authorization", required=true) String xAuthorization,@ApiParam(value = "Datetime value when returned product portfolio is valid. If date is not given, current datetime is used.",required=true) @PathVariable("date") String date,@ApiParam(value = "Longitude part of a coordinate to narrow products query from a certain location.") @Valid @RequestParam(value = "fromLat", required = false) Double fromLat,@ApiParam(value = "Longitude part of a coordinate to narrow products query from a certain location.") @Valid @RequestParam(value = "fromLon", required = false) Double fromLon,@ApiParam(value = "Latitude part of a coordinate to narrow products query to a certain location.") @Valid @RequestParam(value = "toLat", required = false) Double toLat,@ApiParam(value = "Longitude part of a coordinate to narrow products query to a certain location.") @Valid @RequestParam(value = "toLon", required = false) Double toLon,@ApiParam(value = "List of required accessibility options for the product.", allowableValues = "WHEELCHAIR, PUSHCHAIR, PASSENGER_CART, LOW-FLOOR, GUIDE_DOG, ONBOARD_ASSISTANCE, BOARDING_ASSISTANCE, UNACCOMPANIED_MINOR_ASSISTANCE, STEP_FREE_ACCESS, WALKINGSTICK, AUDIO_NAVIGATOR, VISUAL_NAVIGATOR, UMBRELLA, BUGGY, SUITABLE_FOR_WHEELCHAIRS, SUITABLE_FOR_HEAVILY_DISABLED, TACTILE_PLATFORM_EDGES, TACTILE_GUIDING_STRIPS") @Valid @RequestParam(value = "accessibility", required = false) List<String> accessibility,
            @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        if (localDate.isBefore(LocalDate.now())) {
            log.info("Products; date in the past {}, return 400. Message: {}", date,
                    xMessageId);
            return ApiErrorUtil.generateErrorResponse400(messageSource.getMessage("http" +
                    ".error.message.400.pastdate", null, Locale.ENGLISH));
        }
        List<Accessibility> accessibilities = new ArrayList<>();
        if (accessibility != null) {
            for (String accessibilityTitle : accessibility) {
                Accessibility.TitleEnum aacc = Accessibility.TitleEnum.
                        fromValue(accessibilityTitle);
                if (aacc == null) {
                    log.info("Products; accessibility not found, return 400. Message: " +
                            "{}", accessibilityTitle, xMessageId);
                    return ApiErrorUtil.generateErrorResponse400(messageSource
                            .getMessage("http.error.message.400.accessiblity", null,
                                    Locale.ENGLISH));
                } else {
                    accessibilities.add(new Accessibility().title(aacc));
                }
            }
        }

        ProductQueryResponse response = new ProductQueryResponse();
        ProductList productList;
        DecimalFormat oneDigit = new DecimalFormat(Constants.COORDINATE_PRECISION);
        if (fromLat != null && fromLon != null && toLat != null && toLon != null) {
            productList = productService.getAvailableProducts(localDate, Double.valueOf
                    (oneDigit.format(fromLat)), Double.valueOf(oneDigit.format(fromLon)
            ), Double.valueOf(oneDigit.format(toLat)), Double.valueOf(oneDigit.format
                    (toLon)), accessibilities);

        } else if (fromLat != null && fromLon != null) {
            productList = productService.getAvailableProductsFrom(localDate, Double
                    .valueOf(oneDigit.format(fromLat)), Double.valueOf(oneDigit.format
                    (fromLon)), accessibilities);
        } else if (toLat != null && toLon != null) {
            productList = productService.getAvailableProductsTo(localDate, Double
                    .valueOf(oneDigit.format(toLat)), Double.valueOf(oneDigit.format
                    (toLon)), accessibilities);
        } else {
            productList = productService.getAvailableProducts(localDate, accessibilities);
        }
        response.setProducts(productList);
        response.setPassengerCategories(productService.getPassengerCategories());
        log.info("Products; success, {} products, return 200. Message: {}", productList
                .size(), xMessageId);
        return new ResponseEntity<ProductQueryResponse>(response, HttpStatus.OK);
    }
}
