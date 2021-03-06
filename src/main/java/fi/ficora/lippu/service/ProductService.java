/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.service;

import fi.ficora.lippu.domain.*;
import fi.ficora.lippu.domain.model.Accessibility;
import fi.ficora.lippu.domain.model.AccessibilityBase;
import fi.ficora.lippu.domain.model.ProductList;
import fi.ficora.lippu.domain.model.TravelRequest;
import fi.ficora.lippu.exception.TimetableNotFoundException;
import fi.ficora.lippu.repository.DataRepository;
import fi.ficora.lippu.repository.ProductRepository;
import fi.ficora.lippu.repository.TimetableRepository;
import fi.ficora.lippu.repository.TransportRepository;
import fi.ficora.lippu.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private ITimetableService timetableService;
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private DataRepository dataRepository;

    public ProductService() {
    }

    public ProductList getAvailableProductsTo(LocalDate date, Double toLat, Double
            toLon, List<Accessibility> accessibilities) {
        List<Product> products;
        if (date == null || toLon == null || toLat == null) {
            return null;
        }
        products = productRepository.findByToLatAndToLon(toLat, toLon);
        if (accessibilities.size() > 0) {
            products = filterByAccessibilities(products, accessibilities);
        }
        return checkProductTimetable(products, date);
    }

    public ProductList getAvailableProductsFrom(LocalDate date, Double fromLat, Double
            fromLon, List<Accessibility> accessibilities) {
        if (date == null || fromLon == null || fromLat == null) {
            return null;
        }
        List<Product> products = productRepository.findByFromLatAndFromLon(fromLat,
                fromLon);
        if (accessibilities.size() > 0) {
            products = filterByAccessibilities(products, accessibilities);
        }
        return checkProductTimetable(products, date);
    }

    public ProductList getAvailableProducts(LocalDate date, Double fromLat, Double
            fromLon, Double toLat, Double toLon, List<Accessibility> accessibilities) {
        if (date == null || fromLon == null || fromLat == null || toLat == null ||
                toLon == null) {
            return null;
        }
        List<Product> products = productRepository
                .findByFromLatAndFromLonAndToLatAndToLon(fromLat, fromLon, toLat, toLon);
        if (accessibilities.size() > 0) {
            products = filterByAccessibilities(products, accessibilities);
        }
        return checkProductTimetable(products, date);
    }

    public ProductList getAvailableProducts(LocalDate date, List<Accessibility>
            accessibilities) {
        DayOfWeek day = date.getDayOfWeek();
        List<String> products = timetableService.getProductIdsOperateOnDay(day);
        ProductList productList = new ProductList();
        for (String productId : products) {
            Product product = productRepository.findOne(productId);
            // Check are there required accessibilities
            if (accessibilities.size() > 0) {
                if (hasRequiredAccessibityFeatures(product, accessibilities)) {
                    productList.add(ConversionUtil.productToProductDescription(product,
                            timetableService.getProductDeparture(date, product)));
                }
            } else {
                productList.add(ConversionUtil.productToProductDescription(product,
                        timetableService.getProductDeparture(date, product)));
            }
        }
        return productList;
    }

    public boolean isValidProduct(String productType, String contract) {
        for (Product productIter : productRepository.findAll()) {
            if (productIter.getProductType().equalsIgnoreCase(productType) &&
                    productIter.getContract().equalsIgnoreCase(contract)) {
                return true;
            }
        }
        return false;
    }

    public Fare getFare(String id) {
        // @todo replace stub implementation, which would calculate proper prices.
        Fare fare = new Fare();
        fare.setAmount(14d);
        fare.setVatPercent(10d);
        fare.setCurrency("EUR");
        fare.setProductId(id);
        return fare;
    }

    public TransportInfo getTransport(String id) {
        // @todo replace stub implementation
        return transportRepository.findByProductId(id);
    }

    public Product getProduct(TravelRequest travel, String contract) throws
            TimetableNotFoundException {
        List<Product> products = productRepository
                .findByFromLatAndFromLonAndToLatAndToLon(travel.getFrom().getLat(),
                        travel.getFrom().getLon(), travel.getTo().getLat(), travel
                                .getTo().getLon());

        List<Product> validProducts = checkProductTimetable2(products, travel
                .getDepartureTimeEarliest(), travel.getArrivalTimeLatest());
        // @todo handle case where multiple product found
        if (validProducts.size() == 1) {
            return validProducts.get(0);
        } else {
            return null;
        }
    }

    public List<String> getPassengerCategories() {
        return dataRepository.getPassengerCategories();
    }

    private ProductList checkProductTimetable(List<Product> products, LocalDate date) {
        ProductList productList = new ProductList();
        products.forEach(p -> {
            if (timetableService.doesProductOperateOn(date, p)) {
                productList.add(ConversionUtil.productToProductDescription(p,
                        timetableService.getProductDeparture(date, p)));
            }
        });
        return productList;
    }

    private List<Product> checkProductTimetable2(List<Product> products, OffsetDateTime
            departure, OffsetDateTime arrival) throws TimetableNotFoundException {
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            if (timetableService.hasProductDepartures(departure, arrival, product)) {
                productList.add(product);
            }
        }
        return productList;
    }

    private List<Product> filterByAccessibilities(List<Product> products, List<?
            extends AccessibilityBase> accessibilities) {

        return products.stream().filter(p -> hasRequiredAccessibityFeatures(p,
                accessibilities)).collect(Collectors.toList());
    }

    public boolean hasRequiredAccessibityFeatures(Product product, List<? extends
            AccessibilityBase> accessibilities) {
        boolean foundAll = true;
        for (AccessibilityBase accessibility : accessibilities) {
            boolean foundAccessibility = false;
            if (accessibility.getTitle().equals(Accessibility.TitleEnum.UNKNOWN)) {
                foundAccessibility = true;
            } else {
                AccessibilityFeature accessibilityFeature2 =
                        getAccessibilityFromProduct(product, accessibility.getTitle());
                if (accessibilityFeature2 != null) {
                    log.debug("Found accessibility:{}", accessibilityFeature2.getTitle());
                    foundAccessibility = true;
                }
            }
            if (!foundAccessibility) {
                log.debug("Did not find accessibility {} in the product {}",
                        accessibility.getTitle(), product.getId());
                foundAll = false;
                break;
            }
        }
        return foundAll;
    }

    public AccessibilityFeature getAccessibilityFromProduct(Product product,
                                                            Accessibility.TitleEnum
                                                                    title) {
        return product.getAccessibilities().get(title.toString());
    }

    public ExtraServiceFeature getExtraServiceFromProduct(Product product, String title) {
        return product.getExtraServiceFeatures().get(title);
    }
}
