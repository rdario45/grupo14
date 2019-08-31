package domain;

import io.vavr.collection.List;

public enum Status {
    UNKNOWN,
    ACTIVE,
    INACTIVE;

    public static Status of(String name) {
        return List.of(Status.values()).find(t -> t.name().equals(name)).getOrElse(Status.UNKNOWN);
    }

}
