package com.dogsi.itil.services.knownError.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dogsi.itil.domain.knownError.KnownError;
import com.dogsi.itil.dto.KnownErrorDto;
import com.dogsi.itil.exceptions.ItemNotFoundException;
import com.dogsi.itil.repositories.KnownErrorRepository;
import com.dogsi.itil.repositories.ProblemRepository;
import com.dogsi.itil.services.knownError.KnownErrorService;
import com.dogsi.itil.domain.incident.enums.State;
import com.dogsi.itil.domain.problem.Problem;

@Service
public class KnownErrorServiceImpl implements KnownErrorService {

    private KnownErrorRepository repository;
    private ProblemRepository problemRepository;

    public KnownErrorServiceImpl(KnownErrorRepository repository, ProblemRepository problemRepository) {
        this.repository = repository;
        this.problemRepository = problemRepository;
    }

    @Override
    public void saveKnownError(KnownErrorDto dto) {
        var knownError = KnownError.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .creationDate(dto.getCreationDate())
                .rootcause(dto.getRootcause())
                .build();

        //var ids = dto.getProblemId();
        //if(ids!=null){
        //    var problem = problemRepository.findById(dto.getProblemId());
        //    if(!problem.isPresent()) {
        //        throw new ItemNotFoundException("Problem not found");
        //    }
        //    problem.get().setState(State.CERRADO);
        //    knownError.setProblem(problem.get());
        //}

        repository.save(knownError);
    }

    @Override
    public Page<KnownError> getKnownError(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateKnownError(Long id, KnownErrorDto dto) {
        var knownError = repository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("KnownError with id " + id + " not found");
        });

        var ids = dto.getProblemId();
        if(ids!=null){
            var problem = problemRepository.findById(dto.getProblemId());
            if(!problem.isPresent()) {
                throw new ItemNotFoundException("Problem not found");
            }
            problem.get().setState(State.CERRADO);
            knownError.setProblem(problem.get());
        }
        
        knownError.setName(dto.getName());
        knownError.setCategory(dto.getCategory());
        knownError.setDescription(dto.getDescription());
        knownError.setCreationDate(dto.getCreationDate());
        knownError.setRootcause(dto.getRootcause());

        repository.save(knownError);
    }

    @Override
    public void deleteKnownError(Long id) {
        int deleted = repository.deleteKnownErrorById(id);
        if (deleted == 0) {
            throw new ItemNotFoundException("KnownError with id " + id + " not found");
        }
    }

    @Override
    public KnownError getKnownErrorById(Long id) {
        return repository.findById(id).orElseThrow(() -> {throw new ItemNotFoundException("KnownError with id " + id + " not found");});
    }

}