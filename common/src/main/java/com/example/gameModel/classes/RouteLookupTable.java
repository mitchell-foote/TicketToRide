package com.example.gameModel.classes;

import com.example.gameModel.enums.City;
import com.example.model.enums.SharedColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ninjup on 11/10/17.
 */

public class RouteLookupTable {

    private static Map<String, Route> routeTable = new HashMap<>();
    private RouteLookupTable(){};

    static {
        Route route1;
        Route route2;

        //double route
        route1 = new Route("1", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh);
        route2 = new Route("2", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("1", route1);
        routeTable.put("2", route2);

        routeTable.put("3", new Route("3", SharedColor.RAINBOW, 2, City.Atlanta, City.Charleston));
        routeTable.put("4", new Route("4", SharedColor.BLUE, 5, City.Atlanta, City.Miami));

        //double route
        route1 = new Route("5", SharedColor.YELLOW, 4, City.Atlanta, City.New_Orleans);
        route2 = new Route("6", SharedColor.ORANGE, 4, City.Atlanta, City.New_Orleans);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("5", route1);
        routeTable.put("6", route2);

        routeTable.put("7", new Route("7", SharedColor.RAINBOW, 1, City.Atlanta, City.Nashville));

        //double route
        route1 = new Route("8", SharedColor.RAINBOW, 2, City.Boston, City.Montreal);
        route2 = new Route("9", SharedColor.RAINBOW, 2, City.Boston, City.Montreal);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("8", route1);
        routeTable.put("9", route2);

        //double route
        route1 = new Route("10", SharedColor.YELLOW, 2, City.Boston, City.New_York);
        route2 = new Route("11", SharedColor.RED, 2, City.Boston, City.New_York);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("10", route1);
        routeTable.put("11", route2);

        routeTable.put("12", new Route("12", SharedColor.RAINBOW, 3, City.Calgary, City.Vancouver));
        routeTable.put("13", new Route("13", SharedColor.WHITE, 6, City.Calgary, City.Winnipeg));
        routeTable.put("14", new Route("14", SharedColor.RAINBOW, 4, City.Calgary, City.Helena));
        routeTable.put("15", new Route("15", SharedColor.RAINBOW, 4, City.Calgary, City.Seattle));
        routeTable.put("16", new Route("16", SharedColor.RAINBOW, 2, City.Charleston, City.Raleigh));
        routeTable.put("17", new Route("17", SharedColor.PURPLE, 4, City.Charleston, City.Miami));

        //double route
        route1 = new Route("18", SharedColor.ORANGE, 3, City.Chicago, City.Pittsburgh);
        route2 = new Route("19", SharedColor.BLACK, 3, City.Chicago, City.Pittsburgh);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("18", route1);
        routeTable.put("19", route2);

        routeTable.put("20", new Route("20", SharedColor.WHITE, 4, City.Chicago, City.Toronto));
        routeTable.put("21", new Route("21", SharedColor.RED, 3, City.Chicago, City.Duluth));
        routeTable.put("22", new Route("22", SharedColor.BLUE, 4, City.Chicago, City.Omaha));

        //double route
        route1 = new Route("23", SharedColor.GREEN, 2, City.Chicago, City.Saint_Louis);
        route2 = new Route("24", SharedColor.WHITE, 2, City.Chicago, City.Saint_Louis);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("23", route1);
        routeTable.put("24", route2);

        routeTable.put("25", new Route("25", SharedColor.RAINBOW, 2, City.Dallas, City.Little_Rock));

        //double route
        route1 = new Route("26", SharedColor.RAINBOW, 2, City.Dallas, City.Oklahoma_City);
        route2 = new Route("27", SharedColor.RAINBOW, 2, City.Dallas, City.Oklahoma_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("26", route1);
        routeTable.put("27", route2);

        routeTable.put("28", new Route("28", SharedColor.RED, 4, City.Dallas, City.El_Paso));

        //double route
        route1 = new Route("29", SharedColor.RAINBOW, 1, City.Dallas, City.Houston);
        route2 = new Route("30", SharedColor.RAINBOW, 1, City.Dallas, City.Houston);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("29", route1);
        routeTable.put("30", route2);

        //double route
        route1 = new Route("31", SharedColor.ORANGE, 4, City.Denver, City.Kansas_City);
        route2 = new Route("32", SharedColor.BLACK, 4, City.Denver, City.Kansas_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("31", route1);
        routeTable.put("32", route2);

        routeTable.put("33", new Route("33", SharedColor.PURPLE, 4, City.Denver, City.Omaha));
        routeTable.put("34", new Route("34", SharedColor.GREEN, 4, City.Denver, City.Helena));

        //double route
        route1 = new Route("35", SharedColor.RED, 3, City.Denver, City.Salt_Lake_City);
        route2 = new Route("36", SharedColor.YELLOW, 3, City.Denver, City.Salt_Lake_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("35", route1);
        routeTable.put("36", route2);

        routeTable.put("37", new Route("37", SharedColor.WHITE, 5, City.Denver, City.Phoenix));
        routeTable.put("38", new Route("38", SharedColor.RAINBOW, 2, City.Denver, City.Santa_Fe));
        routeTable.put("39", new Route("39", SharedColor.RED, 4, City.Denver, City.Oklahoma_City));

        //double route
        route1 = new Route("40", SharedColor.RAINBOW, 2, City.Duluth, City.Omaha);
        route2 = new Route("41", SharedColor.RAINBOW, 2, City.Duluth, City.Omaha);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("40", route1);
        routeTable.put("41", route2);

        routeTable.put("42", new Route("42", SharedColor.PURPLE, 6, City.Duluth, City.Toronto));
        routeTable.put("43", new Route("43", SharedColor.RAINBOW, 3, City.Duluth, City.Sault_Ste_Marie));
        routeTable.put("44", new Route("44", SharedColor.BLACK, 4, City.Duluth, City.Winnipeg));
        routeTable.put("45", new Route("45", SharedColor.ORANGE, 6, City.Duluth, City.Helena));
        routeTable.put("46", new Route("46", SharedColor.GREEN, 6, City.El_Paso, City.Houston));
        routeTable.put("47", new Route("47", SharedColor.YELLOW, 5, City.El_Paso, City.Oklahoma_City));
        routeTable.put("48", new Route("48", SharedColor.RAINBOW, 2, City.El_Paso, City.Santa_Fe));
        routeTable.put("49", new Route("49", SharedColor.RAINBOW, 3, City.El_Paso, City.Phoenix));
        routeTable.put("50", new Route("50", SharedColor.BLACK, 6, City.El_Paso, City.Los_Angeles));
        routeTable.put("51", new Route("51", SharedColor.BLUE, 4, City.Helena, City.Winnipeg));
        routeTable.put("52", new Route("52", SharedColor.RED, 5, City.Helena, City.Omaha));
        routeTable.put("53", new Route("53", SharedColor.PURPLE, 3, City.Helena, City.Salt_Lake_City));
        routeTable.put("54", new Route("54", SharedColor.YELLOW, 6, City.Helena, City.Seattle));
        routeTable.put("55", new Route("55", SharedColor.RAINBOW, 2, City.Houston, City.New_Orleans));

        //double route
        route1 = new Route("56", SharedColor.BLUE, 2, City.Kansas_City, City.Saint_Louis);
        route2 = new Route("57", SharedColor.PURPLE, 2, City.Kansas_City, City.Saint_Louis);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("56", route1);
        routeTable.put("57", route2);

        //double route
        route1 = new Route("58", SharedColor.RAINBOW, 1, City.Kansas_City, City.Omaha);
        route2 = new Route("59", SharedColor.RAINBOW, 1, City.Kansas_City, City.Omaha);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("58", route1);
        routeTable.put("59", route2);

        //double route
        route1 = new Route("60", SharedColor.RAINBOW, 2, City.Kansas_City, City.Oklahoma_City);
        route2 = new Route("61", SharedColor.RAINBOW, 2, City.Kansas_City, City.Oklahoma_City);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("60", route1);
        routeTable.put("61", route2);

        routeTable.put("62", new Route("62", SharedColor.ORANGE, 3, City.Las_Vegas, City.Salt_Lake_City));
        routeTable.put("63", new Route("63", SharedColor.RAINBOW, 2, City.Las_Vegas, City.Los_Angeles));
        routeTable.put("64", new Route("64", SharedColor.WHITE, 3, City.Little_Rock, City.Nashville));
        routeTable.put("65", new Route("65", SharedColor.RAINBOW, 2, City.Little_Rock, City.Saint_Louis));
        routeTable.put("66", new Route("66", SharedColor.RAINBOW, 2, City.Little_Rock, City.Oklahoma_City));
        routeTable.put("67", new Route("67", SharedColor.GREEN, 3, City.Little_Rock, City.New_Orleans));

        //double route
        route1 = new Route("68", SharedColor.PURPLE, 3, City.San_Francisco, City.Los_Angeles);
        route2 = new Route("69", SharedColor.YELLOW, 3, City.San_Francisco, City.Los_Angeles);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("68", route1);
        routeTable.put("69", route2);

        routeTable.put("70", new Route("70", SharedColor.RAINBOW, 3, City.Phoenix, City.Los_Angeles));
        routeTable.put("71", new Route("71", SharedColor.RED, 6, City.Miami, City.New_Orleans));
        routeTable.put("72", new Route("72", SharedColor.RAINBOW, 3, City.Montreal, City.New_York));
        routeTable.put("73", new Route("73", SharedColor.RAINBOW, 3, City.Montreal, City.Toronto));
        routeTable.put("74", new Route("74", SharedColor.BLACK, 5, City.Montreal, City.Sault_Ste_Marie));
        routeTable.put("75", new Route("75", SharedColor.BLACK, 3, City.Nashville, City.Raleigh));
        routeTable.put("76", new Route("76", SharedColor.YELLOW, 4, City.Nashville, City.Pittsburgh));
        routeTable.put("77", new Route("77", SharedColor.RAINBOW, 2, City.Nashville, City.Saint_Louis));

        //double route
        route1 = new Route("78", SharedColor.BLACK, 2, City.New_York, City.Washington);
        route2 = new Route("79", SharedColor.ORANGE, 2, City.New_York, City.Washington);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("78", route1);
        routeTable.put("79", route2);

        routeTable.put("80", new Route("80", SharedColor.WHITE, 2, City.New_York, City.Pittsburgh));
        routeTable.put("81", new Route("81", SharedColor.GREEN, 2, City.New_York, City.Washington));
        routeTable.put("82", new Route("82", SharedColor.BLUE, 3, City.Oklahoma_City, City.Santa_Fe));
        routeTable.put("83", new Route("83", SharedColor.RAINBOW, 3, City.Phoenix, City.Santa_Fe));
        routeTable.put("84", new Route("84", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Washington));
        routeTable.put("85", new Route("85", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Raleigh));
        routeTable.put("86", new Route("86", SharedColor.GREEN, 5, City.Pittsburgh, City.Saint_Louis));
        routeTable.put("87", new Route("87", SharedColor.RAINBOW, 2, City.Pittsburgh, City.Toronto));

        //double route
        route1 = new Route("88", SharedColor.RAINBOW, 1, City.Portland, City.Seattle);
        route2 = new Route("89", SharedColor.RAINBOW, 1, City.Portland, City.Seattle);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("88", route1);
        routeTable.put("89", route2);

        routeTable.put("90", new Route("90", SharedColor.BLUE, 6, City.Portland, City.Salt_Lake_City));

        //double route
        route1 = new Route("91", SharedColor.GREEN, 5, City.Portland, City.San_Francisco);
        route2 = new Route("92", SharedColor.PURPLE, 5, City.Portland, City.San_Francisco);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("91", route1);
        routeTable.put("92", route2);

        //double route
        route1 = new Route("93", SharedColor.RAINBOW, 2, City.Raleigh, City.Washington);
        route2 = new Route("94", SharedColor.RAINBOW, 2, City.Raleigh, City.Washington);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("93", route1);
        routeTable.put("94", route2);

        //double route
        route1 = new Route("95", SharedColor.ORANGE, 5, City.Salt_Lake_City, City.San_Francisco);
        route2 = new Route("96", SharedColor.WHITE, 5, City.Salt_Lake_City, City.San_Francisco);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("95", route1);
        routeTable.put("96", route2);

        routeTable.put("97", new Route("97", SharedColor.RAINBOW, 6, City.Sault_Ste_Marie, City.Winnipeg));
        routeTable.put("98", new Route("98", SharedColor.RAINBOW, 2, City.Sault_Ste_Marie, City.Toronto));

        //double route
        route1 = new Route("99", SharedColor.RAINBOW, 1, City.Seattle, City.Vancouver);
        route2 = new Route("100", SharedColor.RAINBOW, 1, City.Seattle, City.Vancouver);
        route1.setDoubleRoute(true);
        route1.setSisterRoute(route2);
        route2.setDoubleRoute(true);
        route2.setSisterRoute(route2);
        routeTable.put("99", route1);
        routeTable.put("100", route1);
    }

    public static Route getRouteById(String routeId) {
        return routeTable.get(routeId);
    }

}
