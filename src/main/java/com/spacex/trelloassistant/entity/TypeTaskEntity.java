package com.spacex.trelloassistant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="type_task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String type;
    @Column(name = "DEFAULT_TAG")
    private String defaultTag;
    @Column(name = "HAS_TITLE")
    private boolean hasTitle;
    @Column(name = "HAS_DESCRIPTION")
    private boolean hasDescription;
    @Column(name = "HAS_CATEGORY")
    private boolean hasCategory;
}
