package bpn.MockProject.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint(20)")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "id")
	private Brand brand;
	
	@Column(name = "name", columnDefinition = "varchar(255)")
	private String name;
	
	@Column(name = "description", columnDefinition = "varchar(255)")
	private String description;
	
	@Column(name = "price", columnDefinition = "decimal(15,2)")
	private BigDecimal price;
	
	@Column(name = "sold", columnDefinition = "bigint(20)")
	private Integer sold;
	
	@Column(name = "stock", columnDefinition = "bigint(20)")
	private Integer stock;
	
	@Column(name = "status", columnDefinition = "boolean")
	private Boolean status;
	
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "product")
	private List<FeedbackProduct> feedbacks;
}
