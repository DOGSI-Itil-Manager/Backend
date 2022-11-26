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

//import com.dogsi.itil.domain.knownError.solution.Solution;
import com.dogsi.itil.domain.problem.Problem;

@Getter
@Setter
@Entity
@Table(name = "knownError")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class KnownError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "knownError_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column
    private String description;

    @Column(nullable = false)
    private Instant creationDate;

    //@OneToOne(mappedBy = "knownError")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "knownError_problem_relation", 
        joinColumns = @JoinColumn(name = "knownError_id"), 
        inverseJoinColumns = @JoinColumn(name = "problem_id"))
    private List<Problem> problems;

    @Column
    private String rootcause;

    //@OneToMany(mappedBy = "knownError", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private List<Solution> solutions;

    @Builder
    public KnownError(String name, String category, String description,
            Instant creationDate, String rootcause) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.creationDate = creationDate;
        this.rootcause = rootcause;
        this.problems = new ArrayList<>();
        //this.solutions = new ArrayList<>();
    }
    public void addProblems(List<Problem> problems) {
        this.problems.clear();
        this.problems.addAll(problems);
    }
    
/*
    public void addSolutions(List<Solution> solutions) {
        this.solutions.clear();
        this.solutions.addAll(solutions);
    }
*/
}
