package com.dogsi.itil.domain.problem;

import java.time.Instant;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.problem.workaround.Workaround;
import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.domain.incident.enums.*;

@Getter
@Setter
@Entity
@Table(name = "problem")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
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
    private String description;

    @Column(nullable = false)
    private Instant reportedDate;

    @Column
    private Date closedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "problem_incident_relation", 
        joinColumns = @JoinColumn(name = "problem_id"), 
        inverseJoinColumns = @JoinColumn(name = "incident_id"))
    private List<Incident> incidents;

    @Column
    private String emailOfUserInCharge;

    @Column
    private String rootCause;

    @Column
    private List<Workaround> workarounds;

    @Builder
    public Problem(String name, String category, Priority priority, Impact impact, State state, String description,
            Instant reportedDate, Date closedDate, String emailOfUserInCharge) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.impact = impact;
        this.state = state;
        this.description = description;
        this.reportedDate = reportedDate;
        this.closedDate = closedDate;
        this.incidents = new ArrayList<>();
        this.rootCause = "";
        this.workarounds = new ArrayList<>();
        this.emailOfUserInCharge = emailOfUserInCharge;
    }

    public void addIncidents(List<Incident> incidents) {
        this.incidents.clear();
        this.incidents.addAll(incidents);
    }

    public void addWorarounds(List<Workaround> workarounds) {
        this.workarounds.clear();
        this.workarounds.addAll(workarounds);
    }

}
