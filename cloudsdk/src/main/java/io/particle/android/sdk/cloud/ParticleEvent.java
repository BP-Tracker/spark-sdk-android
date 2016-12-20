package io.particle.android.sdk.cloud;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.ParametersAreNonnullByDefault;


// Normally it's bad form to use network data models as API data models, but considering that
// for the moment, they'd be a 1:1 mapping, we'll just reuse this data model class.  If the
// network API changes, then we can write new classes for the network API models, without
// impacting the public API of the SDK.
@ParametersAreNonnullByDefault
public class ParticleEvent implements Parcelable {

    public static final Parcelable.Creator<ParticleEvent> CREATOR = new Parcelable.Creator<ParticleEvent>() {
        public ParticleEvent createFromParcel(Parcel in) {
            return new ParticleEvent(in);
        }

        public ParticleEvent[] newArray(int size) {
            return new ParticleEvent[size];
        }
    };

    protected ParticleEvent(Parcel in) {
        deviceId = in.readString();
        dataPayload = in.readString();
        publishedAt = new Date(in.readLong());
        timeToLive = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(deviceId);
        out.writeString(dataPayload);
        out.writeLong(publishedAt.getTime());
        out.writeInt(timeToLive);
    }

    @SerializedName("coreid")
    public final String deviceId;

    @SerializedName("data")
    public final String dataPayload;

    @SerializedName("published_at")
    public final Date publishedAt;

    @SerializedName("ttl")
    public final int timeToLive;

    public ParticleEvent(String deviceId, String dataPayload, Date publishedAt, int timeToLive) {
        this.deviceId = deviceId;
        this.dataPayload = dataPayload;
        this.publishedAt = publishedAt;
        this.timeToLive = timeToLive;
    }
}
