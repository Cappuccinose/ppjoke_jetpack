package com.mooc.ppjock;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public abstract class AbsViewModel<T> extends ViewModel {

    private DataSource mDataSource;
    private final PagedList.Config mConfig;
    private LiveData<PagedList<T>> pageData;

    private MutableLiveData<Boolean> boundaryPagedata = new MutableLiveData<Boolean>();


    public AbsViewModel() {
        mConfig = new PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(12)
//                .setMaxSize(100)
//                .setEnablePlaceholders(false)
//                .setPrefetchDistance()
                .build();
        pageData = new LivePagedListBuilder(mFactory,mConfig)
                .setInitialLoadKey(0)
                .setBoundaryCallback(callBack)
                .build();
    }


    public LiveData<PagedList<T>> getPageData(){
        return pageData;
    }


    public DataSource getDataSource(){
        return mDataSource;
    }

    public LiveData<Boolean> getBpundaryPageData(){
        return boundaryPagedata;
    }

    PagedList.BoundaryCallback callBack = new PagedList.BoundaryCallback<T>() {
        @Override
        public void onZeroItemsLoaded() {
            //新提交的pagedList中没有数据
            boundaryPagedata.postValue(false);
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull T itemAtFront) {
            //新提交的PagedList中的第一条数据被加载到列表上
            boundaryPagedata.postValue(true);
        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            //新提交的pagedlist中的最后一条数据被加载到列表上

        }
    };


    DataSource.Factory mFactory = new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            if (mDataSource == null || mDataSource.isInvalid()) {
                mDataSource = createDataSource();
            }
            return mDataSource;
        }
    };

    public abstract DataSource createDataSource();

    @Override
    protected void onCleared() {
        super.onCleared();
        //这个方法中做一些清理的工作
    }
}
