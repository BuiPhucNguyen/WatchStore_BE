package bpn.MockProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bpn.MockProject.entity.FeedbackProduct;

public interface FeedbackRepository extends JpaRepository<FeedbackProduct, Integer> {
	@Query("SELECT f FROM FeedbackProduct f WHERE f.product.id = :productId order by f.star desc")
	Page<FeedbackProduct> getFeedbacksByProductId(@Param("productId") Integer productId, Pageable pageable);

	@Query("SELECT f FROM FeedbackProduct f WHERE f.star = :feedbackStar and f.product.id = :productId")
	Page<FeedbackProduct> getFeedbacksByStarAndProductId(@Param("productId") Integer productId, @Param("feedbackStar") Integer feedbackStar, Pageable pageable);
	
	@Query("SELECT AVG(f.star) FROM FeedbackProduct f WHERE f.product.id = :productId")
    Double findAverageStarByProductId(@Param("productId") Integer productId);
}
