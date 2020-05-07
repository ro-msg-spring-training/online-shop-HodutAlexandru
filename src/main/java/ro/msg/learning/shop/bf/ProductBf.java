package ro.msg.learning.shop.bf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.product.ProductNotFoundException;
import ro.msg.learning.shop.mappers.product.ProductMapper;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.dto.ProductDto;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;
import ro.msg.learning.shop.payload.product.ProductResponse;
import ro.msg.learning.shop.payload.product.ProductsResponse;
import ro.msg.learning.shop.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductBf {

    private final ProductRepository productRepository;

    public ProductsResponse getProducts() {
        return new ProductsResponse(
                StatusCode.OK,
                ApplicationConstants.SUCCESSFULLY_FETCHED_ALL_PRODUCTS,
                this.productRepository.findAll()
        );
    }

    public ProductResponse getProduct(int productId) {
        Product searchedProduct = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
                ApplicationConstants.PRODUCT_NOT_FOUND,
                ApplicationConstants.PRODUCT_ID,
                "" + productId)
        );

        return new ProductResponse(StatusCode.OK,
                ApplicationConstants.GET_PRODUCT_BY_ID_SUCCESS,
                searchedProduct
        );
    }

    public ProductResponse createProduct(ProductDto productDto) {
        Product newProduct = ProductMapper.INSTANCE.productDtoToProduct(productDto);
        return new ProductResponse(
                StatusCode.CREATED,
                ApplicationConstants.CREATE_PRODUCT_SUCCESS,
                this.productRepository.save(newProduct)
        );
    }

    public ProductResponse updateProduct(int productId, ProductDto productDto) {
        Product searchedProduct = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
                ApplicationConstants.PRODUCT_NOT_FOUND,
                ApplicationConstants.PRODUCT_ID,
                "" + productId)
        );

        searchedProduct = ProductMapper.INSTANCE.productDtoToProduct(productDto);
        this.productRepository.save(searchedProduct);

        return new ProductResponse(
                StatusCode.OK,
                ApplicationConstants.GET_PRODUCT_BY_ID_SUCCESS,
                searchedProduct
        );
    }

    public BaseResponse deleteProduct(int productId) {
        this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
                ApplicationConstants.PRODUCT_NOT_FOUND,
                ApplicationConstants.PRODUCT_ID,
                "" + productId)
        );

        this.productRepository.deleteById(productId);

        return new BaseResponse(
                StatusCode.NO_CONTENT,
                ApplicationConstants.DELETE_PRODUCT_SUCCESS
        );
    }

}
