package com.musico.jitcodez.musico.Databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.musico.jitcodez.musico.Songs

/**
 * Created by jitu on 6/2/18.
 */
class MusicoDatabase: SQLiteOpenHelper {

    var _songList=ArrayList<Songs>()

    override fun onCreate(db: SQLiteDatabase?) {

    db?.execSQL("CREATE TABLE"+Staticated.TABLE_NAME+"( "+Staticated.COLUMN_Id+" INTEGER, "+Staticated.COLUMN_SONG_ARTIST+" STRING,"+Staticated.COLUMN_SONG_TITLE+" STRING,"+Staticated.COLUMN_SONG_PATH+" STRING);")


    }

    object Staticated{
        val DB_NAME="FavoriteDatabase"
        val TABLE_NAME="FavoriteTable"
        val COLUMN_Id="SongID"
        val COLUMN_SONG_TITLE="SongTitle"
        val COLUMN_SONG_ARTIST="SongArtist"
        val COLUMN_SONG_PATH="SongPath"
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version)
    constructor(context: Context?) : super(context, Staticated.DB_NAME, null, 1)

    fun storeAsFavorites(id:Int?,artist:String?,songTitle:String?,path:String?)
    {
        val db=this.writableDatabase
        var contentValues=ContentValues()
        contentValues.put(Staticated.COLUMN_Id,id)
        contentValues.put(Staticated.COLUMN_SONG_ARTIST,artist)
        contentValues.put(Staticated.COLUMN_SONG_TITLE,songTitle)
        contentValues.put(Staticated.COLUMN_SONG_PATH,path)

        db.insert(Staticated.TABLE_NAME,null,contentValues)
        db.close()
    }

    fun queryDBList(): ArrayList<Songs>?
    {
        var db=this.readableDatabase
        val query_params="SELECT * FROM "+Staticated.TABLE_NAME
        var cSor=db.rawQuery(query_params,null)
        if(cSor.moveToFirst())
        {
            do {

                var _id=cSor?.getInt(cSor.getColumnIndexOrThrow(Staticated.COLUMN_Id))
                var _artist=cSor?.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_ARTIST))
                var _title=cSor?.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_TITLE))
                var _songPath=cSor?.getString(cSor.getColumnIndexOrThrow(Staticated.COLUMN_SONG_PATH))

_songList.add(Songs(_id!!.toLong(), _title!!, _artist!!, _songPath!!,0))

            }while(cSor.moveToNext())
        }
        else
        {
            return null;
        }
        return _songList
    }

    fun checkifIdExists(_id:Int):Boolean{
        var storeId=-1090
        val db=this.readableDatabase
        val query_params="SELECT * FROM "+Staticated.TABLE_NAME+" WHERE SongId ='$_id"
        val cSor=db.rawQuery(query_params,null)
        if(cSor.moveToFirst())
        {
            do{
                storeId=cSor.getInt(cSor.getColumnIndexOrThrow(Staticated.COLUMN_Id))

            }while (cSor.moveToNext())
        }
        else
            return false
        return storeId!=-1090

    }
    fun deleteFavorite(_id:Int)
    {
        val db=this.writableDatabase
        db.delete(Staticated.TABLE_NAME,Staticated.COLUMN_Id+"="+_id,null)
        db.close()
    }

}