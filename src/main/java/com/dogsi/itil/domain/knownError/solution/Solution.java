package com.dogsi.itil.domain.knownError.solution;

import com.dogsi.itil.domain.knownError.KnownError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "solution")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant creationDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "solutions_known_errors_relation", 
        joinColumns = @JoinColumn(name = "solution_id"), 
        inverseJoinColumns = @JoinColumn(name = "known_error_id"))
    private List<KnownError> knownErrors;

    @Builder
    public Solution(String name, Instant creationDate) {
        this.name = name;
        this.creationDate = creationDate;
        this.knownErrors = new ArrayList<>();
    }

    public void addKnownError(List<KnownError> knownErrors) {
        this.knownErrors.addAll(knownErrors);
    }
}