package br.com.rubiomovies.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * @author RubioAlves
 * Created 08/06/2021 at 16:26
 */
@Parcelize
data class MovieDados(val poster:String, val titulo:String, val detalhes:String):Parcelable
