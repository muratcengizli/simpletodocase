package com.hepsi.simpletodocase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Item implements Serializable {

    private static final long serialVersionUID = -6911004987558341442L;

    @Id
    @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES)
    private String itemId;

    private String description;
    private Boolean isDone;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean isDeleted;
    private Instant deletedDate;
    private String userId;

}
