package com.hotbitmapgg.ohmybilibili.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hotbitmapgg.ohmybilibili.OhMyBiliBiliApp;
import com.hotbitmapgg.ohmybilibili.retrofit.api.BangumiIndexService;
import com.hotbitmapgg.ohmybilibili.retrofit.api.BangumiRecommendService;
import com.hotbitmapgg.ohmybilibili.retrofit.api.BiliBiliLiveService;
import com.hotbitmapgg.ohmybilibili.retrofit.api.LiveUrlService;
import com.hotbitmapgg.ohmybilibili.retrofit.api.RecommendedService;
import com.hotbitmapgg.ohmybilibili.retrofit.api.TwoDimensionalService;
import com.hotbitmapgg.ohmybilibili.retrofit.api.VideoDetailsService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * Retrofit帮助类
 */
public class RetrofitHelper
{

    private static OkHttpClient mOkHttpClient;

    private static final String API_BASE_URL = "http://bilibili-service.daoapp.io/";

    private static final String MAIN_BASE_URL = "http://www.bilibili.com/";

    private static final String APP_BASE_URL = "http://app.bilibili.com/";

    private static final String LIVE_BASE_URL = "http://live.bilibili.com/";

    static
    {
        initOkHttpClient();
    }

    /**
     * 获取哔哩哔哩直播Api
     *
     * @return
     */

    public static BiliBiliLiveService getBiliBiliLiveApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LIVE_BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(BiliBiliLiveService.class);
    }


    /**
     * 获取番剧索引Api
     *
     * @return
     */

    public static BangumiIndexService getBangumiIndexApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(BangumiIndexService.class);
    }

    /**
     * 获取主页推荐Api
     *
     * @return
     */

    public static RecommendedService getHomeRecommendedApi()
    {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APP_BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(RecommendedService.class);
    }

    /**
     * 获取首页番剧推荐列表
     *
     * @return
     */

    public static BangumiRecommendService getBnagumiRecommendApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(BangumiRecommendService.class);
    }

    /**
     * 获取二次元新番
     *
     * @return
     */
    public static TwoDimensionalService getTwoDimensionalApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TwoDimensionalService.class);
    }


    /**
     * 获取视频详情
     *
     * @return
     */
    public static VideoDetailsService getVideoDetailsApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(VideoDetailsService.class);
    }


    /**
     * 获取直播数据Url
     *
     * @return
     */
    public static LiveUrlService getLiveUrlApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LIVE_BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(LiveUrlService.class);
    }


    /**
     * 初始化OKHttpClient
     * 设置缓存
     * 设置超时时间
     * 设置打印日志
     */
    private static void initOkHttpClient()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null)
        {
            synchronized (RetrofitHelper.class)
            {
                if (mOkHttpClient == null)
                {
                    //设置Http缓存
                    Cache cache = new Cache(new File(OhMyBiliBiliApp.getInstance()
                            .getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
}
