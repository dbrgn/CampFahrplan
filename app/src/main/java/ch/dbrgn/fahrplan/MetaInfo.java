package ch.dbrgn.fahrplan;

import ch.dbrgn.fahrplan.FahrplanContract.MetasTable;

public class MetaInfo {

    public int numdays;

    public String version;

    public String title;

    public String subtitle;

    public int dayChangeHour;

    public int dayChangeMinute;

    public String eTag;

    public MetaInfo() {
        numdays = MetasTable.Defaults.NUM_DAYS_DEFAULT;
        version = "";
        title = "";
        subtitle = "";
        dayChangeHour = MetasTable.Defaults.DAY_CHANGE_HOUR_DEFAULT;
        dayChangeMinute = MetasTable.Defaults.DAY_CHANGE_MINUTE_DEFAULT;
        eTag = "";
    }
}
