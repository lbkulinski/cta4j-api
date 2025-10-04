package app.cta4j.api.external.bus.predictions;

public record CtaPredictionsPrd(
    String tmstmp,
    String typ,
    String stpnm,
    String stpid,
    String vid,
    int dstp,
    String rt,
    String rtdd,
    String rtdir,
    String des,
    String prdtm,
    String tablockid,
    String tatripid,
    String origtatripno,
    boolean dly,
    int dyn,
    String prdctdn,
    String zone,
    String psgld,
    int stst,
    String stsd,
    int flagstop
) {
}
