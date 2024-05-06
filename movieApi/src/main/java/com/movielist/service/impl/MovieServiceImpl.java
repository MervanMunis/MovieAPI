package com.movielist.service.impl;

import com.movielist.dto.MovieDto;
import com.movielist.dto.MoviePageResponse;
import com.movielist.entities.Movie;
import com.movielist.exceptions.FileExistsException;
import com.movielist.exceptions.MovieNotFoundException;
import com.movielist.repository.IMovieRepository;
import com.movielist.service.IFileService;
import com.movielist.service.IMovieService;
import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class MovieServiceImpl implements IMovieService {
    
    private final IMovieRepository movieRepository;
    
    private final IFileService fileService;
    
    @Value("${project.poster}")
    private String path;
    
    @Value("${base.url}")
    private String baseUrl;
    
    public MovieServiceImpl(IMovieRepository movieRepository, IFileService fileService){
        this.movieRepository = movieRepository;  
        this.fileService = fileService;
    } 

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        
        if (Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new FileExistsException("File already exists! Please enter anothor file name!");
        }
        String uploadedFileName = fileService.uploadFile(path, file);
        
        movieDto.setPoster(uploadedFileName);
        
        Movie movie = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );
        
        Movie savedMovie = movieRepository.save(movie);
        
        String posterUrl = baseUrl + "/file/" + uploadedFileName;
        
        MovieDto response = new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );
        return response;        
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + movieId));
        
        String posterUrl = baseUrl + "/file/" + movie.getPoster();
        
        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );
        
        
        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        
        List<Movie> movies = movieRepository.findAll();
        
        List<MovieDto> movieDtos = new ArrayList<>();
        
        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            
            MovieDto movieDto = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
            );
            movieDtos.add(movieDto);
        }
       return movieDtos;
    }

    @Override
    public MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException {
        
        Movie currentMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + movieId));
        
        String fileName = currentMovie.getPoster();
        if (file != null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);            
        }
        
        movieDto.setPoster(fileName);
        
        Movie movie = new Movie(
                currentMovie.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
            );
        
        movieRepository.save(movie);
        
        String posterUrl = baseUrl + "/file/" + fileName;
        
        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );    
                
        return response;
    }

    @Override
    public String deleteMovie(Integer movieId) throws IOException{
        
        Movie currentMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + movieId));
        
        Files.deleteIfExists(Paths.get(path + File.separator + currentMovie.getPoster()));
        
        movieRepository.delete(currentMovie);
        
        return "Movie is deleted with id: " + currentMovie.getMovieId();        
    }

    @Override
    public MoviePageResponse getAllMoviesWithPagination(Integer pageNumber, Integer pageSize) {
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        
        Page<Movie> moviePages = movieRepository.findAll(pageable);
        
        List<Movie> movies = moviePages.getContent();
        
        List<MovieDto> movieDtos = new ArrayList<>();
        
        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            
            MovieDto movieDto = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
            );
            movieDtos.add(movieDto);
        }
        
        return new MoviePageResponse(movieDtos, pageNumber, pageSize, 
                                    moviePages.getTotalElements(), 
                                    moviePages.getTotalPages(),
                                    moviePages.isLast());
    }

    @Override
    public MoviePageResponse getAllMoviesWithPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        
        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        
        Page<Movie> moviePages = movieRepository.findAll(pageable);
        
        List<Movie> movies = moviePages.getContent();
        
        List<MovieDto> movieDtos = new ArrayList<>();
        
        for (Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            
            MovieDto movieDto = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
            );
            movieDtos.add(movieDto);
        }
        
        return new MoviePageResponse(movieDtos, pageNumber, pageSize, 
                                    moviePages.getTotalElements(), 
                                    moviePages.getTotalPages(),
                                    moviePages.isLast());
    }
    
}
