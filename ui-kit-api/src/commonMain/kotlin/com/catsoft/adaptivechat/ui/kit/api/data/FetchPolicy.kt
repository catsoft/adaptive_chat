package com.catsoft.adaptivechat.ui.kit.api.data

import kotlinx.serialization.Serializable

/**
 * Defines the data fetching strategy for loading content from cache and/or network.
 *
 * This policy determines the order and conditions under which data is retrieved
 * from local cache storage versus making network requests.
 */
@Serializable
enum class FetchPolicy {
    /**
     * Cache first, then always fetch update from network.
     *
     * Initially returns cached data immediately (if available), then always makes
     * a network request to fetch fresh data and update the cache.
     */
    CACHE_FIRST,

    /**
     * Network first, then load from cache if updates arrive.
     *
     * Prioritizes fetching from network first, then falls back to cached data
     * if the network request fails or while waiting for the network response.
     */
    NETWORK_FIRST,

    /**
     * Load only from network.
     *
     * Ignores cached data completely and always fetches fresh data from the network.
     * No cache reads or writes are performed.
     */
    NETWORK_ONLY,

    /**
     * Load only from cache.
     *
     * Returns only cached data without making any network requests.
     * If no cached data exists, returns empty/null result.
     */
    CACHE_ONLY,

    /**
     * Load from cache, fallback to network if cache is empty.
     *
     * Returns cached data if available. Only makes a network request if the cache
     * is empty or doesn't contain the requested data.
     */
    CACHE_NETWORK_IF_EMPTY
}
