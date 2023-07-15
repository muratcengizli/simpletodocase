package com.hepsi.simpletodocase.model;

import com.hepsi.simpletodocase.enums.ERole;
import com.hepsi.simpletodocase.enums.RegisterType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User implements Serializable{

    private static final long serialVersionUID = -6911002390558341442L;

    @Id
    @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES)
    private String userId;

    private Instant createdAt;
    private String emailAddress;
    private String password;
    private String name;
    private ERole role;
    private RegisterType registerType;
    private Boolean isDeleted;
    private Instant deletedDate;
    private Instant updatedDate;

}
