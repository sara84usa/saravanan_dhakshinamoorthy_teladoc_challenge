@movieAPI
Feature: Get Movie Titles from API

  @getMovieTitle
  Scenario: Get movie titles from API
    Given user has access to movie endpoint "/api/movies/search/"
    When user search for the movie title "spiderman"
    Then user want to view the results from page "1"
    Then user makes a request GET api to view the movie titles
    Then user should get the response code 200
    Then user want to view the results from page "2"
    Then user makes a request GET api to view the movie titles
    Then user should get the response code 200
    Then sort the movie titles in ascending order