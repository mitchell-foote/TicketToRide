package com.example.fifteam.tickettoride.views;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;
import com.example.gameModel.classes.Route;
import com.example.gameModel.enums.City;
import com.example.model.enums.SharedColor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by USER on 10/28/2017.
 */

public class GameMapView extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap map;
    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.view_game_map, container, false);

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        drawCities();
        drawRoutes(new ArrayList<Route>());

        map.setMinZoomPreference(3.2f);
        map.setMaxZoomPreference(5.0f);

        LatLngBounds mapBounds = new LatLngBounds(new LatLng(29, -118), new LatLng(49, -77));
        map.setLatLngBoundsForCameraTarget(mapBounds);

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.6, -97.3)));

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        //LatLng sydney = new LatLng(-34, 151);
        //map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    }

    public void drawCities() {

        for (City c : City.values()) {
            LatLng cityLocation = new LatLng(c.getLatitude(), c.getLongitude());
            map.addCircle(new CircleOptions()
                    .center(cityLocation)
                    .radius(30000)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(2)
                    .fillColor(Color.BLUE)
                    .zIndex(1));
        }
    }

    public void drawRoutes(List<Route> routeList) {

        List<Route> routeTestList = setUpRoutes();

        for (Route r : routeTestList) {

            //these are the original city points
            double x1 = r.getEndpoint1().getLatitude();
            double y1 = r.getEndpoint1().getLongitude();
            double x2 = r.getEndpoint2().getLatitude();
            double y2 = r.getEndpoint2().getLongitude();
            double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

            //these are the points a little distance away from the first points in the direction of each other
            double x3 = x1 + ((0.57/distance) * (x2 - x1));
            double y3 = y1 + ((0.57/distance) * (y2 - y1));
            double x4 = x2 + ((0.57/distance) * (x1 - x2));
            double y4 = y2 + ((0.57/distance) * (y1 - y2));
            double distance2 = Math.sqrt(Math.pow((x4 - x3), 2) + Math.pow((y4 - y3), 2));

            double dx = x1 - x2;
            double dy = y1 - y2;

            double offset = 0.2;
            double normX = (dx / distance2);
            double normY = (dy / distance2);
            double xPerp = (offset * normX);
            double yPerp = (offset * normY);

            //these are the perpendicular points that will be the endpoints of the polygon
            double x5 = x3 + yPerp;
            double y5 = y3 - xPerp;

            double x6 = x3 - yPerp;
            double y6 = y3 + xPerp;

            double x7 = x4 - yPerp;
            double y7 = y4 + xPerp;

            double x8 = x4 + yPerp;
            double y8 = y4 - xPerp;

            LatLng point1 = new LatLng(x5, y5);
            LatLng point2 = new LatLng(x6, y6);
            LatLng point3 = new LatLng(x7, y7);
            LatLng point4 = new LatLng(x8, y8);

            //actual drawing of the polygon on the map
            Polygon polygon = map.addPolygon(new PolygonOptions()
                    .add(point1, point2, point3, point4)
                    .strokeColor(getColorInt(r.getColor()))
                    .strokeWidth(2));


            //LatLng point1 = new LatLng(x3, y3);
            //LatLng point2 = new LatLng(x4, y4);

            //Polyline polyline = map.addPolyline(new PolylineOptions()
            //.add(point1, point2)
            //.width(15)
            //.color(Color.parseColor("#9ff08e")));
        }

        //Route newRoute = new Route("1", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles);
    }

    private void drawSingleRoutes(List<Route> routes) {

    }

    private void drawDoubleRoutes(List<Route> routes) {

    }

    public List<Route> setUpRoutes() {

        List<Route> routeList = new ArrayList<>();

        //double route - need to set this up
        routeList.add(new Route("1", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh));
        routeList.add(new Route("2", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh));

        routeList.add(new Route("3", SharedColor.RAINBOW, 2, City.Atlanta, City.Charleston));
        routeList.add(new Route("4", SharedColor.BLUE, 5, City.Atlanta, City.Miami));

        //double route - need to set this up
        routeList.add(new Route("5", SharedColor.YELLOW, 4, City.Atlanta, City.New_Orleans));
        routeList.add(new Route("6", SharedColor.ORANGE, 4, City.Atlanta, City.New_Orleans));

        routeList.add(new Route("7", SharedColor.RAINBOW, 1, City.Atlanta, City.Nashville));

        //double route - need to set this up
        routeList.add(new Route("8", SharedColor.RAINBOW, 2, City.Boston, City.Montreal));
        routeList.add(new Route("9", SharedColor.RAINBOW, 2, City.Boston, City.Montreal));

        //double route - need to set this up
        routeList.add(new Route("10", SharedColor.YELLOW, 2, City.Boston, City.New_York));
        routeList.add(new Route("11", SharedColor.RED, 2, City.Boston, City.New_York));

        routeList.add(new Route("12", SharedColor.RAINBOW, 3, City.Calgary, City.Vancouver));
        routeList.add(new Route("13", SharedColor.WHITE, 6, City.Calgary, City.Winnipeg));

        routeList.add(new Route("14", SharedColor.RAINBOW, 4, City.Calgary, City.Helena));
        routeList.add(new Route("15", SharedColor.RAINBOW, 4, City.Calgary, City.Seattle));

        routeList.add(new Route("16", SharedColor.RAINBOW, 2, City.Charleston, City.Raleigh));
        routeList.add(new Route("17", SharedColor.RAINBOW, 2, City.Charleston, City.Atlanta));
        routeList.add(new Route("18", SharedColor.PURPLE, 4, City.Charleston, City.Miami));
        //double route
        routeList.add(new Route("19", SharedColor.ORANGE, 3, City.Chicago, City.Pittsburgh));
        routeList.add(new Route("20", SharedColor.BLACK, 3, City.Chicago, City.Pittsburgh));
        routeList.add(new Route("21", SharedColor.WHITE, 4, City.Chicago, City.Toronto));
        routeList.add(new Route("22", SharedColor.RED, 3, City.Chicago, City.Duluth));
        routeList.add(new Route("23", SharedColor.BLUE, 4, City.Chicago, City.Omaha));
        routeList.add(new Route("24", SharedColor.GREEN, 2, City.Chicago, City.Saint_Louis));
        routeList.add(new Route("25", SharedColor.WHITE, 2, City.Chicago, City.Saint_Louis));
        routeList.add(new Route("26", SharedColor.RAINBOW, 2, City.Dallas, City.Little_Rock));
        //double route
        routeList.add(new Route("27", SharedColor.RAINBOW, 2, City.Dallas, City.Oklahoma_City));
        routeList.add(new Route("28", SharedColor.RAINBOW, 2, City.Dallas, City.Oklahoma_City));
        routeList.add(new Route("29", SharedColor.RED, 4, City.Dallas, City.El_Paso));
        //double route
        routeList.add(new Route("30", SharedColor.RAINBOW, 1, City.Dallas, City.Houston));
        routeList.add(new Route("31", SharedColor.RAINBOW, 1, City.Dallas, City.Houston));
        //double route
        routeList.add(new Route("32", SharedColor.ORANGE, 4, City.Denver, City.Kansas_City));
        routeList.add(new Route("33", SharedColor.BLACK, 4, City.Denver, City.Kansas_City));
        routeList.add(new Route("34", SharedColor.PURPLE, 4, City.Denver, City.Omaha));
        routeList.add(new Route("35", SharedColor.GREEN, 4, City.Denver, City.Helena));
        //double route
        routeList.add(new Route("36", SharedColor.RED, 3, City.Denver, City.Salt_Lake_City));
        routeList.add(new Route("37", SharedColor.YELLOW, 3, City.Denver, City.Salt_Lake_City));
        routeList.add(new Route("38", SharedColor.WHITE, 5, City.Denver, City.Phoenix));
        routeList.add(new Route("39", SharedColor.RAINBOW, 2, City.Denver, City.Santa_Fe));
        routeList.add(new Route("40", SharedColor.RED, 4, City.Denver, City.Oklahoma_City));
        //double route
        routeList.add(new Route("41", SharedColor.RAINBOW, 2, City.Duluth, City.Omaha));
        routeList.add(new Route("42", SharedColor.RAINBOW, 2, City.Duluth, City.Omaha));
        routeList.add(new Route("43", SharedColor.RED, 3, City.Duluth, City.Chicago));
        routeList.add(new Route("44", SharedColor.PURPLE, 6, City.Duluth, City.Toronto));
        routeList.add(new Route("45", SharedColor.RAINBOW, 3, City.Duluth, City.Sault_Ste_Marie));
        routeList.add(new Route("46", SharedColor.BLACK, 4, City.Duluth, City.Winnipeg));
        routeList.add(new Route("47", SharedColor.ORANGE, 6, City.Duluth, City.Helena));
        routeList.add(new Route("48", SharedColor.GREEN, 6, City.El_Paso, City.Houston));
        routeList.add(new Route("49", SharedColor.YELLOW, 5, City.El_Paso, City.Oklahoma_City));
        routeList.add(new Route("50", SharedColor.RAINBOW, 2, City.El_Paso, City.Santa_Fe));
        routeList.add(new Route("51", SharedColor.RAINBOW, 3, City.El_Paso, City.Phoenix));
        routeList.add(new Route("52", SharedColor.BLACK, 6, City.El_Paso, City.Los_Angeles));
        routeList.add(new Route("53", SharedColor.BLUE, 4, City.Helena, City.Winnipeg));
        routeList.add(new Route("54", SharedColor.RED, 5, City.Helena, City.Omaha));
        routeList.add(new Route("55", SharedColor.PURPLE, 3, City.Helena, City.Salt_Lake_City));
        routeList.add(new Route("56", SharedColor.YELLOW, 6, City.Helena, City.Seattle));
        routeList.add(new Route("57", SharedColor.RAINBOW, 2, City.Houston, City.New_Orleans));
        //double route
        routeList.add(new Route("58", SharedColor.BLUE, 2, City.Kansas_City, City.Saint_Louis));
        routeList.add(new Route("59", SharedColor.PURPLE, 2, City.Kansas_City, City.Saint_Louis));
        //double route
        routeList.add(new Route("60", SharedColor.RAINBOW, 1, City.Kansas_City, City.Omaha));
        routeList.add(new Route("61", SharedColor.RAINBOW, 1, City.Kansas_City, City.Omaha));
        //double route
        routeList.add(new Route("62", SharedColor.RAINBOW, 2, City.Kansas_City, City.Oklahoma_City));
        routeList.add(new Route("63", SharedColor.RAINBOW, 2, City.Kansas_City, City.Oklahoma_City));
        routeList.add(new Route("64", SharedColor.ORANGE, 3, City.Las_Vegas, City.Salt_Lake_City));
        routeList.add(new Route("65", SharedColor.RAINBOW, 2, City.Las_Vegas, City.Los_Angeles));
        routeList.add(new Route("66", SharedColor.WHITE, 3, City.Little_Rock, City.Nashville));
        routeList.add(new Route("67", SharedColor.RAINBOW, 2, City.Little_Rock, City.Saint_Louis));
        routeList.add(new Route("68", SharedColor.RAINBOW, 2, City.Little_Rock, City.Oklahoma_City));
        routeList.add(new Route("69", SharedColor.GREEN, 3, City.Little_Rock, City.New_Orleans));
        //double route
        routeList.add(new Route("70", SharedColor.PURPLE, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("71", SharedColor.YELLOW, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("72", SharedColor.RAINBOW, 3, City.Phoenix, City.Los_Angeles));
        routeList.add(new Route("73", SharedColor.RED, 6, City.Miami, City.New_Orleans));
        routeList.add(new Route("74", SharedColor.RAINBOW, 3, City.Montreal, City.New_York));
        routeList.add(new Route("75", SharedColor.RAINBOW, 3, City.Montreal, City.Toronto));
        routeList.add(new Route("76", SharedColor.BLACK, 5, City.Montreal, City.Sault_Ste_Marie));
        routeList.add(new Route("77", SharedColor.BLACK, 3, City.Nashville, City.Raleigh));
        routeList.add(new Route("78", SharedColor.YELLOW, 4, City.Nashville, City.Pittsburgh));
        routeList.add(new Route("79", SharedColor.RAINBOW, 2, City.Nashville, City.Saint_Louis));
        //double route
        routeList.add(new Route("80", SharedColor.BLACK, 2, City.New_York, City.Washington));
        routeList.add(new Route("81", SharedColor.ORANGE, 2, City.New_York, City.Washington));
        //double route
        routeList.add(new Route("82", SharedColor.WHITE, 2, City.New_York, City.Pittsburgh));
        routeList.add(new Route("83", SharedColor.GREEN, 2, City.New_York, City.Washington));
        routeList.add(new Route("84", SharedColor.BLUE, 3, City.Oklahoma_City, City.Santa_Fe));
        routeList.add(new Route("85", SharedColor.RAINBOW, 3, City.Phoenix, City.Santa_Fe));
        routeList.add(new Route("86", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Washington));
        routeList.add(new Route("87", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Raleigh));
        routeList.add(new Route("88", SharedColor.GREEN, 5, City.Pittsburgh, City.Saint_Louis));
        routeList.add(new Route("89", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Toronto));
        routeList.add(new Route("90", SharedColor.RAINBOW, 1, City.Portland, City.Seattle));
        routeList.add(new Route("91", SharedColor.RAINBOW, 1, City.Portland, City.Seattle));
        routeList.add(new Route("92", SharedColor.BLUE, 6, City.Portland, City.Salt_Lake_City));
        routeList.add(new Route("93", SharedColor.GREEN, 5, City.Portland, City.San_Francisco));
        routeList.add(new Route("94", SharedColor.PURPLE, 5, City.Portland, City.San_Francisco));
        routeList.add(new Route("95", SharedColor.RAINBOW, 2, City.Raleigh, City.Washington));
        routeList.add(new Route("96", SharedColor.RAINBOW, 2, City.Raleigh, City.Washington));
        routeList.add(new Route("97", SharedColor.ORANGE, 5, City.Salt_Lake_City, City.San_Francisco));
        routeList.add(new Route("98", SharedColor.WHITE, 5, City.Salt_Lake_City, City.San_Francisco));
        routeList.add(new Route("99", SharedColor.RAINBOW, 6, City.Sault_Ste_Marie, City.Winnipeg));
        routeList.add(new Route("100", SharedColor.RAINBOW, 2, City.Sault_Ste_Marie, City.Toronto));
        routeList.add(new Route("101", SharedColor.RAINBOW, 1, City.Seattle, City.Vancouver));
        routeList.add(new Route("102", SharedColor.RAINBOW, 1, City.Seattle, City.Vancouver));

        return routeList;
    }

    //this method takes a SharedColor object and returns the corresponding color integer
    private int getColorInt(SharedColor color) {

        int color_int = Color.parseColor("#ff1493");

        switch(color) {
            case BLACK: {
                color_int = Color.BLACK;
                break;
            }
            case WHITE: {
                color_int = Color.WHITE;
                break;
            }
            case BLUE: {
                color_int = Color.BLUE;
                break;
            }
            case RED: {
                color_int = Color.RED;
                break;
            }
            case ORANGE: {
                color_int = Color.parseColor("#ff7f24");
                break;
            }
            case YELLOW: {
                color_int = Color.YELLOW;
                break;
            }
            case GREEN: {
                color_int = Color.GREEN;
                break;
            }
            case PURPLE: {
                color_int = Color.parseColor("#9932cc");
                break;
            }
            case RAINBOW: {
                color_int = Color.GRAY;
                break;
            }
        }
        return color_int;
    }
}
