package fpmi.db.entities;

import java.io.Serializable;

/**
 * Roles of users.<br>
 *     Available roles: {@link #ADMIN}, {@link #USUAL}
 *
 * @author Ihar Zharykau
 */
public enum Role implements Serializable {
    /**
     * Usual role
     */
    USUAL,
    /**
     * Admin role
     */
    ADMIN
}
