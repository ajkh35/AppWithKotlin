package com.example.ajay3.appwithkotlin

/**
 * Created by ajay3 on 6/30/2017.
 */
class Constants{

    companion object {

        val MOVIE_URL = "https://yts.ag/api/v2/list_movies.json"
        val MOVIE_PAGE_URL = "https://yts.ag/api/v2/list_movies.json?page="

//        val MOVIE_PAGES: Array<String> = arrayOf(
//                "https://yts.ag/api/v2/list_movies.json?page=2",
//                "https://yts.ag/api/v2/list_movies.json?page=3",
//                "https://yts.ag/api/v2/list_movies.json?page=4",
//                "https://yts.ag/api/v2/list_movies.json?page=5",
//                "https://yts.ag/api/v2/list_movies.json?page=6",
//                "https://yts.ag/api/v2/list_movies.json?page=7",
//                "https://yts.ag/api/v2/list_movies.json?page=8",
//                "https://yts.ag/api/v2/list_movies.json?page=9",
//                "https://yts.ag/api/v2/list_movies.json?page=10")

        val VIEW_TYPE_MOVIE = 0
        val VIEW_TYPE_LOADING = 1
    }
}