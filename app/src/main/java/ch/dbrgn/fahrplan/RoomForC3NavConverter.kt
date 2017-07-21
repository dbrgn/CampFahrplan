package ch.dbrgn.fahrplan

object RoomForC3NavConverter {

    private val CCH_MAP = mapOf(
            "HALL 1" to "g:h1",
            "HALL 2" to "g:h2",
            "HALL 3" to "h3",
            "HALL 6" to "h6",
            "HALL 13" to "h13",
            "HALL 14" to "h14",

            "HALL B" to "hb",
            "HALL C" to "hc",
            "HALL D" to "hd",
            "HALL E" to "he",
            "HALL F" to "hf",
            "HALL G" to "hg",
            "HALL H" to "hh"
    )

    fun convert(venue: String, room: String?) = if (venue.toUpperCase() == "CCH" && room != null) {
        CCH_MAP[room.toUpperCase().replace("SAAL", "HALL")]
    } else {
        null
    }

}
