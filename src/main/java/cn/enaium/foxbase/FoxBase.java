package cn.enaium.foxbase;

import cn.enaium.foxbase.config.ConfigManager;
import cn.enaium.foxbase.event.EventManager;
import cn.enaium.foxbase.module.ModuleManager;
import cn.enaium.foxbase.setting.SettingManager;

public enum FoxBase {

    instance;

    public String name = "LawaxiHC";
    public String version = "1.0.1";

    public EventManager eventManager;
    public ModuleManager moduleManager;
    public SettingManager settingManager;
    public ConfigManager configManager;

    public void run() {
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        settingManager = new SettingManager();
        configManager = new ConfigManager();
        moduleManager.loadMods();
        configManager.loadConfig();
    }

    public void stop() {
        configManager.saveConfig();
    }

}
