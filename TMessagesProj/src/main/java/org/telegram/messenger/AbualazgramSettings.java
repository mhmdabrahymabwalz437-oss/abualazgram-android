/*
 * ABUALAZGRAM client-only preferences.
 * This class intentionally contains no Telegram protocol overrides.
 */
package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;

public final class AbualazgramSettings {
    private static final String PREFS = "abualazgram_settings";
    private static final String KEY_LUXURY_MODE = "luxury_mode";
    private static final String KEY_THEME = "theme";
    private static final String KEY_PERFORMANCE = "performance";
    private static final String KEY_APP_LOCK = "app_lock";
    private static final String KEY_HIDDEN_CHATS = "hidden_chats";

    public enum ThemePalette {
        BLACK_GOLD,
        BLACK_PLATINUM,
        ROYAL_BLUE
    }

    public enum PerformanceMode {
        BALANCED,
        HIGH,
        ECONOMY
    }

    private AbualazgramSettings() {
    }

    private static SharedPreferences prefs() {
        return ApplicationLoader.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static boolean isLuxuryModeEnabled() {
        return prefs().getBoolean(KEY_LUXURY_MODE, true);
    }

    public static void setLuxuryModeEnabled(boolean enabled) {
        prefs().edit().putBoolean(KEY_LUXURY_MODE, enabled).apply();
    }

    public static ThemePalette getThemePalette() {
        int ordinal = prefs().getInt(KEY_THEME, ThemePalette.BLACK_GOLD.ordinal());
        ThemePalette[] values = ThemePalette.values();
        return ordinal >= 0 && ordinal < values.length ? values[ordinal] : ThemePalette.BLACK_GOLD;
    }

    public static void setThemePalette(ThemePalette palette) {
        prefs().edit().putInt(KEY_THEME, palette.ordinal()).apply();
    }

    public static PerformanceMode getPerformanceMode() {
        int ordinal = prefs().getInt(KEY_PERFORMANCE, PerformanceMode.BALANCED.ordinal());
        PerformanceMode[] values = PerformanceMode.values();
        return ordinal >= 0 && ordinal < values.length ? values[ordinal] : PerformanceMode.BALANCED;
    }

    public static void setPerformanceMode(PerformanceMode mode) {
        prefs().edit().putInt(KEY_PERFORMANCE, mode.ordinal()).apply();
    }

    public static boolean isAppLockEnabled() {
        return prefs().getBoolean(KEY_APP_LOCK, false);
    }

    public static void setAppLockEnabled(boolean enabled) {
        prefs().edit().putBoolean(KEY_APP_LOCK, enabled).apply();
    }

    public static boolean areHiddenChatsEnabled() {
        return prefs().getBoolean(KEY_HIDDEN_CHATS, false);
    }

    public static void setHiddenChatsEnabled(boolean enabled) {
        prefs().edit().putBoolean(KEY_HIDDEN_CHATS, enabled).apply();
    }

    public static boolean shouldUseEnhancedAnimations() {
        return isLuxuryModeEnabled() && getPerformanceMode() != PerformanceMode.ECONOMY;
    }

    public static boolean shouldUseHighQualityMedia() {
        return getPerformanceMode() == PerformanceMode.HIGH;
    }
}
