package bpn.MockProject.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.ResponseBuild;
import bpn.MockProject.model.ResponseModel;
import bpn.MockProject.model.dto.ProductInDto;
import bpn.MockProject.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseBuild responseBuild;

    @GetMapping("/all")
    public ResponseModel  getAll(@RequestParam Integer page,@RequestParam Integer size){
        ActionResult result = null;
        try {
            result = productService.getAll(page, size);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @GetMapping("/{id}")
    public ResponseModel getByid(@PathVariable Integer id){
        ActionResult result = null;
        try {
            result = productService.getById(id);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }
    @GetMapping("/brand/{id}")
    public ResponseModel getActiveProductByActiveBrand(@PathVariable Integer id, @RequestParam Integer page,@RequestParam Integer size){
        ActionResult result = null;
        try {
            result = productService.getActiveProductByActiveBrand(id, page, size);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }
    
    @GetMapping("/name/{name}")
    public ResponseModel findByNameContainingIgnoreCase(@PathVariable String name, @RequestParam Integer page,@RequestParam Integer size){
        ActionResult result = null;
        try {
            result = productService.findByNameContainingIgnoreCase(name, page, size);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }
    
    @GetMapping("/price/{minPrice}/to/{maxPrice}")
    public ResponseModel findByStatusIsTrueAndPriceBetween(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice,
    		@RequestParam Integer page,@RequestParam Integer size){
        ActionResult result = null;
        try {
            result = productService.findByStatusIsTrueAndPriceBetween(minPrice, maxPrice, page, size);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @PostMapping("/")
    public ResponseModel create(@RequestBody ProductInDto productIn){
        ActionResult result = null;
        try {
            result = productService.create(productIn);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }


    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody ProductInDto productIn, @PathVariable Integer id){
        ActionResult result = null;
        try {
            result = productService.update(productIn, id);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @PutMapping("/{id}/stock/{add}")
    public ResponseModel updateStock(@PathVariable Integer id,@PathVariable Integer add){
        ActionResult result = null;
        try {
            result = productService.updateStock(id, add);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseModel updateStock(@PathVariable Integer id,@PathVariable boolean status){
        ActionResult result = null;
        try {
            result = productService.updateStatus(id, status);
        }catch (Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }
}
