package com.example.gameModel.enums;

/**
 * Created by ninjup on 10/22/17.
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
    Houston (0,0),
    Kansas_City (0,0),
    Las_Vegas (0,0),
    Little_Rock (0,0),
    Los_Angeles (0,0),
    Miami (0,0),
    Montreal (0,0),
    Nashville (0,0),
    New_Orleans (0,0),
    New_York (0,0),
    Oklahoma_City (0,0),
    Omaha (0,0),
    Phoenix (0,0),
    Pittsburgh (0,0),
    Portland (0,0),
    Raleigh (0,0),
    Saint_Louis (0,0),
    Salt_Lake_City (0,0),
    San_Francisco (0,0),
    Santa_Fe (0,0),
    Sault_Ste_Marie (0,0),
    Seattle (0,0),
    Toronto (0,0),
    Vancouver (0,0),
    Washington (0,0),
    Winnipeg (0,0);

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
