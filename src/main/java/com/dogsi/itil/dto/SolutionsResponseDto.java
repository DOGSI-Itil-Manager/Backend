package com.dogsi.itil.dto;

import java.time.Instant;
import java.util.List;

import com.dogsi.itil.domain.knownError.KnownError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SolutionsResponseDto {
    private Long id;
    private String name;
    private Instant creationDate;
    private List<KnownError> knownErrors;
}
