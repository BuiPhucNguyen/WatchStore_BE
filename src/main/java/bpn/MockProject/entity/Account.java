package bpn.MockProject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "bigint(20)")
	private Integer id;

	@Column(name = "user_name", columnDefinition = "varchar(255)", unique = true)
	private String userName;

	@Column(name = "password", columnDefinition = "varchar(255)")
	private String password;

	@Column(name = "status", columnDefinition = "boolean")
	private Boolean status;
	
	@Column(name = "first_name", columnDefinition = "varchar(50)")
	private String firstName;

	@Column(name = "last_name", columnDefinition = "varchar(50)")
	private String lastName;

	@Column(name = "email", columnDefinition = "varchar(100)", unique = true)
	private String email;

	@Column(name = "created_date", columnDefinition = "datetime")
	private Date createdDate;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "account_role", joinColumns = {
			@JoinColumn(name = "account_id", referencedColumnName = "id") }, 
			inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "account")
	private List<FeedbackProduct> feedbacks;
}
