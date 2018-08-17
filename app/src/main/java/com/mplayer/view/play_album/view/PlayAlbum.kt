package com.mplayer.view.play_album.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

import com.mplayer.R
import com.mplayer._core.model.search_album.Album
import com.mplayer.view._core.BaseActivity
import com.mplayer.view._core.ImageLoader
import com.mplayer.view._core.appComponent
import com.mplayer.view._core.startAnimatedActivity
import com.mplayer.view.browse.BrowseContainer
import com.mplayer.view.play_album.di.PlayAlbumComponent
import com.mplayer.view.play_album.di.PlayAlbumModule
import com.mplayer.view.play_album.presenter.PlayAlbumPresenter
import kotlinx.android.synthetic.main.player_ui.*
import javax.inject.Inject

/**
 * Created by Sudeep SR on 15/08/18.
 * Company <Reliance Payment Solutions Ltd.>
 * Email <sudeep.sr@ril.com>
 */
class PlayAlbum : BaseActivity(), PlayerView {

    @Inject
    lateinit var presenter: PlayAlbumPresenter

    private var isFav: Boolean = false
    private var album: Album? = null
    private var exoPlayer: SimpleExoPlayer? = null
    private var eventListener: MediaPlayerHolder = MediaPlayerHolder(this)
    private var isPlaying = true
    private var playAlbumComponent: PlayAlbumComponent? = null

    override fun upadteFavStatus(isFav: Boolean, isClicked: Boolean) {
        this.isFav = isFav
        if (isFav) {
            if (isClicked)
                showToast(album!!.trackName + " " + getText(R.string.add_to_fav))
            ivAddToFavourite.setImageResource(R.drawable.shape_heart_red)
        } else {
            if (isClicked)
                showToast(album!!.trackName + " " + getText(R.string.removed_from_fav))
            ivAddToFavourite.setImageResource(R.drawable.shape_heart)
        }
    }

    override fun showPlayerMessage(message: String) {
        showToast(message)
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }


    companion object {
        val ALBUM_DATA: String = "album_data"
        fun newIntent(context: Context, album: Album): Intent {
            var intent: Intent = Intent(context, PlayAlbum::class.java)
            intent.putExtra(ALBUM_DATA, album)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_ui)
        init(R.drawable.arrow_back)
        playAlbumComponent = appComponent().plus(PlayAlbumModule())
        if (playAlbumComponent == null) {
            this.finish()
        }
        playAlbumComponent?.inject(this)
        presenter.attachView(this)
        supportActionBar?.elevation = resources.getDimension(R.dimen.toolbar_elevation)
        album = intent?.getParcelableExtra<Album>(ALBUM_DATA)
        if (album == null) {
            album = savedInstanceState?.getParcelable(ALBUM_DATA)
        }

        if (album == null) {
            this.finish()
        }

        ivPlayList.setOnClickListener {

            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.m_fav -> startAnimatedActivity(BrowseContainer.newIntent(this, BrowseContainer.BROSE_FAV))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        ImageLoader.loadImage(ivAlbumMainArt, this.album!!.artMainUrl)
        ivAddToFavourite.setOnClickListener {
            presenter.updateFavouritedStatus(!this.isFav, this.album!!)
        }
        presenter.init(album!!.trackId)
        initPlayer()
    }


    private fun initPlayer() {
        tvTrackName.setText(album!!.trackName)
        tvTrackSubName.setText(album!!.artistName + " | " + album!!.collectionName)
        initPlayer(Uri.parse(album!!.previewUrl))
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putParcelable(ALBUM_DATA, album)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    //Init player

    private fun initPlayer(uri: Uri) {
        var trackSelector: TrackSelector = DefaultTrackSelector()
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        val dataSourceFactory = DefaultDataSourceFactory(this, com.google.android.exoplayer2.util.Util.getUserAgent(this, getText(R.string.app_name).toString()), null)
        val extractorsFactory = DefaultExtractorsFactory()
        val audioSource = ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null)
        var playView: com.google.android.exoplayer2.ui.PlayerView = palyerView
        playView.useController = true

        playView.player = exoPlayer
        playView.setShutterBackgroundColor(Color.TRANSPARENT)
        playView.showController()
        exoPlayer!!.addListener(eventListener)
        exoPlayer!!.prepare(audioSource)
        initMediaController()
    }

    private fun initMediaController() {
        initPlayButton()
        initSeekbar()
        setPlayPause(isPlaying)
    }

    private fun initPlayButton() {
        audioPlay.setOnClickListener {
            setPlayPause(!isPlaying)
        }
    }

    private fun initSeekbar() {

    }

    override fun playerSeekTo(pos: Long) {
        exoPlayer?.seekTo(pos)
    }

    override fun setPlayPause(play: Boolean) {
        this.isPlaying = play
        exoPlayer!!.playWhenReady = play
        if (isPlaying) {
            audioPlay.setImageResource(R.drawable.combined_shape_2)
            setProgress()
        } else {
            audioPlay.setImageResource(R.drawable.triangle)
        }
    }

    override fun setProgress() {

    }

    override fun onResume() {
        super.onResume()
        presenter.init(album!!.trackId)
    }

    override fun onPause() {
        super.onPause()
        if (isPlaying) {
            setPlayPause(!isPlaying)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        playAlbumComponent = null //releasing the component
        exoPlayer?.release()
    }

}