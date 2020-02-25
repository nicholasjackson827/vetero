package com.ngjackson.vetero.config;

import com.ngjackson.vetero.models.WeatherLocation;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CacheConfig {

  private CacheManager cacheManager;
  private Cache<String, WeatherLocation> weatherLocationCache;

  private static final int CACHE_TTL_IN_MINUTES = 15;

  /**
   * CacheConfig constructor that initializes all the caches we use.
   */
  public CacheConfig() {
    // Build a simple cache manager
    cacheManager = CacheManagerBuilder
        .newCacheManagerBuilder()
        .build();
    cacheManager.init();

    // Build a cache for the weather locations with a simple timeout
    weatherLocationCache = cacheManager
      .createCache("weatherLocation",
        CacheConfigurationBuilder
          .newCacheConfigurationBuilder(String.class, WeatherLocation.class, ResourcePoolsBuilder.heap(10))
          .withExpiry(Expirations.timeToLiveExpiration(Duration.of(CACHE_TTL_IN_MINUTES, TimeUnit.MINUTES)))
      );

  }

  /**
   * Get the weather location cache.
   *
   * @return A configured weather location cache.
   */
  public Cache<String, WeatherLocation> getWeatherLocationCache() {
    return cacheManager.getCache("weatherLocation", String.class, WeatherLocation.class);
  }

  /**
   * Remove a single entry from the weather location cache.
   *
   * @param zipCode The key of the entry in the weather location cache to clear (a zip code).
   */
  public void clearWeatherLocationEntry(String zipCode) {
    getWeatherLocationCache().remove(zipCode);
  }

  /**
   * Remove multiple entries from the weather location cache.
   *
   * @param zipCodes The keys of the entries in the weather location cache to clear (multiple zip codes).
   */
  public void clearWeatherLocationEntries(Set<String> zipCodes) {
    getWeatherLocationCache().removeAll(zipCodes);
  }

  /**
   * Clear all data from the weather location cache.
   */
  public void clearWeatherLocationCache() {
    getWeatherLocationCache().clear();
  }

}
