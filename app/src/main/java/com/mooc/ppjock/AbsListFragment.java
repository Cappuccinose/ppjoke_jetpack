package com.mooc.ppjock;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mooc.libcommon.view.EmptyView;
import com.mooc.ppjock.databinding.LayoutRefreshViewBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbsListFragment<T,M extends AbsViewModel<T>> extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private LayoutRefreshViewBinding binding;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private EmptyView mEmptyView;
    private M mViewModel;
    private DividerItemDecoration mDecoration;
    private PagedListAdapter<T, RecyclerView.ViewHolder> mAdapter;
    private ViewModel mViewModel1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LayoutRefreshViewBinding.inflate(inflater, container, false);

        mRecyclerView = binding.recyclerView;
        mSmartRefreshLayout =binding.refreshLayout;
        mEmptyView = binding.emptyView;

        mSmartRefreshLayout.setEnableLoadMore(true);
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setOnRefreshListener(this);
        mSmartRefreshLayout.setOnLoadMoreListener(this);

        mAdapter = getAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setItemAnimator(null);

        mDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.list_divider));
        mRecyclerView.addItemDecoration(mDecoration);




        return binding.getRoot();
    }

    private  void getnericViewModel(){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] arguments = type.getActualTypeArguments();
        if (arguments.length > 1){
            Type argument = arguments[1];
            Class aClass = ((Class) argument).asSubclass(AbsViewModel.class);
            mViewModel1 = ViewModelProviders.of(this).get(aClass);

        }

    }


    public abstract PagedListAdapter getAdapter();
}
