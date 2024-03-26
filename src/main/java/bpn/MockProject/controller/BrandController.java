package bpn.MockProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import bpn.MockProject.enums.ErrorCodeEnum;
import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.ResponseBuild;
import bpn.MockProject.model.ResponseModel;
import bpn.MockProject.model.dto.BrandInDto;
import bpn.MockProject.service.BrandService;

@RestController
@RequestMapping(path = "/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ResponseBuild responseBuild;

    @GetMapping("/all")
    public ResponseModel getAll(){
        ActionResult result = null;
        try {
            result = brandService.getAll();
        }catch(Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }
    
    @GetMapping("/active")
    public ResponseModel getActiveBrands(@Param(value = "page") Integer page, @Param(value = "size") Integer size){
        ActionResult result = null;
        try {
            result = brandService.getActiveBrands(page,size);
        }catch(Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @GetMapping("/{id}")
    public ResponseModel getById(@PathVariable Integer id){
        ActionResult result = null;
        try {
            result = brandService.getById(id);
        }catch(Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @PostMapping("/")
    public ResponseModel create(@RequestBody BrandInDto brand){
        ActionResult result = null;
        try {
            result = brandService.create(brand);
        }catch(Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @PutMapping("/{id}")
    public ResponseModel update(@RequestBody BrandInDto brand, @PathVariable Integer id){
        ActionResult result = null;
        try {
            result = brandService.update(brand, id);
        }catch(Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseModel updateStatus(@PathVariable Integer id, @PathVariable boolean status){
        ActionResult result = null;
        try {
            result = brandService.updateStatus(id, status);
        }catch(Exception e){
            result.setErrorCodeEnum(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
        return responseBuild.build(result);
    }
}
