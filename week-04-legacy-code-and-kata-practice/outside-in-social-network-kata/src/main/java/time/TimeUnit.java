package time;

public enum TimeUnit {

    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    HOUR("hour"),
    MINUTE("minute"),
    SECOND("second");

    public final String unit;

    TimeUnit(String unit) {
        this.unit = unit;
    }
}
