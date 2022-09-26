package com.swa.searchservice.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "Job_Table")
@Data
public class JobTable {
    @PrimaryKey
    private UUID jobId;

    private String title;

    private String description;

}