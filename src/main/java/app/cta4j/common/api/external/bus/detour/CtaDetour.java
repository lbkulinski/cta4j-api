package app.cta4j.common.api.external.bus.detour;

import java.util.List;

public record CtaDetour(
    String id,

    int ver,

    int st,

    String desc,

    List<CtaDetoursRouteDirection> rtdirs,

    String startdt,

    String enddt
) {
}
