package org.example.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotNull(message = "id cannot be empty")
    private int id;
    @NotEmpty(message = "id cannot be empty")
    private String name;
    @NotNull(message = "id cannot be empty")
    private int age ;
    @NotEmpty(message = "id cannot be empty")
    private String major;
}
