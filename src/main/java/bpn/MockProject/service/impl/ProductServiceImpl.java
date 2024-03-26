package bpn.MockProject.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import bpn.MockProject.entity.Brand;
import bpn.MockProject.entity.Product;
import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.ProductInDto;
import bpn.MockProject.model.dto.ProductOutDto;
import bpn.MockProject.model.entity.ProductModel;
import bpn.MockProject.repository.BrandReponsitory;
import bpn.MockProject.repository.ProductReponsitory;
import bpn.MockProject.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductReponsitory productReponsitory;

    @Autowired
    private BrandReponsitory brandReponsitory;

    @Override
    public ActionResult getAll(Integer page, Integer size) {
        ActionResult result = new ActionResult();

        Page<Product> productPage = productReponsitory.findAll(PageRequest.of(page - 1, size));

        if (productPage.isEmpty()){
            result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
            return result;
        }

        List<ProductModel> productModels = productPage.stream().map(ProductModel::transform).collect(Collectors.toList());

        ProductOutDto OutDto = new ProductOutDto();
        OutDto.setProducts(productModels);
        OutDto.setTotal(productModels.size());

        result.setData(OutDto);
        return result;
    }

    @Override
    public ActionResult getById(Integer id) {
        ActionResult result = new ActionResult();
        Product product = productReponsitory.getProductById(id);
        if (product == null){
            result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
            return result;
        }

        result.setData(ProductModel.transform(product));
        return result;
    }

    @Override
    public ActionResult getActiveProductByActiveBrand(Integer brandId, Integer page, Integer size) {
    	ActionResult result = new ActionResult();

        Page<Product> productPage = productReponsitory.getActiveProductByActiveBrand(brandId, PageRequest.of(page - 1, size));

        if (productPage.isEmpty()){
            result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
            return result;
        }

        List<ProductModel> productModels = productPage.stream().map(ProductModel::transform).collect(Collectors.toList());

        ProductOutDto OutDto = new ProductOutDto();
        OutDto.setProducts(productModels);
        OutDto.setTotal(productModels.size());

        result.setData(OutDto);
        return result;
    }

    @Override
    public ActionResult create(ProductInDto productIn) {
        ActionResult result = new ActionResult();
        Brand brand = new Brand();
        Product product = new Product();

        brand = brandReponsitory.getBrandById(productIn.getBrandId());
        if (brand == null) {
            result.setErrorCodeEnum(ErrorCodeEnum.NO_HAVE_ID_BRAND);
            return result;
        }else {
            if (brand.getStatus() == false){
                result.setErrorCodeEnum(ErrorCodeEnum.NO_HAVE_ID_BRAND);
                return result;
            }
        }
        product.setBrand(brand);
        product.setName(productIn.getName());
        product.setDescription(productIn.getDescription());
        product.setPrice(productIn.getPrice());
        product.setSold(0);
        product.setStock(0);
        product.setStatus(false);

        product = productReponsitory.save(product);
        if (product == null){
            result.setErrorCodeEnum(ErrorCodeEnum.INVALID_CREATE);
            return result;
        }
        result.setData(ProductModel.transform(product));
        return result;
    }

    @Override
    public ActionResult update(ProductInDto productIn, Integer id) {
        ActionResult result = new ActionResult();
        Product updateP = productReponsitory.getProductById(id);
        if (updateP == null){
            result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
            return result;
        }
        updateP.setName(productIn.getName());
        updateP.setDescription(productIn.getDescription());
        updateP.setPrice(productIn.getPrice());
//        productReponsitory.save(updateP);

        Product product = productReponsitory.save(updateP);

        result.setData(ProductModel.transform(product));
        return result;
    }

    @Override
    public ActionResult updateStock(Integer id, Integer addStock) {
        ActionResult result = new ActionResult();
        
        if (addStock <= 0) {
        	result.setErrorCodeEnum(ErrorCodeEnum.INVALID_NUMBER_PRODUCT_STOCK);
            return result;
		}
        
        Product updateP = productReponsitory.getProductById(id);
        if (updateP == null){
            result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
            return result;
        }
        updateP.setStock(updateP.getStock() + addStock);
        productReponsitory.save(updateP);
        result.setData(ProductModel.transform(updateP));
        return result;
    }

    @Override
    public ActionResult updateStatus(Integer id, boolean status) {
        ActionResult result = new ActionResult();
        Product updateP = productReponsitory.getProductById(id);
        if (updateP == null){
            result.setErrorCodeEnum(ErrorCodeEnum.INVALID_ENTITY);
            return result;
        }
        updateP.setStatus(status);
        productReponsitory.save(updateP);
        result.setData(ProductModel.transform(updateP));
        return result;
    }

	@Override
	public ActionResult findByNameContainingIgnoreCase(String name, Integer page, Integer size) {
		ActionResult result = new ActionResult();

        Page<Product> productPage = productReponsitory.findByNameContainingIgnoreCaseAndStatusIsTrue(name, PageRequest.of(page - 1, size));

        if (productPage.isEmpty()){
            result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
            return result;
        }

        List<ProductModel> productModels = productPage.stream().map(ProductModel::transform).collect(Collectors.toList());

        ProductOutDto OutDto = new ProductOutDto();
        OutDto.setProducts(productModels);
        OutDto.setTotal(productModels.size());

        result.setData(OutDto);
        return result;
	}

	@Override
	public ActionResult findByStatusIsTrueAndPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Integer page,
			Integer size) {
		ActionResult result = new ActionResult();

        Page<Product> productPage = productReponsitory.findByStatusIsTrueAndPriceBetween(minPrice,maxPrice,PageRequest.of(page - 1, size));

        if (productPage.isEmpty()){
            result.setErrorCodeEnum(ErrorCodeEnum.NO_CONTENT);
            return result;
        }

        List<ProductModel> productModels = productPage.stream().map(ProductModel::transform).collect(Collectors.toList());

        ProductOutDto OutDto = new ProductOutDto();
        OutDto.setProducts(productModels);
        OutDto.setTotal(productModels.size());

        result.setData(OutDto);
        return result;
	}
}
