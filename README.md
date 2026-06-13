# Football Live TV

Native Android (Java) football live-scores & live-TV listings app. Built with
Retrofit + API-Football (RapidAPI), Material 3, ViewPager2 onboarding, and
Google AdMob monetization.

## Features
- Splash + 4-screen onboarding
- Continent selection & Terms acceptance (first launch only)
- Bottom-nav shell: Live Score / Live TV / Settings
- Live Score: real-time fixtures with pull-to-refresh
- Live TV: popular leagues -> fixtures by league
- Settings: notifications, dark mode, auto-refresh, data saver
- AdMob banner + interstitial (test IDs included)

## Setup
1. Clone the repo and open in Android Studio (Giraffe+).
2. Get a free API-Football key from RapidAPI.
3. Add to `local.properties`:
4. (Before release) replace AdMob test IDs in `AndroidManifest.xml` and
   `AdManager.java` with your production IDs.
5. Sync Gradle and Run.

## Project structure
app/src/main/java/com/footballlive/tv/

App.java

ads/AdManager.java

data/local/Prefs.java

data/remote/{ApiService,RetrofitClient}.java

data/remote/dto/Dtos.java

ui/splash | onboarding | location | terms | landing

ui/main | livescore | livetv | settings

util/{Constants,NetworkUtils}.java


## Tech stack
- Java 17, minSdk 23, targetSdk 34
- Retrofit 2 + Gson, OkHttp logging
- Glide for image loading
- Material Components 1.12
- Google Mobile Ads (AdMob)

## License
Proprietary — all rights reserved.