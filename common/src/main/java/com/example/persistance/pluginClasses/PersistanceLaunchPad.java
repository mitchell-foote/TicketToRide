package com.example.persistance.pluginClasses;

import com.example.persistance.pluginInterfaces.IPersistanceManagerObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Mitchell Foote on 12/5/2017.
 */

public class PersistanceLaunchPad
{
    public IPersistanceManagerObject initPlugin(String pluginName)
    {
        String fileName = "config.txt";

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(reader);

            String line;
            String jarPath = null;
            String className = null;
            while((line = bufReader.readLine()) != null) {
                String[] sections = line.split(":");
                if (sections[0].equals(pluginName)){
                    jarPath = sections[1];
                    className = sections[2];

                    System.out.println(jarPath + "    " + className);
                    break;
                }
            }

            if (jarPath == null) {
                return null;
            } else {
                URL[] classLoaderUrls = new URL[]{new URL("file:///" + jarPath)};
                URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

                Class<?> dtbClass = urlClassLoader.loadClass(className);
                Constructor<?> constructor = dtbClass.getConstructor();
                return (IPersistanceManagerObject) constructor.newInstance();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
