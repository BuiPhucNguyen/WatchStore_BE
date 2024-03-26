package bpn.MockProject.model.dto;
import lombok.Data;

import java.util.List;

import bpn.MockProject.model.entity.FeedbackModel;

@Data
public class FeedbackOutDto {
    private List<FeedbackModel> feedbacks;
    private Integer total;
}
