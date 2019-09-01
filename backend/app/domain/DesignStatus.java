package domain;

import io.vavr.collection.List;

public enum DesignStatus {
    UNKNOWN,
    PROCESSING,
    AVAILABLE;

    public static DesignStatus of(String name) {
        return List.of(DesignStatus.values()).find(t -> t.name().equals(name)).getOrElse(DesignStatus.UNKNOWN);
    }
}
