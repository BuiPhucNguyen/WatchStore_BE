package bpn.MockProject.model.dto;

import lombok.Data;

@Data
public class FeedbackInDto {
    private Integer productId;
    private Integer star;
    private String content;
}
