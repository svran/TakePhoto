package org.devio.takephoto.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * TakePhoto 操作成功返回的处理结果
 * <p>
 * Author: JPH
 * Date: 2016/8/11 17:01
 */
public class TImage implements Parcelable {
    private String originalPath;
    private String compressPath;
    private FromType fromType;
    private long id;
    private boolean cropped;
    private boolean compressed;

    public static TImage of(String path, FromType fromType) {
        return new TImage(path, fromType);
    }

    public static TImage of(Uri uri, FromType fromType) {
        return new TImage(uri, fromType);
    }

    private TImage(String path, FromType fromType) {
        this.originalPath = path;
        this.fromType = fromType;
    }

    private TImage(Uri uri, FromType fromType) {
        this.originalPath = uri.getPath();
        this.fromType = fromType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public FromType getFromType() {
        return fromType;
    }

    public void setFromType(FromType fromType) {
        this.fromType = fromType;
    }

    public boolean isCropped() {
        return cropped;
    }

    public void setCropped(boolean cropped) {
        this.cropped = cropped;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public enum FromType {
        CAMERA, OTHER
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalPath);
        dest.writeString(this.compressPath);
        dest.writeInt(this.fromType == null ? -1 : this.fromType.ordinal());
        dest.writeLong(this.id);
        dest.writeByte(this.cropped ? (byte) 1 : (byte) 0);
        dest.writeByte(this.compressed ? (byte) 1 : (byte) 0);
    }

    protected TImage(Parcel in) {
        this.originalPath = in.readString();
        this.compressPath = in.readString();
        int tmpFromType = in.readInt();
        this.fromType = tmpFromType == -1 ? null : FromType.values()[tmpFromType];
        this.id = in.readLong();
        this.cropped = in.readByte() != 0;
        this.compressed = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TImage> CREATOR = new Parcelable.Creator<TImage>() {
        @Override
        public TImage createFromParcel(Parcel source) {
            return new TImage(source);
        }

        @Override
        public TImage[] newArray(int size) {
            return new TImage[size];
        }
    };
}
