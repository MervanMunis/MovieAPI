package com.movielist.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    
    @Column(nullable = false, length = 200)
    @NotBlank(message = "Please provide movie's title!") // This is validtations for string.
    private String title;
    
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's director!")
    private String director;
    
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's studio!")
    private String studio;
    
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    
    @Column(name = "release_year",nullable = false)
    private Integer releaseYear;
    
    @Column(nullable = false)
    @NotBlank(message = "Please provide movie's poster!")
    private String poster;
    
    
} 


