package bpn.MockProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bpn.MockProject.entity.Brand;

public interface BrandReponsitory extends JpaRepository<Brand, Integer> {
    @Query("SELECT b FROM Brand b WHERE b.id = :brandId")
    Brand getBrandById(@Param("brandId") int brandId);
    
    @Query("SELECT b FROM Brand b WHERE b.status = true")
    Page<Brand> getActiveBrands(Pageable pageable);
}
