package com.belrose.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "wikimedia_recentchange")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WikimediaData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob //Annotation to save large data
    private String wikiEventData;

}
