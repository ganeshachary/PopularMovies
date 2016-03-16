# PopularMovies
udacity project 1
Made use of picasso library for loading images in gridview.

note :

1.To use Your moviedb api key make changes in build.gradle module:app
instead of MOVIE_DB_API place your api key
 buildTypes.each {
        it.buildConfigField 'String', 'MOVIEDB_API', 'MOVIE_DB_API'

    }
