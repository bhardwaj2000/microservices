package com.example.curd.service;

import com.example.curd.entity.Product;
import com.example.curd.repo.CurdRepository;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurdServiceImpl implements CurdService {

    Logger log = LoggerFactory.getLogger(CurdServiceImpl.class);

    private final CurdRepository curdRepository;

    public CurdServiceImpl(CurdRepository curdRepository) {
        this.curdRepository = curdRepository;
    }

    @Override
    public void save(Product product) {
        try {
            curdRepository.save(product);
        } catch (Exception e) {
            log.error("Getting exception during saving product {}", e.getMessage());
            throw new SqlScriptException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProduct() {
        try {
            return curdRepository.findAll();
        } catch (Exception e) {
            log.error("Getting exception in fetching product list from DB");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product getProductById(Long productId) throws NoSuchFieldException {
        try {
            Optional<Product> productOptional = curdRepository.findById(productId);
            if (productOptional.isPresent())
                return curdRepository.findById(productId).get();
        } catch (Exception e) {
            log.error("Getting exception in fetching product with id {} from DB", productId);
            throw new NoSuchFieldException(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateProduct(Long productId, Product product) {
        try {
            Optional<Product> productOptional = curdRepository.findById(productId);

            if (productOptional.isPresent()) {
                Product oldProduct = productOptional.get();
                oldProduct.setProductId(oldProduct.getProductId());
                oldProduct.setProdName(product.getProdName());
                oldProduct.setProdPrice(product.getProdPrice());
                curdRepository.save(oldProduct);
            }
        } catch (Exception e) {
            log.error("Getting error in updating product");
            throw new RuntimeException(e.getMessage());
        }

    }
}
