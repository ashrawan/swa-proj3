package com.swa.apply.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
@Document(collection = "Application")
public class Application {

    @Id
    private Integer id;
    @Indexed(unique = true)
    private float resumeVersion;
    private Integer candidateId;
    private Integer jobId;
    private LocalDate date;
    @Version
    private int version;


}
