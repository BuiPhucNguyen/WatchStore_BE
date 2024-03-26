package bpn.MockProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "feedback")
public class FeedbackProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint(20)")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column(name = "star", columnDefinition = "bigint(20)")
    private Integer star;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "created_date", columnDefinition = "datetime")
    private Date createDate;
}
