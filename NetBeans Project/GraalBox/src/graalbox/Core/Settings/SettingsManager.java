package graalbox.Core.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager
{

    // Application Name and Version
    public static final String fApplicationTitle = "GraalBox";
    public static final String fApplicationVersion = "Beta 1.0";

    // Application Settings 
    private static String fSettingsFilePath = "";
    public static final Properties fAppSettings = new Properties();

    static
    {
        fSettingsFilePath = getAppSettingsFilePath();

        LoadSettings();
    }

    /**
     * Load the App settings
     */
    private static void LoadSettings()
    {
        try
        {
            File configFile = new File(fSettingsFilePath);

            try ( FileReader reader = new FileReader(configFile))
            {
                fAppSettings.load(reader);
            }
        }
        catch (IOException ex)
        {
            // file does not exist
        }
    }

    /**
     * Save the App settings
     */
    public static void SaveSettings()
    {
        try
        {
            File configFile = new File(fSettingsFilePath);
            try ( FileWriter writer = new FileWriter(configFile))
            {
                fAppSettings.store(writer, "GraalBoxSettings");
            }
        }
        catch (IOException ex)
        {
            // Cannot Save Settings
        }
    }

    /**
     * Returns the file path of the RabbitCAM.settings file
     *
     * @return
     */
    public static String getAppSettingsFilePath()
    {
        return System.getProperty("user.home") + File.separator + "GraalBox.settings";
    }

    public static String getPropertyValue(String property)
    {
        return fAppSettings.getProperty(property);
    }
}
