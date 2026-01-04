package com.catsoft.adaptivechat.ui.kit.api.data

import kotlinx.coroutines.flow.Flow

/**
 * Generic interface for fetching and managing data with cache support.
 *
 * Provides methods for retrieving data from cache and/or network using various
 * [FetchPolicy] strategies, as well as saving and updating cached data.
 *
 * @param T The type of data being fetched and cached.
 */
interface DataFetcher<T> {

    /**
     * Retrieves a single piece of data using the specified fetch policy.
     *
     * This method is typically used for data that doesn't require additional parameters
     * to identify (e.g., user profile, app settings).
     *
     * @param policy The [FetchPolicy] strategy to use for data retrieval.
     * @return A [Flow] emitting [ACData] states representing the data loading process.
     */
    fun getSingleData(policy: FetchPolicy): Flow<ACData<T>>

    /**
     * Saves a single piece of data to cache.
     *
     * @param data The data to save, or null to clear the cached data.
     * @return The saved data, or null if data was cleared.
     */
    suspend fun saveSingleData(data: T?): T?

    /**
     * Updates cached data using a transformation function.
     *
     * Retrieves the current cached data for the specified parameter, applies the
     * transformation function, and saves the result back to cache.
     *
     * @param param A parameter identifying which cached data to update.
     * @param transform A lambda function that receives the current cached data (or null)
     *                  and returns the updated data.
     * @return The updated data after transformation, or null if cleared.
     */
    suspend fun updateCachedData(param: String, transform: T?.() -> T?): T?

    /**
     * Retrieves data identified by a parameter using the specified fetch policy.
     *
     * This method is used for data that requires a parameter to identify (e.g., user by ID,
     * conversation by ID).
     *
     * @param param A parameter identifying which data to retrieve.
     * @param policy The [FetchPolicy] strategy to use for data retrieval.
     * @return A [Flow] emitting [ACData] states representing the data loading process.
     */
    fun getData(param: String, policy: FetchPolicy): Flow<ACData<T>>

    /**
     * Saves data identified by a parameter to cache.
     *
     * @param param A parameter identifying where to save the data.
     * @param data The data to save, or null to clear the cached data for this parameter.
     * @return The saved data, or null if data was cleared.
     */
    suspend fun saveData(param: String, data: T?): T?
}
