package com.edu.cundi.cinema.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutorIdDTO  {
    private String id;
    public AutorIdDTO(String id) {
        this.id = id;
    }
}