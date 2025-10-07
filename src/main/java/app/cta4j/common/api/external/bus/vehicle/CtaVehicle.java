package app.cta4j.common.api.external.bus.vehicle;

public record CtaVehicle(
    String vid,
    String tmstmp,
    String lat,
    String lon,
    String hdg,
    int pid,
    String rt,
    String des,
    int pdist,
    boolean dly,
    String tatripid,
    String origtatripno,
    String tablockid,
    String zone,
    int mode,
    String psgld,
    int stst,
    String stsd
) {
}
