package com.movielist.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Integer movieId;

    @NotBlank(message = "Please provide movie's title!")
    private String title;

    @NotBlank(message = "Please provide movie's director!")
    private String director;

    @NotBlank(message = "Please provide movie's studio!")
    private String studio;

    private Set<String> movieCast;
    
    private Integer releaseYear;
    
    @NotBlank(message = "Please provide movie's poster!")
    private String poster;
    
    @NotBlank(message = "Please provide poster's Url!")
    private String posterUrl;
}
