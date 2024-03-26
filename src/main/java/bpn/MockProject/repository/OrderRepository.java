package bpn.MockProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bpn.MockProject.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("SELECT a FROM Order a WHERE a.id = :orderId")
	Order getOrderById(@Param("orderId") Integer orderId);
	
	@Query("SELECT a FROM Order a WHERE a.account.id = :accountId")
	Page<Order> getOrderByAccountId(@Param("accountId") Integer accountId, Pageable pageable);
	
	List<Order> findByIdIn(List<Integer> ids);
}
