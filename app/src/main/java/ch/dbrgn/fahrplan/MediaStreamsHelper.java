package ch.dbrgn.fahrplan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import info.metadude.java.library.brockman.models.Group;
import info.metadude.java.library.brockman.models.Offer;
import info.metadude.java.library.brockman.models.Room;
import info.metadude.java.library.brockman.models.Stream;
import info.metadude.java.library.brockman.models.Url;

public class MediaStreamsHelper {

    @Nullable
    public static String getThumbnailUrlForLectureRoom(@NonNull List<Offer> offers,
                                                       @NonNull String lectureRoom) {
        for (Offer offer : offers) {
            for (Group group : offer.groups) {
                for (Room room : group.rooms) {
                    if (lectureRoom.equalsIgnoreCase(room.scheduleName)) {
                        return room.thumb;
                    }
                }
            }
        }
        return null;
    }

    public static
    @NonNull
    List<Stream> getStreamsForLectureRoom(
            @NonNull List<Offer> offers, @NonNull String lectureRoom) {
        List<Stream> filteredStreams = new ArrayList<Stream>(8);
        for (Offer offer : offers) {
            for (Group group : offer.groups) {
                for (Room room : group.rooms) {
                    String roomName = room.scheduleName;
                    if (lectureRoom.equalsIgnoreCase(roomName)) {
                        filteredStreams.addAll(room.streams);
                    }
                }
            }
        }
        if (filteredStreams.isEmpty()) {
            return Collections.emptyList();
        }
        return filteredStreams;
    }

    public static
    @Nullable
    String getJoinedStreamLinks(@NonNull List<Stream> streams) {
        List<String> streamLinkList = new ArrayList<String>(8);
        for (Stream stream : streams) {
            String streamDisplay = stream.display;
            List<Url> urls = stream.urls;
            if (urls != null && !urls.isEmpty()) {
                for (Url url : urls) {
                    if (url != null) {
                        streamLinkList.add("<a href=\"" + url.url + "\">" +
                                streamDisplay + " (" + url.display + ")</a>");
                    }
                }
            }
        }
        if (streamLinkList.isEmpty()) {
            return null;
        }
        return TextUtils.join("<br>", streamLinkList);
    }

}
