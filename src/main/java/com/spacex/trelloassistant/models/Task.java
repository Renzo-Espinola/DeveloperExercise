package com.spacex.trelloassistant.models;

import com.spacex.trelloassistant.entity.CategoryEntity;
import com.spacex.trelloassistant.entity.TypeTaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;

    private String description;

    private String title;

    private CategoryEntity categoryEntity;

    private TypeTaskEntity typeTask;
}
