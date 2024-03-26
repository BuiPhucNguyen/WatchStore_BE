package bpn.MockProject.service;

import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.BrandInDto;


public interface BrandService {
    ActionResult getAll();
    
    ActionResult getActiveBrands(Integer page, Integer size);

    ActionResult getById(Integer id);

    ActionResult create(BrandInDto brand);

    ActionResult update(BrandInDto brand, Integer id);

    ActionResult updateStatus(Integer id, Boolean status);
}
