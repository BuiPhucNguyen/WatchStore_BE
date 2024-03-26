package bpn.MockProject.service;

import java.io.IOException;
import java.util.List;

import bpn.MockProject.model.ActionResult;
import bpn.MockProject.model.dto.OrderInDto;

public interface OrderService {
	ActionResult getAllOrders(Integer page, Integer size); 

    ActionResult createOrder(OrderInDto orderIn);
    
    ActionResult findOrderById(Integer id);
    
    ActionResult findOrderByAccountCurrent(Integer page, Integer size); //user
    
    ActionResult updateStatusCompleteOrder(Integer id);
    
    ActionResult updateStatusCancelOrder(Integer id);
    
    ActionResult exportOrder(List<Integer> orderIds, String filePath) throws IOException;
}
