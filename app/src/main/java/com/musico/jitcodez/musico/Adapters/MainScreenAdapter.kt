package com.musico.jitcodez.musico.Adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.musico.jitcodez.musico.Songs
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.musico.jitcodez.musico.Activities.MainActivity
import com.musico.jitcodez.musico.Fragments.MainScreenFragment
import com.musico.jitcodez.musico.Fragments.SongPlayingFragment
import com.musico.jitcodez.musico.R

/**
 * Created by jitu on 3/2/18.
 */
class MainScreenAdapter(_songDetails:ArrayList<Songs>,_context:Context): RecyclerView.Adapter<MainScreenAdapter.MainScreenViewHolder>()
{

    var songDetails:ArrayList<Songs>?=null
    var mContext:Context?=null
    init {
        this.songDetails = _songDetails
        this.mContext=_context
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainScreenViewHolder {
        var itemView=LayoutInflater.from(parent?.context).inflate(R.layout.row_custom_mainscreen_adapter,parent,false)

        return MainScreenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder?, position: Int) {
        var songObject =songDetails?.get(position)
        holder?.trackTitle?.text=songObject?.songTitle
        holder?.trackArtist?.text=songObject?.artist
        holder?.contentHolder?.setOnClickListener({
            val songPlayingFragment= SongPlayingFragment();
            var args= Bundle()
            args.putString("songArtist",songObject?.artist)
            args.putString("path",songObject?.songData)
            args.putString("songTitle",songObject?.songTitle)
            args.putInt("SongId",songObject?.songID?.toInt() as Int)
            args.putInt("songPosition",position)
            args.putParcelableArrayList("songData",songDetails)
            songPlayingFragment.arguments=args
            (mContext as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment,songPlayingFragment)
                    .addToBackStack("SongPlayingFragment")
                    .commit()

        })
    }

    override fun getItemCount(): Int {
if(songDetails==null)
    return 0;
        else
    return (songDetails as ArrayList<Songs>).size
    }


    class MainScreenViewHolder(view : View): RecyclerView.ViewHolder(view) {
        var trackTitle: TextView?=null
        var trackArtist:TextView?= null
        var contentHolder:RelativeLayout?=null
        init{
            trackTitle=view.findViewById<TextView>(R.id.trackTitle)
            trackArtist=view.findViewById<TextView>(R.id.trackArtist)
            contentHolder=view.findViewById<RelativeLayout>(R.id.contentRow)
        }
    }
}