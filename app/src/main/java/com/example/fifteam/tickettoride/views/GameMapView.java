package com.example.fifteam.tickettoride.views;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fifteam.tickettoride.ClientFacadeAsyncTasks.DemoAsyncTask;
import com.example.fifteam.tickettoride.R;
import com.example.fifteam.tickettoride.model.ClientGamePresenterFacade;
import com.example.fifteam.tickettoride.presenters.inGamePresenters.GameMapPresenter;
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
    List<Route> routeList;
    TextView routeNameText;
    TextView lengthText;
    TextView pointsText;
    Button demoButton;
    List<Polygon> polygonList;
    GameMapPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        routeList = ClientGamePresenterFacade.getInstance().getRouteList();
        polygonList = new ArrayList<>();
        presenter = new GameMapPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.view_game_map, container, false);

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        lengthText = (TextView) v.findViewById(R.id.length_text);
        pointsText = (TextView) v.findViewById(R.id.points_text);
        demoButton = (Button) v.findViewById(R.id.demo_button);
        //routeNameText = (TextView) v.findViewById(R.id.route_name_text);

        demoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DemoAsyncTask().execute();
            }
        });

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        drawCities();
        drawRoutes();

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

        map.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Route r = (Route) polygon.getTag();
                Integer routeLength = r.getLength();
                Integer pointValue = r.getPoints();
                lengthText.setText(routeLength.toString());
                pointsText.setText(pointValue.toString());
            }
        });

        /*
        Route testRoute1 = new Route();
        testRoute1.setRouteID("5");
        testRoute1.setClaimed(SharedColor.RED);
        Route testRoute2 = new Route();
        testRoute2.setRouteID("10");
        testRoute2.setClaimed(SharedColor.RED);
        Route testRoute3 = new Route();
        testRoute3.setRouteID("15");
        testRoute3.setClaimed(SharedColor.RED);
        claimRoute(testRoute1);
        claimRoute(testRoute2);
        claimRoute(testRoute3);
        */
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

    public void drawRoutes() {

        List<Route> singleRoutes = new ArrayList<>();
        List<Route> doubleRoutes = new ArrayList<>();

        for (Route r : routeList) {
            if (r.isDoubleRoute()) {
                doubleRoutes.add(r);
            } else {
                singleRoutes.add(r);
            }
        }

        drawSingleRoutes(singleRoutes);
        drawDoubleRoutes(doubleRoutes);

        //Route newRoute = new Route("1", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles);
    }

    private void drawSingleRoutes(List<Route> singleRoutes) {

        for (Route r : singleRoutes) {

            //these are the original city points
            double x1 = r.getEndpoint1().getLatitude();
            double y1 = r.getEndpoint1().getLongitude();
            double x2 = r.getEndpoint2().getLatitude();
            double y2 = r.getEndpoint2().getLongitude();
            double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

            //these are the points a little distance away from the first points in the direction of each other
            double x3 = x1 + ((0.57 / distance) * (x2 - x1));
            double y3 = y1 + ((0.57 / distance) * (y2 - y1));
            double x4 = x2 + ((0.57 / distance) * (x1 - x2));
            double y4 = y2 + ((0.57 / distance) * (y1 - y2));
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

            if (!r.isClaimable()) {
                polygon.setFillColor(getColorInt(r.getOwnerColor()));
            } else {
                //if the route hasn't been claimed, make it clickable
                polygon.setClickable(true);
            }

            //links the Route object to the Polygon so that the object can be accessed later
            polygon.setTag(r);
            polygonList.add(polygon);
        }
    }

    private void drawDoubleRoutes(List<Route> doubleRoutes) {

        for (int i = 0; i < doubleRoutes.size(); i++) {

            Route r = doubleRoutes.get(i);

            //gets the sister route and removes it from the list so it doesn't get iterated over again
            Route sisterRoute = r.getSisterRoute();
            doubleRoutes.remove(i + 1);

            //these are the original city points
            double x1 = r.getEndpoint1().getLatitude();
            double y1 = r.getEndpoint1().getLongitude();
            double x2 = r.getEndpoint2().getLatitude();
            double y2 = r.getEndpoint2().getLongitude();
            double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

            //these are the points a little distance away from the first points in the direction of each other
            double x3 = x1 + ((0.57 / distance) * (x2 - x1));
            double y3 = y1 + ((0.57 / distance) * (y2 - y1));
            double x4 = x2 + ((0.57 / distance) * (x1 - x2));
            double y4 = y2 + ((0.57 / distance) * (y1 - y2));
            double distance2 = Math.sqrt(Math.pow((x4 - x3), 2) + Math.pow((y4 - y3), 2));

            double dx = x1 - x2;
            double dy = y1 - y2;

            double offset = 0.2;
            double normX = (dx / distance2);
            double normY = (dy / distance2);
            double xPerp = (offset * normX);
            double yPerp = (offset * normY);

            //these are the perpendicular points that will be the endpoints of the polygon
            double x5 = x3 + (2 * yPerp);
            double y5 = y3 - (2 * xPerp);

            double x6 = x3;
            double y6 = y3;

            double x7 = x4;
            double y7 = y4;

            double x8 = x4 + (2 * yPerp);
            double y8 = y4 - (2 * xPerp);

            //endpoints for the sister route polygon
            double sx5 = x3;
            double sy5 = y3;

            double sx6 = x3 - (2 * yPerp);
            double sy6 = y3 + (2 * xPerp);

            double sx7 = x4 - (2 * yPerp);
            double sy7 = y4 + (2 * xPerp);

            double sx8 = x4;
            double sy8 = y4;

            LatLng point1 = new LatLng(x5, y5);
            LatLng point2 = new LatLng(x6, y6);
            LatLng point3 = new LatLng(x7, y7);
            LatLng point4 = new LatLng(x8, y8);

            LatLng s_point1 = new LatLng(sx5, sy5);
            LatLng s_point2 = new LatLng(sx6, sy6);
            LatLng s_point3 = new LatLng(sx7, sy7);
            LatLng s_point4 = new LatLng(sx8, sy8);

            //draw the first polygon
            Polygon polygon = map.addPolygon(new PolygonOptions()
                    .add(point1, point2, point3, point4)
                    .strokeColor(getColorInt(r.getColor()))
                    .strokeWidth(2));

            if (!r.isClaimable()) {
                polygon.setFillColor(getColorInt(r.getOwnerColor()));
            } else {
                //if the route hasn't been claimed, make it clickable
                polygon.setClickable(true);
            }

            //links the Route object to the Polygon so that the object can be accessed later
            polygon.setTag(r);
            polygonList.add(polygon);

            //draw the sister route polygon
            Polygon s_polygon = map.addPolygon(new PolygonOptions()
                    .add(s_point1, s_point2, s_point3, s_point4)
                    .strokeColor(getColorInt(sisterRoute.getColor()))
                    .strokeWidth(2));

            if (!sisterRoute.isClaimable()) {
                s_polygon.setFillColor(getColorInt(sisterRoute.getOwnerColor()));
            } else {
                //if the route hasn't been claimed, make it clickable
                s_polygon.setClickable(true);
            }

            //links the Route object to the Polygon so that the object can be accessed later
            s_polygon.setTag(sisterRoute);
            polygonList.add(s_polygon);
        }
    }

    public void claimRoute(Route claimedRoute) {
        for (Polygon p : polygonList) {
            Route r = (Route) p.getTag();
            if (r.getRouteId().equals(claimedRoute.getRouteId())) {
                p.setFillColor(getColorInt(claimedRoute.getOwnerColor()));
                p.setClickable(false);
                presenter.addToPlayerScore(claimedRoute.getPoints());
                presenter.subtractPlayerTrains(claimedRoute.getLength());
            }
        }
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
