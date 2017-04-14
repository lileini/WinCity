package wincity.litao.com.http;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${U3} on 2016/9/18.
 */
public class APIErrorEntity implements Parcelable {
    public int status;
    public String code;
    public String message;
    public String more_info;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.code);
        dest.writeString(this.message);
        dest.writeString(this.more_info);
    }

    public APIErrorEntity() {
    }

    protected APIErrorEntity(Parcel in) {
        this.status = in.readInt();
        this.code = in.readString();
        this.message = in.readString();
        this.more_info = in.readString();
    }

    public static final Creator<APIErrorEntity> CREATOR = new Creator<APIErrorEntity>() {
        @Override
        public APIErrorEntity createFromParcel(Parcel source) {
            return new APIErrorEntity(source);
        }

        @Override
        public APIErrorEntity[] newArray(int size) {
            return new APIErrorEntity[size];
        }
    };
}
