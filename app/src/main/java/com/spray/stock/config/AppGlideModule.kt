package com.spray.stock.config

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream


@GlideModule
class GlideConfig : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // 메모리 캐시
        val calculator = MemorySizeCalculator.Builder(context).setMemoryCacheScreens(2f).build()
        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))

        // 비트맵 풀
        val bitmapPoolSizeBytes = 1024 * 1024 * 30 // 30mb
        builder.setBitmapPool(LruBitmapPool(bitmapPoolSizeBytes.toLong()))

        // 디스크 캐시
        val diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB
        builder.setDiskCache(
            InternalCacheDiskCacheFactory(
                context, "cacheFolderName",
                diskCacheSizeBytes.toLong()
            )
        )

        // 로그 레벨
        builder.setLogLevel(Log.DEBUG)
    }
}