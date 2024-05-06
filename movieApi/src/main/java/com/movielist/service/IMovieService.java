package com.movielist.service;

import com.movielist.dto.MovieDto;
import com.movielist.dto.MoviePageResponse;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMovieService {
    
    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException;
    
    MovieDto getMovie(Integer movieId);
    
    List<MovieDto> getAllMovies();
    
    MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException;
    
    String deleteMovie(Integer movieId)throws IOException;
    
    MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize);
    
    MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir);

    
    
}
