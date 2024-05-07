# <div align="center">Movie API</div>

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Keywords](#keywords)
4. [Layered Architecture](#layered-architecture)
5. [API Documentation](#api-documentation)
6. [Usage](#usage)

## Introduction
The Movie API project is a comprehensive backend application developed using Java and Spring Boot. It is designed to manage movie data efficiently, providing a RESTful API that allows for the creation, reading, updating, and deletion (CRUD) of movie records. The API is secured with JSON Web Tokens (JWT) to ensure that only authorized users have access to the data.

The project utilizes a MySQL database to store movie data and incorporates advanced topics such as pagination and sorting for efficient data retrieval. Additionally, the application includes validation at both the application and database levels to maintain data integrity and consistency. Exception-handling mechanisms are also implemented to ensure smooth operation and provide helpful error messages to clients.

The Movie API project serves as a great example of modern backend development practices, integrating various aspects of security, database management, and efficient API design.

## Features

The Movie API project offers a variety of features designed to provide efficient and secure management of movie data. The key features of the project include:

1. CRUD Operations: Perform Create, Read, Update, and Delete operations on movie records such as title, director, release year, and studio. This allows for full control over movie data.

2. Layered Architecture: The application follows a layered architecture, separating concerns across different layers such as the presentation, service, and data access layers. This design improves maintainability and scalability.

3. JWT Authentication: Secure access to the API with JSON Web Tokens (JWT). Users must authenticate with a valid token to access protected endpoints, ensuring that only authorized users can perform actions on the API.

4. Data Validation: Both application-level and database-level validation are implemented to maintain data integrity and consistency. This includes checking inputs for proper format and adherence to specified constraints.

5. Manage Movie Details: Manage essential movie details such as title, director, release year, and studio. These fields allow for the comprehensive management of movie data.

6. Handle PNG Images: The API supports handling PNG images associated with movie records. It can store, retrieve, and process PNG images efficiently, making it possible to add visual assets such as movie posters.

7. Image Storage: The API allows for storing PNG images associated with movie records in a specified location, such as a file system or a database. This feature is essential for linking visual media with the respective movie data.

8. Pagination and Sorting: Efficiently navigate large datasets with pagination and sorting capabilities. This feature allows users to retrieve data in a structured and manageable way.

9. Exception Handling: Comprehensive exception handling mechanisms are implemented to provide smooth operation and meaningful error messages to clients.

These features provide a robust foundation for managing movie data efficiently and securely. The API is designed to be easy to use, flexible, and scalable for a wide range of applications and use cases.


## Keywords

* @Setter and @Getter: Automatically creates setter and getter methods for class fields, minimizing repetitive code.

* @AllArgsConstructor: Automatically generates a constructor with arguments for all fields in the class.

* @NoArgsConstructor: Automatically generates a no-argument constructor.

* @Builder: Automatically generates a builder pattern for the class, allowing easy creation of objects with a flexible construction process.

* @Entity: Specifies that the class is a JPA entity, representing a table in the database.

* @Table(name = "movie"): Specifies the table name for the entity in the database. In this case, it maps the entity to the "movie" table.

* @Id: Marks a field as the primary key of the entity.
  
* @GeneratedValue(strategy = GenerationType.IDENTITY): Specifies the strategy for generating the primary key values. IDENTITY uses auto-increment in the database.
  
* @Column(nullable = false, length = 200, name = "title"): Specifies column properties such as nullability, length, and name in the database.

* @NotBlank(message = "Please provide the movie"): A validation annotation that ensures the field is not null or empty. If the condition is not met, an error message is provided.

* @CollectionTable(name = "movie_cast"): Specifies the name of the collection table for one-to-many or many-to-many relationships.

* @Service: An annotation used to mark a class as a service, indicating that it contains business logic and should be managed by the Spring container.
  
* FileService:
    -  MultipartFile: Represents an uploaded file in a web request. It is used to handle file uploads in the application.
      
    -  FileInputStream: A stream that reads data from a file, useful for processing file contents.
    
    -  File.separator: A system-dependent file separator character (e.g., "/" on Unix-like systems and "\" on Windows). Useful for constructing file paths in a cross-platform way.
 
    -  file.getInputStream(): Returns an input stream for reading the contents of a file.
 
    -  InputStream: Represents a stream of bytes for reading data from a source such as a file.
 
    -  fileService.uploadFile(path, file): A method in FileService that uploads a file to the specified path. This method handles the file storage logic.

* @Value("${project.poster}"): A Spring annotation used to inject values from a properties file into the application. In this case, it would inject the value associated with the key project.poster.

* @RestController: Indicates that the class is a RESTful web controller, handling HTTP requests and returning responses.

* @RequestMapping("/file"): Specifies the base URL mapping for the controller. All request handling methods in the controller will be mapped under /file.

* @RequestPart: Used in methods to bind a part of a multi-part request (e.g., a file upload) to a method parameter.

* @PathVariable: This annotation is used to bind a method parameter to a path variable in a URL. For instance, in an endpoint like /movies/{id}, the @PathVariable would bind the {id} segment to a method parameter.

* @Bean: An annotation used to define a bean method in a Spring @Configuration class. The method returns an object that is managed by the Spring container as a bean.

* @PostMapping("/upload"): Maps HTTP POST requests with the path /upload to the method.

* @GetMapping("/{fileName}"): Maps HTTP GET requests with the path /{fileName} to the method. The {fileName} part is a path variable.

* @PutMapping: A Spring MVC annotation used to map HTTP PUT requests to a method. PUT is often used to update an existing resource.

* @PatchMapping: PATCH requests are used to partially update a resource, meaning that only certain fields of an existing resource are modified.

* @DeleteMapping: This Spring MVC annotation is used to map HTTP DELETE requests to a specific method.

* HttpServletResponse: Represents the HTTP response in a servlet. It is used to set response headers and body.

* @PathVariable: Used to bind a method parameter to a path variable in the URL.

* InputStream resourceFile = fileService.getResourceFile(path, fileName): A method that retrieves the file from the given path and filename as an input stream.

* ResponseEntity: A type that represents an HTTP response, including the status code, headers, and body.

* ObjectMapper: A class from the Jackson library used for serializing and deserializing JSON data. It can be used to convert between Java objects and JSON representations.

* response.setContentType(MediaType.IMAGE_PNG_VALUE): Sets the content type of the response to image/png for returning a PNG image.

* StreamUtils.copy(resourceFile, response.getOutputStream()): Copies the data from the input stream (resourceFile) to the response's output stream.

* objectMapper.readValue(movieDtoObj, MovieDto.class): A method from the ObjectMapper class that reads JSON data from the input and converts it into an instance of the specified class (MovieDto).

* @PreAuthorize("hasAuthority('ADMIN')"): A Spring Security annotation that is used to specify that a method or endpoint requires a specific authority (in this case, 'ADMIN'). The method will only be executed if the authenticated user has the specified authority.

* @EnableWebSecurity: An annotation used to enable Spring Security's web security configuration. It applies security configurations to the web application.

* @EnableMethodSecurity: An annotation that enables method-level security in Spring Security. It allows the use of annotations like @PreAuthorize to secure individual methods based on roles or authorities.

* @Configuration: An annotation used to mark a class as a source of Spring configuration. It indicates that the class contains Spring-managed beans and other configuration settings.

* SecurityFilterChain: This method in a Spring Security configuration class is used to configure the security settings for the application. It returns a SecurityFilterChain object, which contains a chain of security filters to be applied to HTTP requests.
    - http.csrf(AbstractHttpConfigurer::disable): Disables Cross-Site Request Forgery (CSRF) protection. This is usually done for stateless RESTful APIs since they handle their own security.
    - http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/").permitAll().anyRequest().authenticated())**: Configures URL-based authorization. Requests to /api/v1/auth/** are permitted for   
    everyone, while other requests require authentication.
    - http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)): Sets the session management policy to stateless, meaning that the server does not maintain a session for 
    clients.

## Layered Architecture

In backend development, there are several architectural styles that can be used to design and structure applications. Common architectures include monolithic, microservices, serverless, and layered architecture.

* Monolithic Architecture: In a monolithic architecture, all application components are tightly integrated into a single application. While this architecture is straightforward and easier to manage initially, it can become cumbersome and challenging to scale as the application grows.

* Microservices Architecture: In a microservices architecture, the application is broken down into smaller, independent services that communicate via APIs. This allows for better scalability, maintainability, and flexibility, as each service can be developed, deployed, and scaled independently.

* Serverless Architecture: In a serverless architecture, the application is built on cloud services, allowing developers to focus on writing code rather than managing infrastructure. Serverless applications automatically scale based on demand.

* Layered Architecture: A layered architecture organizes the application into distinct layers, each with a specific responsibility. This architecture promotes separation of concerns, modularity, and maintainability. The most common layers in a layered architecture include the presentation layer, business logic layer, data access layer, and sometimes a utility layer.

1. Entities (Model): The movieApi/entities folder contains the Movie class, which represents the movie data model. This layer serves as the foundation for other layers, providing a clear data structure.

2. DTOs (Data Transfer Objects): The movieApi/dto folder contains MovieDto and MoviePageResponse classes. DTOs are used to transfer data between different layers and to structure the response for pagination and sorting.

3. Repository: The movieApi/repository folder contains the IMovieRepository interface. This interface defines data access methods for the Movie entity, providing an abstraction over the database operations.

4. Services: The movieApi/services folder contains service interfaces such as IFileService and IMovieService. These interfaces define the business logic and operations for the application, such as file handling and movie management.

5. Service Implementations: The movieApi/services/impl folder contains classes that implement the service interfaces, such as FileServiceImpl and MovieServiceImpl. These classes contain the actual business logic and operations.

6. Exceptions: The movieApi/exceptions folder contains custom exception classes such as EmptyFileException, FileExistsException, GlobalExceptionHandler, MovieNotFoundException, TokenExpiredException, and TokenNotFoundException. These classes handle error scenarios and provide meaningful error messages.

7. Utilities: The movieApi/utils folder contains utility classes such as AppConstants, which provides pagination and sorting information.

8. Controllers: The movieApi/controller folder contains controller classes such as FileController, MovieController, and AuthController. Controllers serve as the interface between the client and the business logic, handling HTTP requests and returning responses.

9. Authentication: The movieApi/auth folder contains authentication-related classes and interfaces such as JwtService, RefreshTokenService, AuthService, and AuthFilterService. These classes handle authentication and authorization using JSON Web Tokens (JWT).

By structuring the application into these distinct layers, you achieve a clean separation of concerns and a modular design that promotes maintainability and scalability.


## API Documentation

Here is an overview of the API endpoints available in your application, including the URLs, HTTP methods, and their purposes:

| URL                                                         | HTTP Method | Purpose                                                     |
|-------------------------------------------------------------|-------------|-------------------------------------------------------------|
| **FileController**                                          |             |                                                             |
| `/file/upload`                                              | POST        | Upload a file.                                              |
| `/file/{fileName}`                                          | GET         | Serve a file by its name.                                   |
| **MovieController**                                         |             |                                                             |
| `/api/v1/movie/add-movie`                                   | POST        | Add a new movie, including an image file.                   |
| `/api/v1/movie/{movieId}`                                   | GET         | Retrieve a specific movie by its ID.                        |
| `/api/v1/movie/all`                                         | GET         | Retrieve all movies.                                        |
| `/api/v1/movie/update/{movieId}`                            | PUT         | Update a specific movie by its ID, including an image file. |
| `/api/v1/movie/delete/{movieId}`                            | DELETE      | Delete a specific movie by its ID.                          |
| `/api/v1/movie/allMoviesPage`                               | GET         | Retrieve all movies with pagination.                        |
| `/api/v1/movie/allMoviesPageSort`                           | GET         | Retrieve all movies with pagination and sorting.            |
| **AuthController**                                          |             |                                                             |
| `/api/v1/auth/register`                                     | POST        | Register a new user.                                        |
| `/api/v1/auth/login`                                        | POST        | Authenticate and login a user.                              |
| `/api/v1/auth/refresh`                                      | POST        | Refresh the authentication token.                           |


## Usage

Below are examples of how to use the Movie API with Postman. Each section includes a screenshot showing how to interact with the API.

### Uploading File

To upload a file, send a POST request to `/file/upload` with a file attachment. The screenshot below demonstrates how to do this in Postman:

![Uploading File](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/UploadFilePostman.png)

### Finding File

To find and retrieve a file, send a GET request to `/file/{fileName}`. The screenshot below demonstrates how to do this in Postman:

![Finding File](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/FindF%C4%B0lePostman.png)

### Adding Movie

To add a new movie, send a POST request to `/api/v1/movie/add-movie` with the movie details and an image file. The screenshot below demonstrates how to do this in Postman:

![Adding Movie](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/AddMoviePostman.png)

### Finding Movie

To find a specific movie by ID, send a GET request to `/api/v1/movie/{movieId}`. The screenshot below demonstrates how to do this in Postman:

![Finding Movie](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/FindMoviePostman.png)

### Listing All Movies

To list all movies, send a GET request to `/api/v1/movie/all`. The screenshot below demonstrates how to do this in Postman:

![Listing All Movies](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/AllMoviesPostman.png)

### Updating Movie

To update an existing movie by ID, send a PUT request to `/api/v1/movie/update/{movieId}` with the new movie details and an image file. The screenshot below demonstrates how to do this in Postman:

![Updating Movie](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/UpdateMoviePostman.png)

### Deleting Movie

To delete a movie by ID, send a DELETE request to `/api/v1/movie/delete/{movieId}`. The screenshot below demonstrates how to do this in Postman:

![Deleting Movie](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/DeleteMoviePostman.png)

### Pagination of Movies

To retrieve movies with pagination, send a GET request to `/api/v1/movie/allMoviesPage` with `pageNumber` and `pageSize` parameters. The screenshot below demonstrates how to do this in Postman:

![Pagination of Movies](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/PaginationMoviePostman.png)

### Pagination and Sorting of Movies

To retrieve movies with pagination and sorting, send a GET request to `/api/v1/movie/allMoviesPageSort` with parameters such as `pageNumber`, `pageSize`, `sortBy`, and `dir`. The screenshot below demonstrates how to do this in Postman:

![Pagination and Sorting of Movies](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/PaginationAndSortingMoviesPostman.png)

### Registering a User

To register a new user, send a POST request to `/api/v1/auth/register` with user details. The screenshot below demonstrates how to do this in Postman:

![Registering a User](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/RegisterPostman.png)

### Logging in a User

To log in a user, send a POST request to `/api/v1/auth/login` with login credentials. The screenshot below demonstrates how to do this in Postman:

![Logging in a User](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/LoginPostman.png)

### Refreshing a Token

To refresh an authentication token, send a POST request to `/api/v1/auth/refresh` with a refresh token. The screenshot below demonstrates how to do this in Postman:

![Refreshing a Token](https://github.com/MervanMunis/MovieAPI/blob/master/Usage/RefreshTokenPostman.png)

These examples should help users understand how to use your API endpoints effectively. Let me know if you need further assistance with the Usage section.

