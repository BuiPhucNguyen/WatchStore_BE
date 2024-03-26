package bpn.MockProject.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint(20)")
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	@Column(name = "address", columnDefinition = "varchar(255)")
	private String address;
	
	@Column(name = "phone", columnDefinition = "varchar(255)")
	private String phone;
	
	@Column(name = "created_date", columnDefinition = "datetime")
	private Date createdDate;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<OrderDetail> orderDetails;
	
	@Column(name = "order_details_price", columnDefinition = "decimal(15,2)")
	private BigDecimal orderDetailsPrice;
	
	@Column(name = "ship_price", columnDefinition = "decimal(15,2)")
	private BigDecimal shipPrice;
	
	@Column(name = "total_price", columnDefinition = "decimal(15,2)")
	private BigDecimal totalPrice;
	
	@Column(name = "note", columnDefinition = "varchar(255)")
	private String note;
	
	@Column(name = "status", columnDefinition = "varchar(255)")
	private String status;
}
