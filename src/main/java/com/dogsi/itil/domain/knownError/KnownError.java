package com.dogsi.itil.domain.knownError;

import java.time.Instant;
//import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.domain.problem.Problem;

@Getter
@Setter
@Entity
@Table(name = "known_error")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class KnownError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "known_error_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant creationDate;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinTable(
        name = "known_error_problem_relation", 
        joinColumns = @JoinColumn(name = "known_error_id"), 
        inverseJoinColumns = @JoinColumn(name = "problem_id"))
    private List<Problem> problems;

    @Column
    private String rootcause;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinTable(
        name = "solutions_known_errors_relation", 
        joinColumns = @JoinColumn(name = "known_error_id"), 
        inverseJoinColumns = @JoinColumn(name = "solution_id"))
    private List<Solution> solutions;

    @Builder
    public KnownError(String name, String category, String description,
            Instant creationDate, String rootcause) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.creationDate = creationDate;
        this.rootcause = rootcause;
        this.problems = new ArrayList<>();
        this.solutions = new ArrayList<>();
    }
    
    public void addProblems(List<Problem> problems) {
        this.problems.addAll(problems);
    }
    
    public void addSolutions(List<Solution> solutions) {
        this.solutions.addAll(solutions);
    }
}
