package org.vinst.position;

/**
 * <p>A position key</p>
 *
 * todo
 *
 * @author lars-velsky
 * @since 03/08/14
 *
 * @see Position
 */
public final class PositionKey {

    public static PositionKey of(long id){
        return new PositionKey(id);
    }

    private final long id;

    private PositionKey(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
