package com.example.gameModel.enums;

/**
 * Created by ninjup on 10/22/17.
 * cities to be used as anchors for routes
 */

public enum City {

    Atlanta (33.748995, -84.387982),
    Boston (42.360082, -71.058880),
    Calgary (51.048615, -114.070846),
    Charleston (32.776475, -79.931051),
    Chicago (41.878114, -87.629798),
    Dallas (32.776664, -96.796988),
    Denver (39.739236, -104.990251),
    Duluth (46.786672, -92.100485),
    El_Paso (31.761878, -106.485022),
    Helena (46.588371, -112.024505),
    Houston (29.7604,-95.3698),
    Kansas_City (39.0097,-94.5786),
    Las_Vegas (36.1699,-115.1398),
    Little_Rock (34.7465,-92.2896),
    Los_Angeles (34.0522,-118.2437),
    Miami (25.7617,-80.1918),
    Montreal (45.5017,-73.56730),
    Nashville (36.1627,-86.78160),
    New_Orleans (23.9511,-90.07150),
    New_York (40.7128,-74.0060),
    Oklahoma_City (35.4676,-97.5164),
    Omaha (41.2524,-95.9980),
    Phoenix (33.4484,-112.07400),
    Pittsburgh (40.4406,-79.99590),
    Portland (45.5231,-122.67650),
    Raleigh (35.7796,-78.63820),
    Saint_Louis (38.6270,-90.19940),
    Salt_Lake_City (40.7608,-111.8910),
    San_Francisco (37.7749,-122.4194),
    Santa_Fe (35.6870,-105.93780),
    Sault_Ste_Marie (46.4953,-84.34530),
    Seattle (47.6062,-122.3321),
    Toronto (43.6532,-79.38320),
    Vancouver (49.2827,-123.12070),
    Washington (38.9072,-77.0369),
    Winnipeg (49.8951,-97.1384);

    private final double latitude;
    private final double longitude;

    City(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
