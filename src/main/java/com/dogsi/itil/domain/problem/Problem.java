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
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.incident.Incident;

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
    private String priority;

    @Column(nullable = false)
    private String impact;

    @Column(nullable = false)
    private String state;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant reportedDate;

    @Column
    private Date closedDate;

    @OneToMany(mappedBy = "problem",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<Incident> incidents;

    @Builder
    public Problem(String name, String category, String priority, String impact, String state, String description,
            Instant reportedDate, Date closedDate) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.impact = impact;
        this.state = state;
        this.description = description;
        this.reportedDate = reportedDate;
        this.closedDate = closedDate;
        this.incidents = new ArrayList<>();
    }

    public void addIncident(Incident incident) {
        incidents.add(incident);
    }
}
