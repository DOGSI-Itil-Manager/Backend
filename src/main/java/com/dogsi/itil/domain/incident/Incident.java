package com.dogsi.itil.domain.incident;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "incident")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Incident {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;
    //FIXME: terminar de definir los valores posibles
    @Column(nullable = false)
    private String priority;
    //FIXME: terminar de definir los valores posibles
    @Column(nullable = false)
    private String impact;
    //FIXME: terminar de definir los valores posibles
    @Column(nullable = false)
    private String state;

    @Column
    private String assignee;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant reportedDate;

    @Column
    private Date closedDate;

    @Builder
    public Incident(String name, String category, String priority, String impact, String state, String assignee, String description,
            Instant reportedDate, Date closedDate) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.impact = impact;
        this.state = state;
        this.assignee = assignee;
        this.description = description;
        this.reportedDate = reportedDate;
        this.closedDate = closedDate;
    }
}
