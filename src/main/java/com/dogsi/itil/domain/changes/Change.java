package com.dogsi.itil.domain.changes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dogsi.itil.domain.Impact;
import com.dogsi.itil.domain.Priority;
import com.dogsi.itil.domain.incident.Incident;
import com.dogsi.itil.domain.problem.Problem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;


@Getter
@Setter
@Entity
@Table(name = "changes")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Change {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "change_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private Impact impact;

    @Column
    private String description;

    @Column
    private Date closedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "change_incident_relation", 
        joinColumns = @JoinColumn(name = "change_id"), 
        inverseJoinColumns = @JoinColumn(name = "incident_id"))
    private List<Incident> incidents;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "change_problem_relation", 
        joinColumns = @JoinColumn(name = "change_id"), 
        inverseJoinColumns = @JoinColumn(name = "problem_id"))
    private List<Problem> problems;

    @Column
    private String emailOfUserInCharge;

    public Change(String name, String category, Priority priority, Impact impact, String description, Date closedDate,
            String emailOfUserInCharge) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.impact = impact;
        this.description = description;
        this.closedDate = closedDate;
        this.emailOfUserInCharge = emailOfUserInCharge;
    }

    public void addIncidents(List<Incident> incidents) {
        this.incidents.clear();
        this.incidents.addAll(incidents);
    }

    public void addProblems(List<Problem> problems) {
        this.problems.clear();
        this.problems.addAll(problems);
    }
}
