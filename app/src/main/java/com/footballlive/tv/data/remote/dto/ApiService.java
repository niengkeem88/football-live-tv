package com.footballlive.tv.data.remote;

import com.footballlive.tv.data.remote.dto.Dtos.CountryResponse;
import com.footballlive.tv.data.remote.dto.Dtos.FixtureResponse;
import com.footballlive.tv.data.remote.dto.Dtos.LeagueResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("fixtures")
    Call<FixtureResponse> getLiveFixtures(@Query("live") String live); // pass "all"

    @GET("fixtures")
    Call<FixtureResponse> getFixturesByLeague(@Query("league") int leagueId,
                                              @Query("season") int season);

    @GET("fixtures")
    Call<FixtureResponse> getFixturesByDate(@Query("date") String date);

    @GET("leagues")
    Call<LeagueResponse> getLeaguesByCountry(@Query("code") String iso2);

    @GET("countries")
    Call<CountryResponse> getCountries();
}