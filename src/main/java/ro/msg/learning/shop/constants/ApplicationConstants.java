package ro.msg.learning.shop.constants;

public class ApplicationConstants {

    // EntityManager title
    public static final String ENTITY_MANAGER = "CriteriaBuliderEntityManager";

    // Products

    // Success
    public static final String SUCCESSFULLY_FETCHED_ALL_PRODUCTS = "Fetch products successfully!";
    public static final String GET_PRODUCT_BY_ID_SUCCESS = "Successfully get product by id!";
    public static final String CREATE_PRODUCT_SUCCESS = "Successfully created new product!";
    public static final String UPDATE_PRODUCT_SUCCESS = "Successfully updated product!";
    public static final String DELETE_PRODUCT_SUCCESS = "Successfully deleted product!";

    // Fail
    public static final String FETCH_ALL_PRODUCTS_FAIL = "Fetch all product failed with some errors!";
    public static final String GET_PRODUCT_BY_ID_FAIL = "Fail to fetch searched product!";
    public static final String CREATE_PRODUCT_FAIL = "Fail to create new product!";

    // Product
    public static final String PRODUCT_ID = "Id";

    // Orders

    // Locations

    // Success
    public static final String ORDER_CREATE_SUCCESS = "Successfully created order!";

    // Fail
    public static final String ORDER_CREATE_FAIL = "Fail to create order!";

    // Exceptions
    public static final String PRODUCT_NOT_FOUND = "Searched product couldn't been found!";
    public static final String NO_LOCATION_FOUND_FOR_ORDER_PRODUCTS = "No location found with necessary stock!";
    public static final String NO_LOCATION_FOUND_FOR_THE_GIVEN_ID = "No location found for the given id!!";
    public static final String NO_AVAILABLE_STOCK_FOUND = "No stock found with necessary available quantity!";
    public static final String CSV_CONVERTER_EXPORT = "Cannot export data as csv file!";

    // Search fields

    // Queries
    public static final String GET_ALL_LOCATIONS_BY_SINGLE_STRATEGY = "";
    public static final String GET_ALL_LOCATIONS_BY_MOST_ABUNDANT_STRATEGY = "";

}
