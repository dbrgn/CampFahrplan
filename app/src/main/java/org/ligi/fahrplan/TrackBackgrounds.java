package org.ligi.fahrplan;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;

import ch.dbrgn.fahrplan.zeteco.R;
import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrackBackgrounds {

    public static Map<String, String> getHashMapResource(Context c, int hashMapResId) {
        Map<String, String> map = null;
        XmlResourceParser parser = c.getResources().getXml(hashMapResId);

        String key = null;
        String value = null;

        try {
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("map")) {
                        boolean isLinked = parser.getAttributeBooleanValue(null, "linked", false);

                        map = isLinked ? new LinkedHashMap<String,
                                String>() : new HashMap<String, String>();
                    } else if (parser.getName().equals("entry")) {
                        key = parser.getAttributeValue(null, "key");

                        if (null == key) {
                            parser.close();
                            return null;
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (parser.getName().equals("entry")) {
                        map.put(key, value);
                        key = null;
                        value = null;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (null != key) {
                        value = parser.getText();
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return map;
    }

    public static HashMap<String, Integer> buildTrackBackgroundHashMap(Map<String,
            String> trackNamesMap, String prefix, String resourceType, Context context) {
        HashMap<String, Integer> trackNameResourceIdPairs = new HashMap<String, Integer>();
        Resources res = context.getResources();
        String packageName = context.getPackageName();

        for (Map.Entry<String, String> entry : trackNamesMap.entrySet()) {
            String key = entry.getKey();
            String value = prefix;
            // Handle empty track names
            // key can have the value: ""
            // See track_resource_names.xml
            if (!TextUtils.isEmpty(key)) {
                value += "_" + entry.getValue();
            }
            int resourceId = res.getIdentifier(value, resourceType, packageName);
            trackNameResourceIdPairs.put(key, resourceId);
        }
        return trackNameResourceIdPairs;
    }

    public static HashMap<String, Integer> getTrackAccentColorNormal(Context context) {
        Map<String, String> trackNames = getHashMapResource(context, R.xml.track_resource_names);
        return buildTrackBackgroundHashMap(trackNames, "event_border_accent", "color", context);
    }

    public static HashMap<String, Integer> getTrackAccentColorHighlight(Context context) {
        Map<String, String> trackNames = getHashMapResource(context, R.xml.track_resource_names);
        return buildTrackBackgroundHashMap(trackNames, "event_border_accent_highlight", "color", context);
    }

    public static HashMap<String, Integer> getTrackNameBackgroundColorDefaultPairs(Context context) {
        Map<String, String> trackNames = getHashMapResource(context, R.xml.track_resource_names);
        return buildTrackBackgroundHashMap(trackNames, "event_border_default", "color", context);
    }

    public static HashMap<String, Integer> getTrackNameBackgroundColorHighlightPairs(Context context) {
        Map<String, String> trackNames = getHashMapResource(context, R.xml.track_resource_names);
        return buildTrackBackgroundHashMap(trackNames, "event_border_highlight", "color", context);
    }

}
