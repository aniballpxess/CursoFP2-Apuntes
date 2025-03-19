package com.example.whanime.ui.search;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

// Entidad clase para los elementos de búsqueda
@Entity(tableName = "search_items")
public class SearchItem implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id; // Llave primaria
    public String image; // URL de la imagen
    public String filename; // Nombre del archivo
    public String episode; // Episodio
    public String video; // URL del video

    // Constructor
    public SearchItem(int id, String image, String filename, String episode, String video) {
        this.id = id;
        this.image = image;
        this.filename = filename;
        this.episode = episode;
        this.video = video;
    }

    // Constructor sin ID
    @Ignore
    public SearchItem(String image, String filename, String episode, String video) {
        this.image = image;
        this.filename = filename;
        this.episode = episode;
        this.video = video;
    }

    // Constructor Parcelable
    protected SearchItem(Parcel in) {
        id = in.readInt();
        image = in.readString();
        filename = in.readString();
        episode = in.readString();
        video = in.readString();
    }

    public static final Creator<SearchItem> CREATOR = new Creator<SearchItem>() {
        @Override
        public SearchItem createFromParcel(Parcel in) {
            return new SearchItem(in);
        }

        @Override
        public SearchItem[] newArray(int size) {
            return new SearchItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(filename);
        dest.writeString(episode);
        dest.writeString(video);
    }

    // Getters y setters
    public int getId() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getName() {
        return filename;
    }
    public String getEpisode() {
        return episode;
    }
    public String getVideo() {
        return video;
    }
}