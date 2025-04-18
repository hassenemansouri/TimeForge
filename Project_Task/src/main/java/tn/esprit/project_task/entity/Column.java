package tn.esprit.project_task.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "columns")
public class Column {
    @Id
    private String _id;
    private String name;
    private int order;
    private String board;
    @DBRef
    private List<Task> tasks;


}

