package com.mplayer.view.browse.search.view

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.view.View
import android.widget.AdapterView
import com.mplayer.R
import com.mplayer._core.model.search_album.AlbumResponse
import com.mplayer.view.browse.search.di.SearchComponent
import com.mplayer.view.browse.search.di.SearchModule
import com.mplayer.view.browse.search.presenter.SearchPresenter
import kotlinx.android.synthetic.main.search_album.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import com.mplayer.R.id.listView
import com.mplayer._core.db.DBConstant
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.mplayer._core.model.search_album.Album
import com.mplayer.view._core.*
import com.mplayer.view.play_album.view.PlayAlbum
import kotlinx.android.synthetic.main.progress_indicator.*
import kotlinx.coroutines.experimental.delay
import java.util.concurrent.TimeUnit


/**
 * Created by Sudeep SR on 14/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class SearchAlbum : BaseFragment(), SearchView {


    private lateinit var searchResultViewPager: SearchResultPager
    private var browseView: BrowseView? = null
    private lateinit var albumResponse: AlbumResponse
    private var viewItemCnt = 0
    private var keyword: String = "jack"

    override fun showSearchResult(result: AlbumResponse?) {
        keyword = etSearchBox.text.toString()
        tvSongsCnt.text = "All songs " + result?.count
        this.albumResponse = result!!
        calculatePageSize(result)
    }


    override fun hideLoader() {
        super.hideLoader()
    }

    @Inject
    lateinit var presenter: SearchPresenter


    companion object {

        fun newFragment(): SearchAlbum {
            return SearchAlbum()
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        browseView = context as BrowseView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        var searchComponent = appComponent().plus(SearchModule())
        searchComponent?.inject(this@SearchAlbum)

        setHasOptionsMenu(true)
        if (searchComponent == null)
            activity?.finish()
    }

    override fun getLayoutId(): Int {
        return R.layout.search_album
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_fav -> browseView?.navigateToFavourite()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeUp(R.drawable.shape, false)
        searchResultViewPager = SearchResultPager(fragmentManager)
        vpSongsContainer.adapter = searchResultViewPager
        this.presenter.attachView(this)
        etSearchBox.setText(keyword)
        etSearchBox.setSelection(keyword.length)
        if (::albumResponse.isInitialized) {
            hideLoader()
            calculatePageSize(this.albumResponse)
        } else {

            this.presenter.searchAlbum(keyword)
        }

        var keywordAdapter: KeywordAdapter = KeywordAdapter(context, false, null, presenter.getDBAdapter())
        etSearchBox.setAdapter(keywordAdapter)
        etSearchBox.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val cursor = p0?.getItemAtPosition(p2) as Cursor
                var text: String = CursorUtil.getString(cursor, DBConstant.SEARCH_KEYWORD)
                etSearchBox.setText(text)
                etSearchBox.setSelection(text.length)
                hideKeyboard()
                presenter.searchAlbum(text)

            }

        })

        etSearchBox.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    var text=etSearchBox.text.toString()
                    if(TextUtils.isEmpty(text)){
                        showToast(getText(R.string.search_keyword).toString())
                        return true
                    }
                    hideKeyboard()
                    presenter.searchAlbum(etSearchBox.text.toString())
                    return true
                }
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.loaderManager.destroyLoader(ViewConstant.LIST_ALBUM)
        this.presenter.detachView()
    }

    fun calculatePageSize(response: AlbumResponse?) {

        if (viewItemCnt == 0) {
            val cellHeight: Int = resources.getDimensionPixelSize(R.dimen.art_img_width) + 2 * resources.getDimensionPixelSize(R.dimen.track_padding) + resources.getDimensionPixelSize(R.dimen.track_view_margin_top)
            val viewHeight: Int = vpSongsContainer.height
            viewItemCnt = viewHeight / cellHeight
        }

        if (response?.count == 0) {
            showMessage(getText(R.string.no_search).toString())

        }
        var adpaterSize: Int = 0
        if (response!!.count > 0) {
            adpaterSize = response!!.count / viewItemCnt
            if (response!!.count % viewItemCnt > 0) {
                adpaterSize++
            }
        }

        searchResultViewPager.setSearchResultSize(adpaterSize, viewItemCnt)
         indicator.setViewPager(vpSongsContainer)

        launch(UI) {
            delay(1,TimeUnit.SECONDS)
            vpSongsContainer?.currentItem = 0

        }

    }


    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }




    override fun showLoader() {
        super.showLoader()
        searchResultViewPager.setSearchResultSize(0, 0)
    }

    override fun showMessage(message: String?) {
        showToast(message!!)
        layout_progress_indicator.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        tv_error.text = message
    }
}