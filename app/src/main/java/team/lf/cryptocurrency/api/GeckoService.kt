package team.lf.cryptocurrency.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import team.lf.cryptocurrency.data.GeckoCoin
import team.lf.cryptocurrency.data.GeckoCoinChart


/**
 * Lego Gecko API access points
 */
interface GeckoService {

    companion object {
        const val ENDPOINT = "https://api.coingecko.com/api/v3/"
    }
    @GET("coins/markets")
    suspend fun getCoinMarket(
        @Query("vs_currency") vs: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc"
    ): Response<List<GeckoCoin>>



    @GET("coins/{id}/market_chart")
    suspend fun getCoinMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "max"
    ): Response<GeckoCoinChart>
}