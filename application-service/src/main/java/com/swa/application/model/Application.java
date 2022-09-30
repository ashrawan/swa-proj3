package com.swa.application.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "Application")
public class Application {

    @Id
    private String id;
    @Indexed(unique = true)
    private float resumeVersion;
    private String candidateId;
    private String jobId;
    private LocalDate date;
    @Version
    private int version;
    private Boolean status;


}
