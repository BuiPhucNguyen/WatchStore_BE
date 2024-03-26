package bpn.MockProject.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint(20)")
	private Integer id;
	@Column(name = "name", columnDefinition = "varchar(255)")
	private String name;
	@Column(name = "status", columnDefinition = "boolean")
	private Boolean status;

	@OneToMany(mappedBy = "brand")
	private List<Product> products;
}
