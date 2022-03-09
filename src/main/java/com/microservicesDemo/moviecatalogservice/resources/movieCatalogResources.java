package com.microservicesDemo.moviecatalogservice.resources;

import com.microservicesDemo.moviecatalogservice.models.Movie;
import com.microservicesDemo.moviecatalogservice.models.Rating;
import com.microservicesDemo.moviecatalogservice.models.UserRating;
import com.microservicesDemo.moviecatalogservice.models.catalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class movieCatalogResources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webBuilder;

    @GetMapping("/{userId}")
    public List<catalogItem> getCatalog(@PathVariable String userId){

    // get all rated movie ids

    UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);


    //for each movie id call movie info service and get details
  return ratings.getUserRating().stream().map(rating -> {
      Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

//    Movie movie = webBuilder.build()
//            .get()
//            .uri("http://localhost:8081/movies/"+rating.getMovieId())
//            .retrieve()
//            .bodyToMono(Movie.class)
//            .block();


       return new catalogItem(movie.getName(), "action",rating.getRating());
    })

            //put them all together
            .collect(Collectors.toList());



         
    }
}
