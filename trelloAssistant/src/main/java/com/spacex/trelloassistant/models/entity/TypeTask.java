package com.spacex.trelloassistant.models.entity;

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
public class TypeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String type;
    @OneToOne
    private Task task;
    @Column(name = "DEFAULT_TAG")
    private String defaultTag;
    @Column(name = "HAS_TITLE")
    private boolean hasTitle;
    @Column(name = "HAS_DESCRIPTION")
    private boolean hasDescription;
    @Column(name = "HAS_CATEGORY")
    private boolean hasCategory;
}
