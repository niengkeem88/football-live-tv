package com.footballlive.tv.data.remote.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Dtos {

    // ---- Fixtures ----
    public static class FixtureResponse {
        public List<FixtureItem> response;
    }

    public static class FixtureItem {
        public Fixture fixture;
        public League league;
        public Teams teams;
        public Goals goals;
    }

    public static class Fixture {
        public long id;
        public String date;
        public Status status;
    }

    public static class Status {
        @SerializedName("long")  public String longStatus;
        @SerializedName("short") public String shortStatus; // 1H, HT, 2H, FT, NS
        public Integer elapsed;                              // minute
    }

    public static class League {
        public int id;
        public String name;
        public String country;
        public String logo;
        public String flag;
        public Integer season;
    }

    public static class Teams {
        public TeamInfo home;
        public TeamInfo away;
    }

    public static class TeamInfo {
        public int id;
        public String name;
        public String logo;
    }

    public static class Goals {
        public Integer home;
        public Integer away;
    }

    // ---- Leagues ----
    public static class LeagueResponse {
        public List<LeagueItem> response;
    }

    public static class LeagueItem {
        public League league;
        public Country country;
    }

    // ---- Countries ----
    public static class CountryResponse {
        public List<Country> response;
    }

    public static class Country {
        public String name;
        public String code;
        public String flag;
    }
}