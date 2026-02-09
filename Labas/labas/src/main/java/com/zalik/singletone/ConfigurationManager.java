package com.zalik.singletone;


public class ConfigurationManager {
    // Єдиний екземпляр класу, volatile для безпеки в багатопоточному середовищі
    private static volatile ConfigurationManager instance;

    private String serverUrl;
    private int timeout;

    // Приватний конструктор — забороняє створення об'єктів через new
    private ConfigurationManager() {
        // Імітація завантаження даних, наприклад, з файлу або БД
        this.serverUrl = "https://api.college-project.com";
        this.timeout = 5000;
        System.out.println("[System]: Налаштування завантажено з конфігураційного файлу.");
    }

    // Глобальна точка доступу
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    // Методи для отримання даних
    public String getServerUrl() { return serverUrl; }
    public int getTimeout() { return timeout; }

    public void setServerUrl(String url) {
        this.serverUrl = url;
    }
}