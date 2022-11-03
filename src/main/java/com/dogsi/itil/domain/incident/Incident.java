package com.dogsi.itil.domain.incident;

import com.dogsi.itil.domain.problem.Problem;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.FetchType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.incident.enums.*;
import com.dogsi.itil.domain.problem.Problem;

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
    @Column(nullable = false)
    private Priority priority;
    @Column(nullable = false)
    private Impact impact;
    @Column(nullable = false)
    private State state;

    @Column
    private String assignee;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant reportedDate;

    @Column
    private Date closedDate;

    @Column
    private Satisfaction satisfaction;

    @JsonIgnore
    @ManyToMany(mappedBy = "incidents")
    private List<Problem> problems;

    @Builder
    public Incident(String name, String category, Priority priority, Impact impact, State state, String assignee, String description,
            Instant reportedDate, Date closedDate, Satisfaction satisfaction) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.impact = impact;
        this.state = state;
        this.assignee = assignee;
        this.description = description;
        this.reportedDate = reportedDate;
        this.closedDate = closedDate;
        this.satisfaction = satisfaction;
    }
}
