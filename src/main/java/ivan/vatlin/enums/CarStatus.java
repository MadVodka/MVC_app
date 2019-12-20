package ivan.vatlin.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "carStatus")
@XmlEnum
public enum CarStatus {
    IDLE, IN_USE, IN_MAINTAINANCE, WRITTEN_OFF;

    public String value() {
        return name();
    }

    public static CarStatus fromValue(String v) {
        return valueOf(v);
    }
}
