package com.myhomeDB.controllers;

import com.myhomeDB.dto.ProductDto;
import com.myhomeDB.models.Product;
import com.myhomeDB.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(p);
    }

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title", required = false) String title
    ) {
        HashMap<String, Object> specificationList = new HashMap<>();
        specificationList.put("min_price", minPrice);
        specificationList.put("max_price", maxPrice);
        specificationList.put("title", title);
        SpecificationFilterOfProductController specFilter = new SpecificationFilterOfProductController(specificationList);
        Specification<Product> spec = specFilter.getSpecification();

        return productService.findPage(pageIndex - 1, 5, spec).map(ProductDto::new);
    }


    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProductDto) {
        Product product = new Product();
        product.setPrice(newProductDto.getPrice());
        product.setTitle(newProductDto.getTitle());
        return new ProductDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
