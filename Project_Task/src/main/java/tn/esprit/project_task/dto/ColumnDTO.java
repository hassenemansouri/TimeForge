package tn.esprit.project_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnDTO {
    private String name;
    private int order;
    private String board;
}
