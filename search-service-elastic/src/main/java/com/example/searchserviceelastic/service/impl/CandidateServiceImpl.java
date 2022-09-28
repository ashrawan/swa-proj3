package com.example.searchserviceelastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.searchserviceelastic.entity.CandidateEntity;
import com.example.searchserviceelastic.service.CandidateService;
import com.swa.proj3commonmodule.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private static final String MY_INDEX = "candidate_index";
    private final ElasticsearchClient elasticsearchClient;

    @Override
    @Cacheable(value = "candidates", key = "#candidateName", condition = "#result.size() > 0")
    public List<CandidateEntity> findAllByName(String candidateName) {
        log.info("findAll by candidate title {}", candidateName);
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(MY_INDEX)
                .q(candidateName)
//                .query(q -> q
//                        .bool(b -> b
//                                .must(m -> m.term(
//                                        t -> t.field("fullName").value(FieldValue.of(candidateName))))
//                        )
//                )
        );
        SearchResponse<CandidateEntity> search = null;
        try {
            search = elasticsearchClient.search(searchRequest, CandidateEntity.class);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
        return search.hits().hits().stream().map(Hit::source).toList();
    }

    @Override
    @Cacheable(value = "candidates", key = "#id")
    public CandidateEntity findOne(String id) {

        log.info("CandidateEntity searching by ID {}", id);
        GetResponse<CandidateEntity> response = null;
        try {
            response = elasticsearchClient.get(g -> g
                    .index(MY_INDEX) // <1>
                    .id(id), CandidateEntity.class);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }

        if (response.found()) {
            CandidateEntity candidateEntity = response.source();
            return candidateEntity;
        } else {
            log.error("Requested CandidateEntity not found");
            throw new NotFoundException("Request CandidateEntity Not Found");
        }
    }

    @Override
    @CachePut(value = "candidates", condition = "#result.candidateID != null", key = "#result.candidateID")
    @CacheEvict(value = "candidates", key = "#candidateEntity.fullName")
    public CandidateEntity save(CandidateEntity candidateEntity) {

        log.info("Saving CandidateEntity of ID {}", candidateEntity.getCandidateId());
        IndexRequest<CandidateEntity> indexRequest = IndexRequest.of(b -> b
                .index(MY_INDEX)
                .id(candidateEntity.getCandidateId())
                .document(candidateEntity)
                .refresh(Refresh.True));  // Make it visible for search

        try {
            elasticsearchClient.index(indexRequest);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
        return candidateEntity;
    }

    @Override
    @CachePut(value = "candidates", key = "#candidateTable.candidateId")
    @CacheEvict(value = "candidates", key = "#candidateEntity.fullName")
    public CandidateEntity update(CandidateEntity candidateEntity) {

        log.info("Updating CandidateEntity of ID {}", candidateEntity.getCandidateId());
        UpdateRequest updateRequest = UpdateRequest.of(b -> b
                .index(MY_INDEX)
                .id(candidateEntity.getCandidateId())
                .doc(candidateEntity)
                .refresh(Refresh.True));  // Make it visible for search

        try {
            elasticsearchClient.update(updateRequest, CandidateEntity.class);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
        return candidateEntity;
    }

    @Override
    public void deleteById(String id) {
        log.warn("CandidateEntity delete by ID {}", id);
        DeleteRequest deleteRequest = DeleteRequest.of(s -> s
                .index(MY_INDEX)
                .id(id));
        try {
            elasticsearchClient.delete(deleteRequest);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}