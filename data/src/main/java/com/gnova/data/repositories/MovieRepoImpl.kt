package com.gnova.data.repositories

import com.gnova.data.api.MovieApi
import com.gnova.data.mappers.MovieDTOMapper
import com.gnova.data.mappers.TrailerDTOMapper
import com.gnova.domain.models.Movie
import com.gnova.domain.models.Trailer
import com.gnova.domain.repositories.MovieRepo
import io.reactivex.Single
import javax.inject.Inject

class MovieRepoImpl@Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieDTOMapper,
    private val trailerMapper: TrailerDTOMapper,
    ) : MovieRepo {

    override fun getTopRatedMovies(sortBy: String): Single<List<Movie>> {

        return movieApi.getTopRatedMovies("544f2a06a6730f09a1c56cbd233eabb0", "en-us", sortBy,
        "true", "false", 1)
            .map {
                movieMapper.mapToDomainList(it.results)
            }
    }

    override fun getTrailers(id: Int): Single<List<Trailer>> {

        return movieApi.getTrailers(id, "544f2a06a6730f09a1c56cbd233eabb0", "en-us")
            .map {
                trailerMapper.mapToDomainList(it.results)
            }
    }
}

