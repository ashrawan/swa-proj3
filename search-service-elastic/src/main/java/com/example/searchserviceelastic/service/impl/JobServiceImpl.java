package com.example.searchserviceelastic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.searchserviceelastic.entity.JobEntity;
import com.example.searchserviceelastic.service.JobService;
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
public class JobServiceImpl implements JobService {
    private static final String MY_INDEX = "job_index";
    private final ElasticsearchClient elasticsearchClient;

    @Override
    @Cacheable(value = "jobs", key = "#jobTitle", condition = "#result.size() > 0")
    public List<JobEntity> findAllByTitle(String jobTitle) {
        log.info("findAll by job title {}", jobTitle);
        SearchRequest searchRequest = SearchRequest.of(s -> s
                        .index(MY_INDEX)
                        .q(jobTitle)
//                .query(q -> q
//                        .bool(b -> b
//                                .must(m -> m.term(
//                                        t -> t.field("title").value(FieldValue.of(jobTitle))))
//                        )
//                )
        );
        SearchResponse<JobEntity> search = null;
        try {
            search = elasticsearchClient.search(searchRequest, JobEntity.class);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
        return search.hits().hits().stream().map(Hit::source).toList();
    }

    @Override
    @Cacheable(value = "jobs", key = "#id")
    public JobEntity findOne(String id) {

        log.info("JobEntity searching by ID {}", id);
        GetResponse<JobEntity> response = null;
        try {
            response = elasticsearchClient.get(g -> g
                    .index(MY_INDEX) // <1>
                    .id(id), JobEntity.class);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }

        if (response.found()) {
            JobEntity jobEntity = response.source();
            return jobEntity;
        } else {
            log.error("Requested JobEntity not found");
            throw new NotFoundException("Request JobEntity Not Found");
        }
    }

    @Override
    @CachePut(value = "jobs", condition = "#result.jobId != null", key = "#result.jobTitle")
    @CacheEvict(value = "jobs", key = "#jobEntity.title")
    public JobEntity save(JobEntity jobEntity) {

        log.info("Saving JobEntity of ID {}", jobEntity.getJobId());
        IndexRequest<JobEntity> indexRequest = IndexRequest.of(b -> b
                .index(MY_INDEX)
                .id(jobEntity.getJobId())
                .document(jobEntity)
                .refresh(Refresh.True));  // Make it visible for search

        try {
            elasticsearchClient.index(indexRequest);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
        return jobEntity;
    }

    @Override
    @CachePut(value = "jobs", key = "#jobEntity.jobTitle")
    @CacheEvict(value = "jobs", key = "#jobEntity.title")
    public JobEntity update(JobEntity jobEntity) {

        log.info("Updating JobEntity of ID {}", jobEntity.getJobId());
        UpdateRequest updateRequest = UpdateRequest.of(b -> b
                .index(MY_INDEX)
                .id(jobEntity.getJobId())
                .doc(jobEntity)
                .refresh(Refresh.True));  // Make it visible for search

        try {
            elasticsearchClient.update(updateRequest, JobEntity.class);
        } catch (IOException e) {
            log.error("Error elastic search ", e.getMessage());
            throw new RuntimeException(e);
        }
        return jobEntity;
    }

    @Override
    public void deleteById(String id) {
        log.warn("JobEntity delete by ID {}", id);
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