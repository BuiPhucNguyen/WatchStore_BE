package bpn.MockProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bpn.MockProject.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{

}
